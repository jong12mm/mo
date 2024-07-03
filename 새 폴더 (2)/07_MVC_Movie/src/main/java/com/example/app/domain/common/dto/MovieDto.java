package com.example.app.domain.common.dto;

import java.io.Serializable;

public class MovieDto  implements Serializable{
	
	private int movieId;
	private String movieTitle;
	private String moviegenre;
	private boolean reserv;
	private String cgv;
	private String time;
	
	
	public MovieDto(int movieId, String movieTitle, String moviegenre, boolean reserv, String cgv, String time) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.moviegenre = moviegenre;
		this.reserv = reserv;
		this.cgv = cgv;
		this.time = time;
	}


	@Override
	public String toString() {
		return "MovieDto [movieId=" + movieId + ", movieTitle=" + movieTitle + ", moviegenre=" + moviegenre
				+ ", reserv=" + reserv + ", cgv=" + cgv + ", time=" + time + "]";
	}


	public int getMovieId() {
		return movieId;
	}


	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}


	public String getMovieTitle() {
		return movieTitle;
	}


	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}


	public String getMoviegenre() {
		return moviegenre;
	}


	public void setMoviegenre(String moviegenre) {
		this.moviegenre = moviegenre;
	}


	public boolean isReserv() {
		return reserv;
	}


	public void setReserv(boolean reserv) {
		this.reserv = reserv;
	}


	public String getCgv() {
		return cgv;
	}


	public void setCgv(String cgv) {
		this.cgv = cgv;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public MovieDto() {}
	
	//toString
	//getter and Setter
	//디폴트생성자 / 모든인자 생성자 추가하기
	

}