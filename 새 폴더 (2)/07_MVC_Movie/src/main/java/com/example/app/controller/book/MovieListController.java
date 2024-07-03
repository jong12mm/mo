package com.example.app.controller.book;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.common.ConnectionPool;
import com.example.app.domain.common.dto.Criteria;
import com.example.app.domain.common.service.MovieService;
import com.example.app.domain.common.service.MovieServiceImpl;

public class MovieListController  implements SubController{

	private MovieService movieService;
	private ConnectionPool connectionPool;
	
	public MovieListController(){
		
		try {		
			movieService = MovieServiceImpl.getInstance();
			connectionPool = ConnectionPool.getInstance();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MovieListController's execute() invoke");
		
		try {
			
			//String method = request.getMethod();
			String type = request.getParameter("type");
			String keyword = request.getParameter("keyword");
			String pageNo =  request.getParameter("pageNo");
			
			
			
			
			//1
			//1000건 -> 한페이지에 10건 -> 100페이지
			//PageNo : 1
			//Amount : 10건
			
			Criteria criteria = null;
			if(pageNo==null) {
				
				criteria = new Criteria(); // pageno=1 amount=10
			}
			else {
				
				if(type==null||keyword==null||type.isEmpty()||keyword.isEmpty()) {
					criteria = new Criteria(Integer.parseInt(pageNo),10); //pageno=1 amount=10
				}
				else {
					criteria = new Criteria(Integer.parseInt(pageNo),10,type,keyword); 
				}
			}
			
			
			//2
			
			//3
			Map<String,Object> returnValue =  movieService.getAllMovies(criteria);
			
			
			//4
			request.setAttribute("list", returnValue.get("list"));
			request.setAttribute("pageDto", returnValue.get("pageDto"));
			request.setAttribute("count", returnValue.get("count"));

			request.getRequestDispatcher("/WEB-INF/view/movie/list.jsp").forward(request, response);
							
		
			
		}catch(Exception e) {
			e.printStackTrace();
			//예외페이지로 넘기기..or new ServletException("message") 처ㅣ
		}
		
	}

}