package com.example.app.domain.common.service;

import java.util.List;
import java.util.Map;

import com.example.app.domain.common.dto.Criteria;
import com.example.app.domain.common.dto.MovieDto;

public interface MovieService {

	 boolean movieRegister(MovieDto dto) throws Exception;

	List<MovieDto> getAllMovies() throws Exception;

	MovieDto getMovie(int movieId) throws Exception;

	public Map<String,Object> getAllMovies(Criteria criteria)  throws Exception;
	
	void removeMovie(int movieId) throws Exception;
	
	boolean updateMovie(MovieDto dto) throws Exception;
	
}