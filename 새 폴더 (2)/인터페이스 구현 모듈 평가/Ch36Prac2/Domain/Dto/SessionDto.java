package Ch36Prac2.Domain.Dto;

public class SessionDto {
	private int sessionId;
	private String id;
	private String role;
	//toString
	//getter and setter
	//생성자(디폴트,모든인자)
	@Override
	public String toString() {
		return "SessionDto [sessionId=" + sessionId + ", id=" + id + ", role=" + role + "]";
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public SessionDto(int sessionId, String id, String role) {
		super();
		this.sessionId = sessionId;
		this.id = id;
		this.role = role;
	}
	public SessionDto() {}
	
	
}
