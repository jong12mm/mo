package Ch36Prac2.Domain.Service;

import java.util.List;
import java.util.Map;

import Ch36Prac2.Domain.Dto.MemberDto;

public interface MemberService {

	//회원가입
	boolean MemberJoin(MemberDto dto) throws Exception;

	Map<String, Object> login(String id, String password, int SessiondId) throws Exception;

	//로그아웃
	Map<String, Object> logout(int SessionId) throws Exception;



	//현재 접속중인 세션Id list 리턴
	List<Integer> getSessionIdList();

	//유저정보 가져오기
	MemberDto getUser(String id) throws Exception;

}