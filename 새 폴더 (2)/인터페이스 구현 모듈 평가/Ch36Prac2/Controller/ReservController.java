package Ch36Prac2.Controller;

import java.util.Map;

public class ReservController implements SubController{
	// 1 Insert , 2 Update , 3 Delete 4 SelectAll 5 Select 
	@Override
	public Map<String, Object> execute(String uri,int serviceNo, Map<String, Object> params) {
		System.out.println("ReservController's execute()");
		return null;
	}

}
