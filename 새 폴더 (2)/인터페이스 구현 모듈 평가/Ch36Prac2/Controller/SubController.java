package Ch36Prac2.Controller;

import java.util.Map;

public interface SubController {
	
	// 1 Insert , 2 Update , 3 Delete 4 SelectAll 5 Select 
	

	Map<String, Object> execute(String uri, int serviceNo, Map<String, Object> params);
	

}
