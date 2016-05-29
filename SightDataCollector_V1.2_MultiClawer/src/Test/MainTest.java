package Test;

import cn.ustc.sightdatacollector.mainframe.MainFrame;
import cn.ustc.sightdatacollector.service.MyCrawler;
import cn.ustc.sightdatacollector.thread.ClwerThreadPool;

public class MainTest {

	public static void main(String[] args) {

		{
			// 单线程爬虫测试
			// MyCrawler crawler = new MyCrawler();
			// crawler.crawling(new String[] {
			// "http://you.ctrip.com/sight/suzhou11.html","http://www.ctrip.com","http://you.ctrip.com/place/"});
			// MainFrame mf = new MainFrame("旅游数据采集系统");
		}
			//多线程
		{
			ClwerThreadPool.commitClwer(new String[]{"http://you.ctrip.com/place/"});
			ClwerThreadPool.commitClwer(new String[]{"http://you.ctrip.com/sight/beijing1.html","http://you.ctrip.com/sight/shanghai2.html","http://you.ctrip.com/sight/suzhou11.html","http://you.ctrip.com/sight/hangzhou14.html"});
			// ClwerThreadPool.commitClwer(new
			// String[]{"http://you.ctrip.com/place/"});
			// ClwerThreadPool.commitClwer(new
			// String[]{"http://you.ctrip.com/place/"});
		}
	}
}
