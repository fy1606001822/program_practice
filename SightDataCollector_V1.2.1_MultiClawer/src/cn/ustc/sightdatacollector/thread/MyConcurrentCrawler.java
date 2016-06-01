package cn.ustc.sightdatacollector.thread;

import java.util.Set;
import cn.ustc.sightdatacollector.DAO.AccessDB;
import cn.ustc.sightdatacollector.model.SightData;
import cn.ustc.sightdatacollector.model.SightURL;
import cn.ustc.sightdatacollector.service.*;
import java.util.concurrent.*;
import cn.ustc.sightdatacollector.thread.*;
import cn.ustc.sightdatacollector.concurrent.*;

/*
 * @author fangshusen
 *
 */
public class MyConcurrentCrawler {
	/**
	 * 爬虫主程序
	 * @return
	 * @param seeds
	 */
	private String[] seeds = null;

	// 用于初始化爬虫队列的url种子
	public MyConcurrentCrawler(String[] seeds) {
		this.seeds = seeds;
		// 将url种子放入url队列
		initCrawlerWithSeeds(seeds);
	}

	private void initCrawlerWithSeeds(String[] seeds) {
		for (int i = 0; i < seeds.length; i++)
			URLQueue.add(seeds[i]);
	}

	/**
	 * 该方法主要是迭代访问队列中的url，并解析目标网页，并加入url对垒中心获取的url
	 * @return
	 * @param seeds
	 */
	public void crawling() {		
				
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

		// 迭代判断和抓取url的过程
		while (!URLQueue.isEmpty() && VisitedUrlSet.size() <= 10000000000L) {

			// 从url队列中取出一个url
			String visitUrl = URLQueue.remove();
//			if (visitUrl == null)
//				continue;
			//如果该url访问过则丢弃，继续下一轮
			if(VisitedUrlSet.contains(visitUrl) == true)
				continue;
			// 匹配并过滤从url队列中获取的url
			if (StringMatcher
					.linkfilter(
							"http://you.ctrip.com/sight/([a-z]+)([0-9]+)/([0-9]+).html",
							visitUrl)) {
				System.out.println("目标url:  " + visitUrl);

				// 提交一个解析网页的线程
				HtmlPaserPool.commitParser(visitUrl);
			}

			System.out.println("**********************************第"
					+ ++Counter.sumurl
					+ "轮结束*************************************");

			// 将已经访问过的url放入已经访问过的url集合中
			VisitedUrlSet.add(visitUrl);
			// 抓取当前url所对应的页面中的url
			Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);
			// 将心抓的url放去url队列中
			for (String link : links) {
				URLQueue.add(link);
			}
		}
		// System.out.println("正确匹配的Url个数：" + i);
		// System.out.println("总共抓取得url个数：" + j);
	}

}
