package Test;

import cn.ustc.sightdatacollector.mainframe.MainFrame;
import cn.ustc.sightdatacollector.service.MyCrawler;

public class MainTest {

	public static void main(String[] args) {

		{

			MyCrawler crawler = new MyCrawler();
			crawler.crawling(new String[] { "http://you.ctrip.com/sight/suzhou11.html","http://www.ctrip.com","http://you.ctrip.com/place/"});
			//MainFrame mf = new MainFrame("旅游数据采集系统");
		}
	}
}


	


