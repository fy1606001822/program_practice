package cn.ustc.sightdatacollector.service;

import java.util.Set;
import cn.ustc.sightdatacollector.DAO.AccessDB;
import cn.ustc.sightdatacollector.datastructure.LinkQueue;
import cn.ustc.sightdatacollector.model.SightData;
import cn.ustc.sightdatacollector.model.SightURL;
import cn.ustc.sightdatacollector.service.*;
import java.util.concurrent.*;
import cn.ustc.sightdatacollector.thread.*;

/*
 * @author fangshusen
 *
 */
public class MyCrawler {
	/**
	 * 爬虫主程序
	 * 
	 * @return
	 * @param seeds
	 */
	// 用于初始化爬虫队列的url种子
	private void initCrawlerWithSeeds(String[] seeds) {
		for (int i = 0; i < seeds.length; i++)
			LinkQueue.addUnvisitedUrl(seeds[i]);
	}

	/**
	 * 该方法主要是迭代访问队列中的url，并解析目标网页，并加入url对垒中心获取的url
	 * 
	 * @return
	 * @param seeds
	 */
	public void crawling(String[] seeds) {
		// 全局的过滤器，满足条件的将被加入url队列
		LinkFilter filter = new LinkFilter() {
			@Override
			public boolean accept(String url) {
				// if
				// (url.contains("you.ctrip.com/sight/")||StringMatcher.linkfilter(
				// "http://you.ctrip.com/place/([a-z]+)([0-9]+).html", url)) {
				if (url.contains("you.ctrip.com/sight/")) {
					return true;
				} else {
					return false;
				}
			}
		};

		// 将url种子放入url队列
		initCrawlerWithSeeds(seeds);
		int i = 0; // 计数正确匹配的的url
		int j = 0; // 计数当前过滤过的所有url


		// 迭代判断和抓取url的过程
		while (!LinkQueue.unVisitedUrlsEmpty()
				&& LinkQueue.getVisitedUrlNum() <= 10000000000L) {

			// 从url队列中取出一个url
			String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
			if (visitUrl == null)
				continue;
			// 匹配并过滤从url队列中获取的url
			if (StringMatcher
					.linkfilter(
							"http://you.ctrip.com/sight/([a-z]+)([0-9]+)/([0-9]+).html",
							visitUrl)) {
				System.out.println("目标url:  " + visitUrl);
				// SightDetails sightDetails = new SightDetails();
				// sightDetails.init(visitUrl, "utf-8");
				//
				// // 在数据库中存储目标景点的URL
				// surl.setUrl(visitUrl);
				// AccessDB.save(surl);
				//
				// // 解析并存储网页中放入景点信息
				// String sname = sightDetails.getSightName();
				// String score = sightDetails.getSightScore();
				// String sIntroduction = sightDetails.getsightIntroduction();
				// sdata.setSightName(sname);
				// sdata.setSightScore(score);
				// sdata.setSightIntruduction(sIntroduction);
				// AccessDB.save(sdata);

				// 提交一个解析网页的线程
				HtmlPaserPool.commitParser(visitUrl);

				i++; // 正确匹配到的url个数加1
			}

			j++; // 获取的总的url数目加1
			System.out.println("**********************************第" + j + "轮结束*************************************");

			// 将已经访问过的url放入已经访问过的url集合中
			LinkQueue.addVisitedUrl(visitUrl);
			// 抓取当前url所对应的页面中的url
			Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);
			// 将心抓的url放去url队列中
			for (String link : links) {
				LinkQueue.addUnvisitedUrl(link);
			}
		}
		System.out.println("正确匹配的Url个数：" + i);
		System.out.println("总共抓取得url个数：" + j);
	}

}
