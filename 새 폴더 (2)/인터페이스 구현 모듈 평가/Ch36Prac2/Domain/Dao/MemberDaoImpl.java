package Ch36Prac2.Domain.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Ch36Prac2.Domain.Dao.MemberDao;
import Ch36Prac2.Domain.Dao.MemberDaoImpl;
import Ch36Prac2.Domain.Dto.MemberDto;
import Ch36Prac2.Domain.Service.MemberService;
import Ch36Prac2.Domain.Service.MemberServiceImpl;



public class MemberDaoImpl implements MemberDao {
	
	private String url ="jdbc:mysql://localhost:3306/moviedb";
	private String id = "root";
	private String pw = "1234";
	
	private Connection conn =null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private static MemberDao instance;
	public static MemberDao getInstance() throws Exception {
		if(instance==null)
			instance= new MemberDaoImpl();
		return instance;
	}
	


	
	public MemberDaoImpl() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url,id,pw);
		System.out.println("[DAO] MemberDaoImpl's INIT DB Connected...");
	}
	
	//INSERT
	@Override
	public boolean Insert(MemberDto dto) throws Exception{
		pstmt =  conn.prepareStatement("insert into Member values(?,?,?,?,?,?,?)");
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getPw());
		pstmt.setString(3, dto.getMembername());
		pstmt.setString(4, dto.getAddress());
		pstmt.setString(5, dto.getPhone_number());
		pstmt.setInt(6, dto.get포인트_보유량());
		pstmt.setString(7, dto.getRole());
		
		
		return pstmt.executeUpdate()>0;
	}
	
	//UPDATE
	//DELETE
	//SELECTALL
	//SELECT
	
	

	@Override
	public MemberDto Select(String id) throws Exception{
		pstmt = conn.prepareStatement("select * from Member where id=?");
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		MemberDto dto = null;
		
		if(rs!=null) {
			if(rs.next()) {
				dto = new MemberDto();
				dto.setId(id);
				dto.setPw(rs.getString("pw"));
				dto.setMembername(rs.getString("Membername"));
				dto.setAddress(rs.getString("address"));
				dto.setPhone_number(rs.getString("phone_number"));
				dto.set포인트_보유량(rs.getInt("포인트_보유량"));
			}
			
		}
		return dto;	
	}



	
}
