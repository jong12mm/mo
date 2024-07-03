package Ch36Prac2;

import java.util.Map;

import Ch36Prac2.Domain.Dto.MemberDto;
import Ch36Prac2.Domain.Service.MemberServiceImpl;

public class Application {
	public static void main(String[] args) throws Exception {
		// 1
//		FrontController controller = new FrontController();
//		Map<String,Object> params = new HashMap();
//		params.put("MovieDto", new MovieDto(2219,"이것이C언어다","EASY",false,"대구","4시"));
//		Map<String,Object> result = controller.execute("/movie", 1, params);

		// 2
//		MovieDaoImpl dao = new MovieDaoImpl();
//		dao.Insert(new MovieDto(1,"웡키","스릴러",true,"경산","202020202"));
//		dao.Insert(new MovieDto(2,"웡키","스릴러",true,"경산","202020202"));
//		dao.Insert(new MovieDto(3,"웡키","스릴러",true,"경산","202020202"));
		
		// 3
//		List<MovieDto> list = dao.SelectAll();
//		list.forEach(dto->{
//			System.out.println(dto);
//		});
//		System.out.println();
//		MovieDto dto = dao.Select(2);
//		System.out.println(dto);
//		
		// 4
//		MovieServiceImpl service = new MovieServiceImpl();
//		List<MovieDto> list = service.getAllMovies();
//		list.forEach(dto->{
//			System.out.println(dto);
//		});
//		
		// 5 
//		FrontController controller = new FrontController();
//		Map<String,Object> params = new HashMap();
//		params.put("MovieDto", new MovieDto(123564,"웡키","스릴러",true,"경산","202020202"));
//		Map<String,Object> result = controller.execute("/movie", 1, params);
//		Object response = result.get("response");
		
		//06
//		MemberDaoImpl dao = new MemberDaoImpl();
//		dao.Insert(new MemberDto("user1","1234","ROLE_USER","대구","101011111",51,"역할"));
//		
//
//		//07
//		MemberServiceImpl service = new MemberServiceImpl();
//		service.MemberJoin(new MemberDto("user2","1234","ROLE_USER","대구","1010101",51,"역할"));

		
		
		//08
//		FrontController controller = new FrontController();		
//	
//		Map<String,Object> params= new HashMap();
//		params.put("memberDto",new MemberDto("user1","1234","ROLE_USER","대구","10102311111",551,"역할"));
//		controller.execute("/member", 1, params);
		
		//09
		MemberServiceImpl service = new MemberServiceImpl();
//		service.MemberJoin(new MemberDto("user5","1234","ROLE_USER","대구","101011111",51,"역할"));
//		
//		//로그인실패 : 존재하지 않는계정
//		Map<String,Object> islogin1= service.login("user6","1234", 0);
//		System.out.println("islogin1 : " + islogin1);
//		
////		//로그인실패 : 계정은 존재,패스워드 일치
//		Map<String,Object>  islogin2= service.login("user2","1235", 0);
//		System.out.println("islogin2 : " + islogin2);
////		
////		//로그인성공 : 계정은 존재하나 패스워드 일치
		Map<String,Object>  islogin3= service.login("user3","1234",0);
		System.out.println("islogin3 : " + islogin3);
		Integer mySessionId = (Integer)islogin3.get("sessionId");
		System.out.println(mySessionId);
//		
//		//로그인실패 : 기존 로그인된 계정이 존재
//		Map<String,Object>  islogin4= service.login("user2","1234",mySessionId);
//		System.out.println("islogin4 : " + islogin4);
		
//		Map<String,Object>  isLogout01= service.logout(30);
//		System.out.println("isLogout01 :" + isLogout01);
		
		
//		FrontController controller = new FrontController();
//		Map<String,Object> params = new HashMap();
//		params.put("username", "user1");
//		params.put("password", "1234");
//		params.put("sessionId", 0);
//		Map<String,Object>response =controller.execute("/user", 6, params);
//		System.out.println(response);
		
//		params.put("sessionId", 31);
//		Map<String,Object>response =controller.execute("/user", 7, params);
//		System.out.println(response);
		
		
//		FrontController controller = new FrontController();
		
	}
}
