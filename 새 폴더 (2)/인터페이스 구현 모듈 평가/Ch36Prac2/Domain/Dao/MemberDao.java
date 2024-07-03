package Ch36Prac2.Domain.Dao;

import Ch36Prac2.Domain.Dto.MemberDto;

public interface MemberDao {

	//INSERT
	boolean Insert(MemberDto dto) throws Exception;

	MemberDto Select(String id) throws Exception;

}