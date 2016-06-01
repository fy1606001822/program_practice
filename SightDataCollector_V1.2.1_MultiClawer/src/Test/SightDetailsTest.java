package Test;
import cn.ustc.sightdatacollector.service.*;
public class SightDetailsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SightDetails st = new SightDetails();
		st.init("http://you.ctrip.com/sight/suzhou11/47072.html", "utf-8");
		st.getSightName();
		st.getSightScore();
		st.getsightIntroduction();
		st.getComments();
		

	}

}
