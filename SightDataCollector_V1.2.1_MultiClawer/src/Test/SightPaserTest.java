package Test;
import cn.ustc.sightdatacollector.service.*;
public class SightPaserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SightPaser st = new SightPaser("http://you.ctrip.com/sight/suzhou11/47072.html");
		System.out.println("***********************�����ͽ���*****************************");
		st.getSightName();
		st.getSightScore();
		st.getsightIntroduction();
		st.getComments();
	}

}
