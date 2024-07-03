package Ch36Prac2.Domain.Service;

import java.util.List;

import Ch36Prac2.Domain.Dao.MovieDao;
import Ch36Prac2.Domain.Dao.MovieDaoImpl;
import Ch36Prac2.Domain.Dto.MovieDto;


public class MovieServiceImpl implements MovieService {
	
	private MovieDao dao;
	
	
	
	private static MovieService instance ;
	public static MovieService getInstance() throws Exception {
		if(instance==null)
			instance=MovieServiceImpl.getInstance();
		return instance;
	}
	
	public MovieServiceImpl() throws Exception{
		
		dao = new MovieDaoImpl();
		
	}
	
	@Override
	public boolean movieRegister(MovieDto dto) throws Exception {
		
		//autoCommit false
		
		//commit()
		return dao.Insert(dto);
	};
	@Override
	public List<MovieDto> getAllMovies() throws Exception {
		return dao.SelectAll();
	}
	@Override
	public MovieDto getMovie(int 영화_ID) throws Exception{
		return dao.Select(영화_ID);
	}

}