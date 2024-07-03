package com.example.app.domain.common.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.app.domain.common.dao.common.CommonDao;
import com.example.app.domain.common.dto.Criteria;
import com.example.app.domain.common.dto.MovieDto;




public class MovieDaoImpl extends CommonDao implements MovieDao {
	
	private static MovieDao instance ;
	public static MovieDao getInstance() throws Exception {
		if(instance==null)
			instance=new MovieDaoImpl();
		return instance;
	}
	private MovieDaoImpl() throws Exception{
		System.out.println("[DAO] MovieDaoImpl's INIT.." + conn);
		
	}
	
	//INSERT
	@Override
	public boolean Insert(MovieDto dto) throws Exception{
		pstmt = conn.prepareStatement("insert into movie values(?,?,?,?,?,?)");
		pstmt.setInt(1,dto.getMovieId());
		pstmt.setString(2,dto.getMovieTitle());
		pstmt.setString(3,dto.getMoviegenre());
		pstmt.setBoolean(4, dto.isReserv());
		pstmt.setString(5,dto.getCgv());
		pstmt.setString(6,dto.getTime());
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		return result>0;
	}
	
	//UPDATE
	//DELETE
	
	//SELECTALL
	@Override
	public List<MovieDto> SelectAll() throws Exception{
		pstmt = conn.prepareStatement("select * from movie order by 영화_ID desc");
		rs =  pstmt.executeQuery();
		List<MovieDto> list = new ArrayList();
		MovieDto dto = null;
		if(rs!=null)
		{
			while(rs.next()) {
				dto = new MovieDto();
				dto.setMovieId(rs.getInt("영화_ID"));
				dto.setMovieTitle(rs.getString("영화제목"));
				dto.setMoviegenre(rs.getString("영화장르"));
				dto.setReserv(rs.getBoolean("영화예매여부"));
				dto.setCgv(rs.getString("상영장소"));
				dto.setTime(rs.getString("상영시간"));
			
				list.add(dto);
			}
		}	
		
		
		freeConnection(pstmt,rs);
		return list;
	}
	
	
	//SELECTONE
	@Override
	public MovieDto Select(int movieId) throws Exception {
		pstmt = conn.prepareStatement("select * from movie where 영화_ID=?");
		pstmt.setInt(1, movieId);
		rs =  pstmt.executeQuery();
		
		MovieDto dto = null;
		if(rs!=null)
		{
				rs.next();
				dto = new MovieDto();
				dto.setMovieId(rs.getInt("영화_ID"));
				dto.setMovieTitle(rs.getString("영화제목"));
				dto.setMoviegenre(rs.getString("영화장르"));
				dto.setReserv(rs.getBoolean("영화예매여부"));
				dto.setCgv(rs.getString("상영장소"));
				dto.setTime(rs.getString("상영시간"));		
		}	
		
		freeConnection(pstmt,rs);
		return dto;
	}
	
	//COUNT
	@Override
	public int Count() throws Exception {
		pstmt = conn.prepareStatement("select count(*) from movie");
		rs =  pstmt.executeQuery();
		
		int count = 0;
		if(rs.next())
			count = rs.getInt(1);
			
		
		freeConnection(pstmt,rs);
		return count;
	}
	
	@Override
	public int Count(Criteria criteria) throws Exception  {
		pstmt = conn.prepareStatement("select count(*) from movie where "+criteria.getType()+" like '%"+criteria.getKeyword()+"%' ");
		rs =  pstmt.executeQuery();
		
		int count = 0;
		if(rs.next())
			count = rs.getInt(1);
			
		
		freeConnection(pstmt,rs);
		return count;
	}
	
	
	@Override
	public List<MovieDto> selectAll(int offset, int amount) throws SQLException {
		pstmt = conn.prepareStatement("select * from movie order by 영화_ID desc limit ?,?");
		pstmt.setInt(1, offset);
		pstmt.setInt(2, amount);
		rs =  pstmt.executeQuery();
		List<MovieDto> list = new ArrayList();
		MovieDto dto = null;
		if(rs!=null)
		{
			while(rs.next()) {
				dto = new MovieDto();
				dto.setMovieId(rs.getInt("영화_ID"));
				dto.setMovieTitle(rs.getString("영화제목"));
				dto.setMoviegenre(rs.getString("영화장르"));
				dto.setReserv(rs.getBoolean("영화예매여부"));
				dto.setCgv(rs.getString("상영장소"));
				dto.setTime(rs.getString("상영시간"));
				list.add(dto);
			}
		}	
	
		freeConnection(pstmt,rs);
		return list;
		
	}	
	@Override
	public List<MovieDto> selectAll(int offset, int amount, Criteria criteria) throws Exception {
		pstmt = conn.prepareStatement("select * from movie where "+criteria.getType()+" like '%"+criteria.getKeyword()+"%' order by 영화_ID desc limit ?,?");
		pstmt.setInt(1, offset);
		pstmt.setInt(2, amount);
		rs =  pstmt.executeQuery();
		List<MovieDto> list = new ArrayList();
		MovieDto dto = null;
		if(rs!=null)
		{
			while(rs.next()) {
				dto = new MovieDto();
				dto.setMovieId(rs.getInt("영화_ID"));
				dto.setMovieTitle(rs.getString("영화제목"));
				dto.setMoviegenre(rs.getString("영화장르"));
				dto.setReserv(rs.getBoolean("영화예매여부"));
				dto.setCgv(rs.getString("상영장소"));
				dto.setTime(rs.getString("상영시간"));
				list.add(dto);
			}
		}	
	
		freeConnection(pstmt,rs);
		return list;
	}
	@Override
	public boolean Delete(int movieId) throws Exception{
		pstmt = conn.prepareStatement("delete from moviedb.movie where 영화_ID=?");
		pstmt.setInt(1, movieId);
		int result = pstmt.executeUpdate();
		freeConnection(pstmt,rs);
		System.out.println("삭제완료");
		return result>0;
	}
	
	public boolean Update(MovieDto dto) throws Exception{
		pstmt = conn.prepareStatement("update moviedb.movie set 영화제목=?,영화장르=?,영화예매여부=?,상영장소=?,상영시간=? where 영화_ID=?");
		pstmt.setString(1,dto.getMovieTitle());
		pstmt.setString(2,dto.getMoviegenre());
		pstmt.setBoolean(3, dto.isReserv());
		pstmt.setString(4,dto.getCgv());
		pstmt.setString(5,dto.getTime());
		pstmt.setInt(6, dto.getMovieId());
		int result = pstmt.executeUpdate();
		freeConnection(pstmt,rs);
		System.out.println("수정완료");
		return result>0;
	}
	
	
	
		
}