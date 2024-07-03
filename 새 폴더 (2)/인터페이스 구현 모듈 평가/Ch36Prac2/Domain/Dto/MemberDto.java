package Ch36Prac2.Domain.Dto;

public class MemberDto {
	
	private String id;
	private String pw;
	private String membername;
	private String address;
	private String phone_number;
	private int 포인트_보유량;
	private String role;
	@Override
	public String toString() {
		return "memberDto [id=" + id + ", pw=" + pw + ", membername=" + membername + ", address=" + address
				+ ", phone_number=" + phone_number + ", 포인트_보유량=" + 포인트_보유량 + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public int get포인트_보유량() {
		return 포인트_보유량;
	}
	public void set포인트_보유량(int 포인트_보유량) {
		this.포인트_보유량 = 포인트_보유량;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public MemberDto(String id, String pw, String membername, String address, String phone_number, int 포인트_보유량,String role) {
		super();
		this.id = id;
		this.pw = pw;
		this.membername = membername;
		this.address = address;
		this.phone_number = phone_number;
		this.포인트_보유량 = 포인트_보유량;
		this.role = role;
	}
	public MemberDto() {}
	//생성자(디폴트생성자 , 모든인자 생성자)
	//toString
	//getter and setter
	
	
}
