package Ch36Prac2.Domain.Service;

import java.util.List;

import Ch36Prac2.Domain.Dto.MovieDto;

public interface MovieService {

	boolean movieRegister(MovieDto dto) throws Exception;

	List<MovieDto> getAllMovies() throws Exception;

	MovieDto getMovie(int 영화_ID) throws Exception;

}