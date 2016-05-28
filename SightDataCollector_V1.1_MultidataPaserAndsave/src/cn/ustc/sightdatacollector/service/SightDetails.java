package cn.ustc.sightdatacollector.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

public class SightDetails {
	private String url = null;
	private String encode = null;

	public void init(String url, String encoding) {

		this.url = url;
		this.encode = encoding;
	}

	public String getSightName() {
		String sightName = null;
		try {
			Parser parser = new Parser(
					(HttpURLConnection) (new URL(url)).openConnection());
			parser.setEncoding(encode); // 设置编码格式
			NodeFilter filter1 = new TagNameFilter("div");
			NodeFilter filter2 = new HasAttributeFilter("class", "f_left");
			NodeFilter filter12 = new AndFilter(filter1, filter2); // 设置页面过滤条件
			NodeList nodeList = parser.extractAllNodesThatMatch(filter12);
			Node node = nodeList.elementAt(0);
			
			// 将解析的数据充构成html文档			
			String html = node.toHtml();
//			System.out.println("******************************测试(名字)输出重构的网页******************************" + '\n' + html);
			// 设置编码
			parser = Parser.createParser(html, encode);
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
			Parser parser = new Parser(
					(HttpURLConnection) (new URL(url)).openConnection());
			parser.setEncoding(encode); // 设置编码格式
			NodeFilter filter1 = new TagNameFilter("span");
			NodeFilter filter2 = new HasAttributeFilter("class", "score");
			NodeFilter filter12 = new AndFilter(filter1, filter2); // 设置页面过滤条件
			NodeList nodeList = parser.extractAllNodesThatMatch(filter12);
			Node node = nodeList.elementAt(0);
			
			// 将解析的数据充构成html文档
//			String html = node.toHtml();
//			System.out.println("*******************************测试（景点分数）输出重构的网页：**********************" + '\n' + html);
			
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
			Parser parser = new Parser(
					(HttpURLConnection) (new URL(url)).openConnection());
			parser.setEncoding(encode); // 设置编码格式
			NodeFilter filter1 = new TagNameFilter("div");
			NodeFilter filter2 = new HasAttributeFilter("class", "toggle_s");
			NodeFilter filter3 = new HasAttributeFilter("class", "toggle_l");
			NodeFilter filter12 = new AndFilter(filter1, filter2);
			NodeFilter filter13 = new AndFilter(filter1, filter3); // 设置页面过滤条件
			NodeFilter filter = new OrFilter(filter12, filter13);
			NodeList nodeList = parser.extractAllNodesThatMatch(filter13);

			Node node = nodeList.elementAt(0);
		
			//测试抓取的网页信息
//			String html = node.toHtml();
//			System.out.println("*********************************测试（景点介绍）输出重构的网页：*********************" + '\n' + html);
			
			sightIntroduction = node.toPlainTextString();

			// 一次替换出所得到的描述文本中的特殊字符
			sightIntroduction = sightIntroduction.replace("&ldquo", "");
			sightIntroduction = sightIntroduction.replace("&rdquo", "");
			// 去掉其中的换行符
			sightIntroduction = sightIntroduction.replace('\n', ' ');
			sightIntroduction = sightIntroduction.replace('\r', ' ');
			sightIntroduction = sightIntroduction.replace(" ", "");
			System.out.println("景点介绍：" +  sightIntroduction);

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		return sightIntroduction;
	}

	public Set<String> getComments() {
		Set<String> comments = new HashSet();
		try {
			Parser parser = new Parser(
					(HttpURLConnection) (new URL(url)).openConnection());
			parser.setEncoding(encode); // 设置编码格式
			NodeFilter filter1 = new TagNameFilter("li");
			NodeFilter filter2 = new HasAttributeFilter("class", "main_con");
			NodeFilter filter3 = new HasAttributeFilter("itemprop",
					"description");

			NodeFilter filter23 = new AndFilter(filter2, filter3);
			// NodeFilter filter13 = new AndFilter(filter1, filter3); //
			// 设置页面过滤条件
			NodeFilter filter = new AndFilter(filter1, filter23);
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);

			for (int i = 0; i < nodeList.size(); i++) {
				Node node = nodeList.elementAt(i);

				// 控制台测试输出是否正确抓取评论
				String temp = node.toPlainTextString();
				temp = temp.replace("展开更多", "");
				System.out.println(temp);
				comments.add(temp);
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		return comments;
	}
}
