package cn.ustc.sightdatacollector.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

/**
 * 该类用于解析目标网页页面并提取信息
 * 
 * @author fangshuseng
 * 
 */
public class SightPaser {
	private String url;
	private String html = "";

	public SightPaser(String url) {
		this.url = url;
		initparser();
	}

	private void initparser() {
		try {
			Parser parser = new Parser(
					(HttpURLConnection) (new URL(url)).openConnection());
			parser.setEncoding("utf-8"); // 设置编码格式

			// 设置景点名字过滤条件
			NodeFilter nfilter1 = new TagNameFilter("div");
			NodeFilter nfilter2 = new HasAttributeFilter("class", "f_left");
			NodeFilter nfilter = new AndFilter(nfilter1, nfilter2);

			// 设置分数过滤条件
			NodeFilter cfilter1 = new TagNameFilter("span");
			NodeFilter cfilter2 = new HasAttributeFilter("class", "score");
			NodeFilter cfilter = new AndFilter(cfilter1, cfilter2);

			// 设置景点介绍过滤条件
			NodeFilter ifilter1 = new TagNameFilter("div");
			// NodeFilter ifilter2 = new HasAttributeFilter("class",
			// "toggle_s");
			NodeFilter ifilter3 = new HasAttributeFilter("class", "toggle_l");
			// NodeFilter ifilter12 = new AndFilter(ifilter1, ifilter2);
			NodeFilter ifilter13 = new AndFilter(ifilter1, ifilter3); // 设置页面过滤条件
			NodeFilter ifilter = ifilter13;

			// 设置评论过滤条件
			NodeFilter comfilter1 = new TagNameFilter("li");
			NodeFilter comfilter2 = new HasAttributeFilter("class", "main_con");
			NodeFilter comfilter3 = new HasAttributeFilter("itemprop",
					"description");
			NodeFilter comfilter23 = new AndFilter(comfilter2, comfilter3);
			NodeFilter comfilter = new AndFilter(comfilter1, comfilter23);

			// 集成条件
			NodeFilter filter1 = new OrFilter(nfilter, cfilter);
			NodeFilter filter2 = new OrFilter(comfilter, ifilter);
			NodeFilter filter = new OrFilter(filter1, filter2);

			// 执行过滤
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
			for (int i = 0; i < nodeList.size(); i++) {
				Node node = nodeList.elementAt(i);
				html = html + node.toHtml();
			}
			System.out
					.println("******************一次完全解析的结果**********************"
							+ '\n' + html);

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	public String getSightName() {
		String sightName = null;
		try {
			Parser parser = Parser.createParser(html, "utf-8");
			NodeFilter filter1 = new TagNameFilter("div");
			NodeFilter filter2 = new HasAttributeFilter("class", "f_left");
			NodeFilter filter12 = new AndFilter(filter1, filter2); // 设置页面过滤条件
			NodeList nodeList = parser.extractAllNodesThatMatch(filter12);
			Node node = nodeList.elementAt(0);

			String thtml = node.toHtml();
			// 测试解析结果
			// System.out.println("******************************测试(名字)输出重构的网页******************************"
			// + '\n' + html);

			parser = Parser.createParser(thtml, "utf-8");
			filter1 = new TagNameFilter("a");
			nodeList = parser.extractAllNodesThatMatch(filter1);
			node = nodeList.elementAt(0);
			// 控制台测试，是否捕景点名字
			System.out.println("名字：" + node.toPlainTextString());
			// 取得景点的名字
			sightName = node.toPlainTextString();

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return sightName;

	}

	public String getSightScore() {
		String sightScore = null;
		try {
			Parser parser = Parser.createParser(html, "utf-8");
			NodeFilter filter1 = new TagNameFilter("span");
			NodeFilter filter2 = new HasAttributeFilter("class", "score");
			NodeFilter filter12 = new AndFilter(filter1, filter2); // 设置页面过滤条件
			NodeList nodeList = parser.extractAllNodesThatMatch(filter12);
			Node node = nodeList.elementAt(0);
			// 控制台测试，是否捕获经典评分所在的html语言块
			// System.out.println(node.toHtml());

			// 将解析的数据充构成html文档
			// String html = node.toHtml();
			// System.out.println("*******************************测试（景点分数）输出重构的网页：**********************"
			// + '\n' + html);

			// 控制台测试，是否捕景点名字
			System.out.println("景点分数：" + node.toPlainTextString());
			// 取得景点的评分
			sightScore = node.toPlainTextString();

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return sightScore;

	}

	public String getsightIntroduction() {
		String sightIntroduction = null;
		try {
			Parser parser = Parser.createParser(html, "utf-8");
			NodeFilter filter1 = new TagNameFilter("div");
			NodeFilter filter2 = new HasAttributeFilter("class", "toggle_s");
			NodeFilter filter3 = new HasAttributeFilter("class", "toggle_l");
			NodeFilter filter12 = new AndFilter(filter1, filter2);
			NodeFilter filter13 = new AndFilter(filter1, filter3); // 设置页面过滤条件
			NodeFilter filter = new OrFilter(filter12, filter13);
			NodeList nodeList = parser.extractAllNodesThatMatch(filter13);

			Node node = nodeList.elementAt(0);

			// 控制台输出抓取的页面
			// String html = node.toHtml();
			// System.out.println("*********************************测试（景点介绍）输出重构的网页：*********************"
			// + '\n' + html);

			sightIntroduction = node.toPlainTextString();

			// 一次替换出所得到的描述文本中的特殊字符
			sightIntroduction = sightIntroduction.replace("&ldquo", "");
			sightIntroduction = sightIntroduction.replace("&rdquo", "");
			// 去掉其中的换行符
			sightIntroduction = sightIntroduction.replace('\n', ' ');
			sightIntroduction = sightIntroduction.replace('\r', ' ');
			sightIntroduction = sightIntroduction.replace(" ", "");
			System.out.println("景点介绍：" + sightIntroduction);

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		return sightIntroduction;
	}

	public Set<String> getComments() {
		Set<String> comments = new HashSet();
		try {
			Parser parser = Parser.createParser(html, "utf-8");
			NodeFilter filter1 = new TagNameFilter("li");
			NodeFilter filter2 = new HasAttributeFilter("class", "main_con");
			NodeFilter filter3 = new HasAttributeFilter("itemprop",
					"description");

			NodeFilter filter23 = new AndFilter(filter2, filter3);
			NodeFilter filter = new AndFilter(filter1, filter23);
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);

			for (int i = 0; i < nodeList.size(); i++) {
				Node node = nodeList.elementAt(i);

				// 控制台测试输出是否正确抓取评论
				String temp = node.toPlainTextString();
				// 去掉非评论字符
				temp = temp.replace("展开更多", "");
				temp = temp.replace(" ", "");
				temp = temp.replace("\n", "");
				temp = temp.replace("\r", "");
				
				// 显示评论
				// System.out.println("评论信息：");
				if (temp != null && !temp.equals("")) {
					// System.out.println(temp);
					comments.add(temp);
				}

			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		return comments;
	}

}
