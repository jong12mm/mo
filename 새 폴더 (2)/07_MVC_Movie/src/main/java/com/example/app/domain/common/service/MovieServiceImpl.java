package com.example.app.domain.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.app.domain.common.dao.MovieDao;
import com.example.app.domain.common.dao.MovieDaoImpl;
import com.example.app.domain.common.dao.common.ConnectionPool;
import com.example.app.domain.common.dto.Criteria;
import com.example.app.domain.common.dto.MovieDto;
import com.example.app.domain.common.dto.PageDto;

public class MovieServiceImpl implements MovieService {
	
	private MovieDao dao;
	private ConnectionPool connectionPool; //05-01Day
	
	private static MovieService instance ;
	public static MovieService getInstance() throws Exception {
		if(instance==null)
			instance=new MovieServiceImpl();
		return instance;
	}
	
	private MovieServiceImpl() throws Exception{
		
		dao = MovieDaoImpl.getInstance();
		this.connectionPool = ConnectionPool.getInstance();
	}
	
	@Override
	public boolean movieRegister(MovieDto dto) throws Exception {
		
		//TX
		connectionPool.txStart();			//05-01 Day TX
		boolean result = dao.Insert(dto);	//05-01 Day TX
		connectionPool.txCommit();			//05-01 Day TX
		
		return result;
	};
	@Override
	public List<MovieDto> getAllMovies() throws Exception {
		
		//TX
		connectionPool.txStart();				//05-01 Day TX
		List<MovieDto> list = dao.SelectAll();	//05-01 Day TX
		connectionPool.txCommit();				//05-01 Day TX
		return list;
	}
	
	@Override
	public Map<String,Object> getAllMovies(Criteria criteria)  throws Exception{
		//TX
		connectionPool.txStart();				//05-01 Day TX
		
		Map<String,Object> resultValue = new HashMap();
		
		if(criteria.getType()==null || criteria.getKeyword() == null)
		{
		int count =  dao.Count();
		System.out.println("total Count : " + count);
		PageDto pageDto = new PageDto(count,criteria);
		System.out.println("pageDto : " + pageDto);
		
		int offset = (criteria.getPageno()-1)*criteria.getAmount();
		List<MovieDto> list = dao.selectAll(offset,criteria.getAmount());
		System.out.println("list : " + list);

		resultValue.put("pageDto", pageDto);	//페이징처리에 필요한 요소들
		resultValue.put("list", list);			//해당 페이지에 표시할 게시물들
		resultValue.put("count", count);		//전체 게시물 건수

		}
		else {
			int count =  dao.Count(criteria);
			System.out.println("total Count : " + count);
			
			PageDto pageDto = new PageDto(count,criteria);
			System.out.println("pageDto : " + pageDto);
			
			int offset = (criteria.getPageno()-1)*criteria.getAmount();
			List<MovieDto> list = dao.selectAll(offset,criteria.getAmount(),criteria);
			System.out.println("list : " + list);

			resultValue.put("pageDto", pageDto);	//페이징처리에 필요한 요소들
			resultValue.put("list", list);			//해당 페이지에 표시할 게시물들
			resultValue.put("count", count);		//전체 게시물 건수
		}
		connectionPool.txCommit();				//05-01 Day TX
		return resultValue;
	}

	
	@Override
	public MovieDto getMovie(int movieId) throws Exception{
		
		
		MovieDto dto =  dao.Select(movieId);
		
		return dto;
	}

	@Override
	public void removeMovie(int movieId) throws Exception {
		connectionPool.txStart();
			dao.Delete(movieId);
		System.out.println(dao.Delete(movieId));
		connectionPool.txCommit();
		
		
	}
	public boolean updateMovie(MovieDto dto) throws Exception {
		connectionPool.txStart();
		dao.Update(dto);
		connectionPool.txCommit();
		return dao.Update(dto);
	}


}