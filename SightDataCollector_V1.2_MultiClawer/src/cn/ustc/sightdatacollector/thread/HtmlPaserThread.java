package cn.ustc.sightdatacollector.thread;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.ustc.sightdatacollector.model.*;
import cn.ustc.sightdatacollector.service.SightDetails;
import cn.ustc.sightdatacollector.service.SightPaser;
import cn.ustc.sightdatacollector.DAO.*;

/**
 * �������ڽ���Ŀ����ҳ���������� 
 * @author fangshuseng
 */
public class HtmlPaserThread implements Runnable {
	private String url;

	public HtmlPaserThread(String url) {
		this.url = url;
	}

	@Override
	public  void run() {
		SightPaser temp = new SightPaser(url);
		String sightname = temp.getSightName();
		String core = temp.getSightScore();
		String intruduction = temp.getsightIntroduction();
		Set<String> comments = temp.getComments();

		SightData sdObj = new SightData();
		sdObj.setSightName(sightname);
		sdObj.setSightScore(core);
		sdObj.setSightIntruduction(intruduction);
		AccessDB.save(sdObj);

		// �洢url
		SightURL urlString = new SightURL();
		urlString.setUrl(url);
		urlString.setSightName(sightname);
		AccessDB.save(urlString);

		// �洢������Ϣ
		for (String s : comments) {
			SightComment tempcom = new SightComment();
			tempcom.setSighturl(url);
			tempcom.setComment(s);
			AccessDB.save(tempcom);
		}

	}

}
