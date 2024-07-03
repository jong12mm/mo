package Ch36Prac2.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Ch36Prac2.Controller.SubController;
import Ch36Prac2.Domain.Dto.MovieDto;
import Ch36Prac2.Domain.Service.MovieServiceImpl;


public class MovieController implements SubController{
	
	private MovieServiceImpl service;
	
	public MovieController(){	
		try {
			
			service = new MovieServiceImpl();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 1 Insert , 2 Update , 3 Delete 4 SelectAll 5 Select 
	
	@Override
	public Map<String, Object> execute(String uri,int serviceNo, Map<String, Object> params) {
		System.out.println("MovieController's execute()");
		//1 파라미터 받기
		//2 입력값 검증(유효성체크(데이터) ,Validation Check)
		//3 서비스 실행
		//4 뷰페이지로 이동(or Rest Data 전달)
		
		if(serviceNo==1) 	//INSERT
		{
			
			//1
			MovieDto dto =(MovieDto)params.get("movieDto");
			System.out.println("[SC]MovieController's Insert.."+dto);
			
			//2
			if( !isValid(dto) ) 
				return null;
			
			
			//3 서비스 실행
			boolean isRegistred=false;
			try {		
				isRegistred =  service.movieRegister(dto);		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//4 뷰로 전달 or 이동
			Map<String,Object> result = new HashMap();
			result.put("response", isRegistred);
			
		}
		else if(serviceNo==2) //UPDATE
		{
			System.out.println("");
		}
		else if(serviceNo==3) //DELETE
		{
			System.out.println("");
		}
		else if(serviceNo==4) //SELECTALL
		{
			System.out.println("");
			//파라미터
			//유효성
			//서비스
			List<MovieDto> list =null;
			try {
			
				list =   service.getAllMovies();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//뷰
			Map<String,Object> response = new HashMap();
			if(list!=null) {
				response.put("response", true);
				response.put("msg", "조회 성공 list : " + list);
				response.put("list", list);
			}else {
				response.put("msg", "조회 실패");
				response.put("response", false);
			}
			return response;
		}
		else if(serviceNo==5) //SELECTONE
		{
			System.out.println("");
			//파라미터
			//유효성
			//서비스
			Integer MovieCode = (Integer) params.get("MovieCode");
			MovieDto dto = null;
			try {
				if(MovieCode!=null)
					dto =service.getMovie(MovieCode);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//뷰
			Map<String,Object> response = new HashMap();
			if(dto!=null) {
				response.put("response", true);
				response.put("msg", "조회 성공 MovieDto : " + dto);
				response.put("MovieDto", dto);
			}else {
				response.put("response", false);
				response.put("msg", "조회 실패");
			}
			return response;
		}
		else
		{
			System.out.println("");
		}

		
	
		return null;
	}

	
	private boolean isValid(MovieDto dto) {
		// Null체크
		// trim제거
		
		return true;
	}
	
}