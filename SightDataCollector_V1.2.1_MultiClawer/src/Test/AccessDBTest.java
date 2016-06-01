package Test;

import cn.ustc.sightdatacollector.DAO.AccessDB;
import cn.ustc.sightdatacollector.model.SightData;

public class AccessDBTest {

	public static void main(String[] args) {
		SightData s = new SightData();
		for(int i = 0;i<10;i++){
			s.setSightName("name " +i );
			s.setSightScore("" + i);
			s.setSightIntruduction(i+ "indru" );
			AccessDB.save(s);
		}

	}

}
