package Ch36Prac2.Domain.Dto;

public class MovieDto {
	private int 영화_ID;
	private String 영화제목;
	private String 영화장르;
	private Boolean 영화예매여부;
	private String 상영장소;
	private String 상영시간;
	
	
	
	
	@Override
	public String toString() {
		return "MovieDto [영화_ID=" + 영화_ID + ", 영화제목=" + 영화제목 + ", 영화장르=" + 영화장르 + ", 영화예매여부=" + 영화예매여부 + ", 상영장소="
				+ 상영장소 + ", 상영시간=" + 상영시간 + "]";
	}
	public int get영화_ID() {
		return 영화_ID;
	}
	public void set영화_ID(int 영화_ID) {
		this.영화_ID = 영화_ID;
	}
	public String get영화제목() {
		return 영화제목;
	}
	public void set영화제목(String 영화제목) {
		this.영화제목 = 영화제목;
	}
	public String get영화장르() {
		return 영화장르;
	}
	public void set영화장르(String 영화장르) {
		this.영화장르 = 영화장르;
	}
	public Boolean get영화예매여부() {
		return 영화예매여부;
	}
	public void set영화예매여부(Boolean 영화예매여부) {
		this.영화예매여부 = 영화예매여부;
	}
	public String get상영장소() {
		return 상영장소;
	}
	public void set상영장소(String 상영장소) {
		this.상영장소 = 상영장소;
	}
	public String get상영시간() {
		return 상영시간;
	}
	public void set상영시간(String 상영시간) {
		this.상영시간 = 상영시간;
	}
	

	public MovieDto(int 영화_ID, String 영화제목, String 영화장르, boolean 영화예매여부, String 상영장소, String 상영시간) {
		super();
		this.영화_ID = 영화_ID;
		this.영화제목 = 영화제목;
		this.영화장르 = 영화장르;
		this.영화예매여부 = 영화예매여부;
		this.상영장소 = 상영장소;
		this.상영시간 = 상영시간;
	}
	public MovieDto() {
		
	}


	
	//toString
	//getter and Setter
	//디폴트생성자 / 모든인자 생성자 추가하기
	

}