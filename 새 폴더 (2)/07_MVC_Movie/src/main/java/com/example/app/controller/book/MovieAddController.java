package com.example.app.controller.book;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.common.ConnectionPool;
import com.example.app.domain.common.dto.MovieDto;
import com.example.app.domain.common.service.MovieService;
import com.example.app.domain.common.service.MovieServiceImpl;

public class MovieAddController implements SubController{

	private MovieService movieService;
	private ConnectionPool connectionPool;
	
	public MovieAddController(){
		
		try {
			
			movieService = MovieServiceImpl.getInstance();
			connectionPool = ConnectionPool.getInstance();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MovieAddController's execute() invoke ");
		
		try {
			
			//GET 요청
			String method = request.getMethod();
			if(method.contains("GET")) {
				request.getRequestDispatcher("/WEB-INF/view/movie/add.jsp").forward(request, response);
				return ;
			}
			
			//POST 요청(etc Method)
			//1
			String movieId = request.getParameter("movieId");
			String movieTitle = request.getParameter("movieTitle");
			String moviegenre = request.getParameter("moviegenre");
			String reserv = request.getParameter("reserv");
			String cgv = request.getParameter("cgv");
			String time = request.getParameter("time");
			
			//2
			if(!isValid(movieId,movieTitle,moviegenre,reserv,cgv,time)) {		
				
				return ;
			}
			
			//3
			MovieDto movieDto = new MovieDto(Integer.parseInt(movieId),movieTitle,moviegenre,Boolean.parseBoolean(reserv),cgv,time);
			
			boolean isadded = movieService.movieRegister(movieDto);
				
			//4
			if(isadded) {
				
				response.sendRedirect(request.getContextPath()+ "/movie/list"); 
				
			}else {
				
				request.getRequestDispatcher("/WEB-INF/view/movie/add.jsp").forward(request, response);
				
			}
		
			
		}catch(Exception e) {
			e.printStackTrace();
			//예외페이지로 넘기기..or new ServletException("message") 처ㅣ
			
			try {
				connectionPool.txRollBack();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}

	private boolean isValid(String movieId, String movieTitle, String moviegenre, String reserv, String cgv, String time) {
		if(movieId==null) {
			return false;
		}
		if(movieId.isEmpty()) {
			return false;
		}
		if(movieTitle==null || movieTitle.isEmpty())
			return false;
		if(moviegenre==null || moviegenre.isEmpty())
			return false;
		if(reserv==null || reserv.isEmpty())
			return false;
		if(cgv==null || cgv.isEmpty())
			return false;
		if(time==null || time.isEmpty())
			return false;
		if(Boolean.parseBoolean(reserv)!=true && Boolean.parseBoolean(reserv)!= false)
			return false;
		
		return true;
	}
	

}
