package Ch36Prac2.Domain.Dao;

import java.util.List;

import Ch36Prac2.Domain.Dto.SessionDto;

public interface SessionDao {

	boolean Insert(SessionDto sessionDto) throws Exception;

	SessionDto Select(int sessiondId) throws Exception;

	SessionDto Select(String id) throws Exception;

	boolean Delete(int sessionId) throws Exception;

	//SELECTALL
	List<SessionDto> SelectAll() throws Exception;
}