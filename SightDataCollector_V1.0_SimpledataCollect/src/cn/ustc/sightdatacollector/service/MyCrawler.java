package cn.ustc.sightdatacollector.service;

import java.util.Set;

import cn.ustc.sightdatacollector.DAO.AccessDB;
import cn.ustc.sightdatacollector.datastructure.LinkQueue;
import cn.ustc.sightdatacollector.model.SightData;
import cn.ustc.sightdatacollector.model.SightURL;

/*
 * @author fangshusen
 * 锟斤拷锟斤拷锟斤拷锟节讹拷URL锟斤拷锟叫的筹拷始锟斤拷锟斤拷锟斤拷夭锟斤拷锟�
 * 
 */
public class MyCrawler {
	/**
	 * 使锟斤拷锟斤拷锟接筹拷始锟斤拷 URL 锟斤拷锟斤拷
	 * 
	 * @return
	 * @param seeds
	 *            锟斤拷锟斤拷URL
	 */
	private void initCrawlerWithSeeds(String[] seeds) {
		for (int i = 0; i < seeds.length; i++)
			LinkQueue.addUnvisitedUrl(seeds[i]);
	}

	/**
	 * 抓取锟斤拷锟�锟斤拷URL锟斤拷锟斤拷识锟金，癸拷锟剿和对凤拷锟侥匡拷锟斤拷Url锟斤拷硬锟斤拷锟�
	 * 
	 * @return
	 * @param seeds
	 */
	public void crawling(String[] seeds) { // 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟饺RL锟街凤拷锟斤拷锟斤拷锟斤拷锟叫帮拷"you.ctrip.com/sight/"锟斤拷目锟斤拷URL

		LinkFilter filter = new LinkFilter() {
			@Override
			public boolean accept(String url) {
				
				// if (url.contains("you.ctrip.com/place/") || url.contains("you.ctrip.com/sight/")) {
				if (url.contains("you.ctrip.com/sight/")) {
					return true;
				} else {
					return false;
				}
			}

		};

		// 锟斤拷始锟斤拷 URL 锟斤拷锟斤拷
		initCrawlerWithSeeds(seeds);
		int i = 0;
		int j = 0;

		SightURL surl = new SightURL();
		SightData sdata = new SightData();
		// 循锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷抓取锟斤拷锟斤拷锟接诧拷锟斤拷锟斤拷抓取锟斤拷锟斤拷页锟斤拷锟斤拷锟斤拷1000
		while (!LinkQueue.unVisitedUrlsEmpty()
				&& LinkQueue.getVisitedUrlNum() <= 1000000000) {
			// 锟斤拷头URL锟斤拷锟斤拷锟斤拷
			String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
			if (visitUrl == null)
				continue;
			// 锟斤拷锟斤拷台锟斤拷锟侥匡拷锟絬rl锟斤拷锟斤拷锟斤拷锟斤拷锟�
			if (StringMatcher.linkfilter(
					"http://you.ctrip.com/sight/(.)*/(\\d)*.html", visitUrl)) {
				System.out.println("目标url:  " + visitUrl);
				SightDetails.init(visitUrl, "utf-8");
				
				//锟芥储目锟斤拷url(锟斤拷锟斤拷)
				surl.setUrl(visitUrl);
				AccessDB.save(surl);
				
				//锟斤拷取锟斤拷锟斤拷锟斤拷锟�
				String sname = SightDetails.getSightName();
				String score = SightDetails.getSightScore();
				String sIntroduction = SightDetails.getsightIntroduction();
				sdata.setSightName(sname);
				sdata.setSightScore(score);
				sdata.setSightIntruduction(sIntroduction);
				AccessDB.save(sdata);
				
				i++; // 锟斤拷效锟斤拷url锟斤拷锟斤拷锟揭�
			}
				
			j++; //锟斤拷锟绞碉拷url锟杰革拷锟斤拷+1

			// 锟斤拷 url 锟斤拷锟诫到锟窖凤拷锟绞碉拷 URL 锟斤拷
			LinkQueue.addVisitedUrl(visitUrl);
			// 锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷页锟叫碉拷 URL
			Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);
			// 锟铰碉拷未锟斤拷锟绞碉拷 URL 锟斤拷锟�
			for (String link : links) {
				LinkQueue.addUnvisitedUrl(link);
			}
		}
		System.out.println("正确匹配的Url个数：" + i);
		System.out.println("总共抓取得url个数：" + j);
	}

}
