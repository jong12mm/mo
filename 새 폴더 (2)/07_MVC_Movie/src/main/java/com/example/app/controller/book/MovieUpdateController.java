package com.example.app.controller.book;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.common.ConnectionPool;
import com.example.app.domain.common.dto.Criteria;
import com.example.app.domain.common.dto.MovieDto;
import com.example.app.domain.common.service.MovieService;
import com.example.app.domain.common.service.MovieServiceImpl;

public class MovieUpdateController  implements SubController{

	private MovieService movieService;
	private ConnectionPool connectionPool;
	
	public MovieUpdateController() {
		try {
			movieService = MovieServiceImpl.getInstance();
			connectionPool = ConnectionPool.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MovieUpdateController's execute() invoke");		

		try {
			
			
			String method =  request.getMethod();
			if(method.contains("GET")) {
				request.getRequestDispatcher("/WEB-INF/view/movie/update.jsp").forward(request, response);
				
			}
			
			
			
			//String method = request.getMethod();
			String updateIdStr = request.getParameter("movieId");
			String movieTitle = request.getParameter("movieTitle");
			String moviegenre = request.getParameter("moviegenre");
			String reserv = request.getParameter("reserv");
			String cgv = request.getParameter("cgv");
			String time = request.getParameter("time");
			
			

			
			
			if(!isValid(updateIdStr,movieTitle,moviegenre,reserv,cgv,time)) {		
				
				return ;
			}
			
			if (updateIdStr != null && !updateIdStr.isEmpty()) {
				int updateId = Integer.parseInt(updateIdStr);
			
	            MovieDto moviedto = new MovieDto(updateId,movieTitle,moviegenre,Boolean.parseBoolean(reserv),cgv,time);
		
			//1
			//1000건 -> 한페이지에 10건 -> 100페이지
			//PageNo : 1
			//Amount : 10건
			
			
			boolean isupdated = movieService.updateMovie(moviedto);

			//2
			
			//3
			
			
			//4


			if(isupdated) {
				response.sendRedirect(request.getContextPath()+ "/movie/delete"); 
				
			}else {
				
				request.getRequestDispatcher("/WEB-INF/view/movie/update.jsp").forward(request, response);
				
			}
							
		}	
			
		}catch(Exception e) {
			e.printStackTrace();
			//예외페이지로 넘기기..or new ServletException("message") 처ㅣ
		}
     
		
	}


	private boolean isValid(String updateIdStr, String movieTitle, String moviegenre, String reserv, String cgv,String time) {
		if(updateIdStr==null) {
			return false;
		}
		if(updateIdStr.isEmpty()) {
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
