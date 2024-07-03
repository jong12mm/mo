package com.example.app.domain.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.app.domain.common.dto.Criteria;
import com.example.app.domain.common.dto.MovieDto;

public interface MovieDao {

	//INSERT
	boolean Insert(MovieDto dto) throws Exception;

	//SELECTALL
	List<MovieDto> SelectAll() throws Exception;

	//SELECTONE
	MovieDto Select(int movieId) throws Exception;

	int Count() throws Exception;

	List<MovieDto> selectAll(int offset, int amount) throws SQLException;

	int Count(Criteria criteria) throws Exception;

	List<MovieDto> selectAll(int offset, int amount, Criteria criteria) throws Exception;

	boolean Delete(int movieId) throws Exception;
	
	boolean Update(MovieDto dto) throws Exception;
}