package Ch36Prac2.Domain.Dao;

import java.util.List;


import Ch36Prac2.Domain.Dto.MovieDto;

public interface MovieDao {

	//INSERT
	boolean Insert(MovieDto dto) throws Exception;

	//SELECTALL
	List<MovieDto> SelectAll() throws Exception;

	//SELECTONE
	MovieDto Select(int 영화_ID) throws Exception;

}