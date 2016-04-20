package cn.ustc.sightdatacollector.service;

import java.net.HttpURLConnection;
import java.net.URL;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

public class SightDetails {
	private static String url = null;
	private static String encode = null;

	public static void init(String url, String encoding) {

		SightDetails.url = url;
		SightDetails.encode = encoding;
	}

	public static String getSightName() {
		String sightName = null;
		try {
			Parser parser = new Parser((HttpURLConnection) (new URL(
					SightDetails.url)).openConnection());
			parser.setEncoding(SightDetails.encode); // 设置编码格式
			NodeFilter filter1 = new TagNameFilter("div");
			NodeFilter filter2 = new HasAttributeFilter("class", "f_left");
			NodeFilter filter12 = new AndFilter(filter1, filter2); // 设置页面过滤条件
			NodeList nodeList = parser.extractAllNodesThatMatch(filter12);
			Node node = nodeList.elementAt(0);
			// 控制台测试，是否捕获经典名字所在的html语言块
			// System.out.println(node.toHtml());
			// 将解析的数据充构成html文档
			String html = node.toHtml();
			// 设置编码
			parser = Parser.createParser(html, SightDetails.encode);
			filter1 = new TagNameFilter("a");
			nodeList = parser.extractAllNodesThatMatch(filter1);
			node = nodeList.elementAt(0);
			// 控制台测试，是否捕景点名字
			System.out.println(node.toPlainTextString());
			// 取得景点的名字
			sightName = node.toPlainTextString();

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return sightName;

	}

	public static String getSightScore() {
		String sightScore = null;
		try {
			Parser parser = new Parser((HttpURLConnection) (new URL(
					SightDetails.url)).openConnection());
			parser.setEncoding(SightDetails.encode); // 设置编码格式
			NodeFilter filter1 = new TagNameFilter("span");
			NodeFilter filter2 = new HasAttributeFilter("class", "score");
			NodeFilter filter12 = new AndFilter(filter1, filter2); // 设置页面过滤条件
			NodeList nodeList = parser.extractAllNodesThatMatch(filter12);
			Node node = nodeList.elementAt(0);
			// 控制台测试，是否捕获经典评分所在的html语言块
			// System.out.println(node.toHtml());
			// 将解析的数据充构成html文档
			String html = node.toHtml();
			// 设置编码
			// parser = Parser.createParser(html, "utf-8");
			// 自定义一个Filter，用于过滤<Frame >标签，然后取得标签中的src属性值
			// NodeFilter bNodeFilter = new NodeFilter() {
			// @Override
			// public boolean accept(Node node) {
			// if (node.getText().startsWith("b")) {
			// return true;
			// } else {
			// return false;
			// }
			// }
			// };
			// filter1 = new TagNameFilter("b");
			// nodeList = parser.extractAllNodesThatMatch(filter1);
			// nodeList = parser.extractAllNodesThatMatch(bNodeFilter);
			// node = nodeList.elementAt(0);
			// String nodeText = node.toHtml();
			// int beginPosition = nodeText.indexOf("<");
			// int endPosition = nodeText.lastIndexOf("<");
			// sightScore = nodeText.substring(beginPosition,endPosition);
			//
			// 控制台测试，是否捕景点名字
			System.out.println(node.toPlainTextString());
			// 取得景点的评分
			sightScore = node.toPlainTextString();

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return sightScore;

	}

	public static String getsightIntroduction() {
		String sightIntroduction = null;
		try {
			Parser parser = new Parser((HttpURLConnection) (new URL(
					SightDetails.url)).openConnection());
			parser.setEncoding(SightDetails.encode); // 设置编码格式
			NodeFilter filter1 = new TagNameFilter("div");
			NodeFilter filter2 = new HasAttributeFilter("class", "toggle_s");
			NodeFilter filter3 = new HasAttributeFilter("class", "toggle_l");
			NodeFilter filter12 = new AndFilter(filter1, filter2);
			NodeFilter filter13 = new AndFilter(filter1, filter3); // 设置页面过滤条件
			NodeFilter filter = new OrFilter(filter12, filter13);
			NodeList nodeList = parser.extractAllNodesThatMatch(filter13);

			Node node = nodeList.elementAt(0);
			// 控制台测试，是否捕获景点简介所在的html语言块
			// System.out.println("这是第"+ i + "段html：" + node.toHtml());
			// 输出该段中包含的描述信息
			// System.out.println("这是第" + i +"段介绍：" +node.toPlainTextString());
			sightIntroduction = node.toPlainTextString();

			// 一次替换出所得到的描述文本中的特殊字符
			sightIntroduction = sightIntroduction.replace("&ldquo", "");
			sightIntroduction = sightIntroduction.replace("&rdquo", "");
			// 去掉其中的换行符
			sightIntroduction = sightIntroduction.replace('\n', ' ');
			sightIntroduction = sightIntroduction.replace('\r', ' ');
			sightIntroduction = sightIntroduction.replace(" ", "");
			System.out.println(sightIntroduction);

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		return sightIntroduction;
	}
}
