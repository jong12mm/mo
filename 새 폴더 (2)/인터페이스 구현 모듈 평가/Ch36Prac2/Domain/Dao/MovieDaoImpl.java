package Ch36Prac2.Domain.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Ch36Prac2.Domain.Dao.CommonDao;
import Ch36Prac2.Domain.Dto.MovieDto;
import Ch36Prac2.Domain.Service.MovieService;
import Ch36Prac2.Domain.Service.MovieServiceImpl;

public class MovieDaoImpl extends CommonDao implements MovieDao{

	
	private static MovieService instance ;
	public static MovieService getInstance() throws Exception {
		if(instance==null)
			instance= MovieServiceImpl.getInstance();
		return instance;
	}
	
	public MovieDaoImpl() throws Exception{

		
		
		System.out.println("[DAO] MovieDaoImpl's INIT DB Connected...");
	}
	//INSERT
	public boolean Insert(MovieDto dto) throws Exception{
		pstmt = conn.prepareStatement("insert into Movie values(?,?,?,?,?,?)");
		pstmt.setInt(1, dto.get영화_ID());
		pstmt.setString(2, dto.get영화제목());
		pstmt.setString(3, dto.get영화장르());
		pstmt.setBoolean(4, true);
		pstmt.setString(5, dto.get상영장소());
		pstmt.setString(6, dto.get상영시간());
		int result = pstmt.executeUpdate();
		
		pstmt.close();
		return result>0;
	}
	
	//UPDATE
	//DELETE
	
	//SELECTALL
	public List<MovieDto> SelectAll() throws Exception{
		pstmt = conn.prepareStatement("select * from movie");
		rs =  pstmt.executeQuery();
		List<MovieDto> list = new ArrayList();
		MovieDto dto = null;
		if(rs!=null)
		{
			while(rs.next()) {
				dto = new MovieDto();
				dto.set영화_ID(rs.getInt("영화_ID"));
				dto.set영화제목(rs.getString("영화제목"));
				dto.set영화장르(rs.getString("영화장르"));
				dto.set영화예매여부(rs.getBoolean("영화예매여부"));
				dto.set상영장소(rs.getString("상영장소"));
				dto.set상영시간(rs.getString("상영시간"));
				list.add(dto);
			}
		}	
		rs.close();
		pstmt.close();
		return list;
	}
	
	
	//SELECTONE
	public MovieDto Select(int MovieCode) throws Exception {
		pstmt = conn.prepareStatement("select * from Movie where 영화_ID=?");
		pstmt.setInt(1, MovieCode);
		rs =  pstmt.executeQuery();
		
		MovieDto dto = null;
		if(rs!=null)
		{
				rs.next();
				dto = new MovieDto();
				dto.set영화_ID(rs.getInt("영화_ID"));
				dto.set영화제목(rs.getString("영화제목"));
				dto.set영화장르(rs.getString("영화장르"));
				dto.set영화예매여부(rs.getBoolean("영화예매여부"));
				dto.set상영장소(rs.getString("상영장소"));
				dto.set상영시간(rs.getString("상영시간"));	
		}	
		rs.close();
		pstmt.close();
		return dto;
	}
	
	
	
		
}