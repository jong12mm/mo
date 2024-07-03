package Ch36Prac2.Domain.Dao;

import java.util.ArrayList;
import java.util.List;

import Ch36Prac2.Domain.Dto.SessionDto;

public class SessionDaoImpl extends CommonDao implements  SessionDao{
	
	
	private static SessionDao instance;
	
	public static SessionDao getInstance() throws Exception {
		if(instance==null)
			instance=new SessionDaoImpl();
		return instance;
	}
	
	public SessionDaoImpl() throws Exception{
		System.out.println("[DAO] SessionDaoImpl's INIT" + conn);
	}
	
	//SESSION용
	@Override
	public boolean Insert(SessionDto sessionDto) throws Exception {
		pstmt =  conn.prepareStatement("insert into session values(null,?,?)");
		pstmt.setString(1, sessionDto.getId());
		pstmt.setString(2, sessionDto.getRole());
		int result = pstmt.executeUpdate();
		freeConnection(pstmt);
		return result>0;
	}
	
	@Override
	public SessionDto Select(int sessiondId) throws Exception {
		pstmt = conn.prepareStatement("select * from session where sessionId=?");
		pstmt.setInt(1,sessiondId);
		rs=pstmt.executeQuery();
		SessionDto dto=null;
		if(rs!=null) {
			rs.next();
			dto=new SessionDto();
			dto.setSessionId(rs.getInt("sessionId"));
			dto.setId(rs.getString("id"));
			dto.setRole(rs.getString("role"));
		}
		freeConnection(pstmt,rs);
		return dto;
	}
	@Override
	public SessionDto Select(String id) throws Exception {
		pstmt = conn.prepareStatement("select * from session where id=?");
		pstmt.setString(1,id);
		rs=pstmt.executeQuery();
		SessionDto dto=null;
		if(rs!=null) {
			rs.next();
			dto=new SessionDto();
			dto.setSessionId(rs.getInt("sessionId"));
			dto.setId(rs.getString("id"));
			dto.setRole(rs.getString("role"));
		}
		freeConnection(pstmt,rs);
		return dto;
	}

	@Override
	public boolean Delete(int sessionId) throws Exception {
		pstmt = conn.prepareStatement("delete from session where sessionId=?");
		pstmt.setInt(1, sessionId);
		int result = pstmt.executeUpdate();
		freeConnection(pstmt);
		return  result>0;
	}
	// SelectAll
	@Override
	public List<SessionDto> SelectAll() throws Exception {
		pstmt=conn.prepareStatement("select * from session");
		rs = pstmt.executeQuery();
		List<SessionDto> list = new ArrayList();
		SessionDto dto = null;
		if(rs!= null) {
			while (rs.next()) {
				dto = new SessionDto();
				dto.setSessionId(rs.getInt("sessionId"));
				dto.setId(rs.getString("id"));
				dto.setRole(rs.getString("role"));
				list.add(dto);
			}
		}
		freeConnection(pstmt,rs);
		return list;
	}	
	
	
	
}
