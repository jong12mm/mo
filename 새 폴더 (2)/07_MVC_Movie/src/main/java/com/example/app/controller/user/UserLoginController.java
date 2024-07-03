package com.example.app.controller.user;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.common.ConnectionPool;
import com.example.app.domain.common.service.UserService;
import com.example.app.domain.common.service.UserServiceImpl;

public class UserLoginController implements SubController{

	private UserService userService;
	private ConnectionPool connectionPool;
	
	public UserLoginController(){
		try 
		{
			userService = UserServiceImpl.getInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("UserLoginController execute : ");
		
		
		String method = request.getMethod();
		if(method.contains("GET")) {
			try {
				request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
				return ;
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//1
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//2
		if(!isValid(username,password)) {
			return ;
		}
		
		//3
		Map<String,Object> returnValue = null;
		HttpSession session = request.getSession();
		try {
			returnValue = userService.login(username, password, session,response);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		//4
		boolean isLogined = (boolean) returnValue.get("response");
		String msg = (String) returnValue.get("msg");
		session.setAttribute("msg",msg);
		
		if(isLogined)	//로그인성공 
		{
			
			try {
				response.sendRedirect(request.getContextPath());
				return ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else 
		{
	
			try {
				
				request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
				return ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}

		
	}
	
	private boolean isValid(String username, String password) {
		// TODO Auto-generated method stub
		return true;
	}

}
