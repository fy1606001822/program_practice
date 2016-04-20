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
			parser.setEncoding(SightDetails.encode); // ���ñ����ʽ
			NodeFilter filter1 = new TagNameFilter("div");
			NodeFilter filter2 = new HasAttributeFilter("class", "f_left");
			NodeFilter filter12 = new AndFilter(filter1, filter2); // ����ҳ���������
			NodeList nodeList = parser.extractAllNodesThatMatch(filter12);
			Node node = nodeList.elementAt(0);
			// ����̨���ԣ��Ƿ񲶻񾭵��������ڵ�html���Կ�
			// System.out.println(node.toHtml());
			// �����������ݳ乹��html�ĵ�
			String html = node.toHtml();
			// ���ñ���
			parser = Parser.createParser(html, SightDetails.encode);
			filter1 = new TagNameFilter("a");
			nodeList = parser.extractAllNodesThatMatch(filter1);
			node = nodeList.elementAt(0);
			// ����̨���ԣ��Ƿ񲶾�������
			System.out.println(node.toPlainTextString());
			// ȡ�þ��������
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
			parser.setEncoding(SightDetails.encode); // ���ñ����ʽ
			NodeFilter filter1 = new TagNameFilter("span");
			NodeFilter filter2 = new HasAttributeFilter("class", "score");
			NodeFilter filter12 = new AndFilter(filter1, filter2); // ����ҳ���������
			NodeList nodeList = parser.extractAllNodesThatMatch(filter12);
			Node node = nodeList.elementAt(0);
			// ����̨���ԣ��Ƿ񲶻񾭵��������ڵ�html���Կ�
			// System.out.println(node.toHtml());
			// �����������ݳ乹��html�ĵ�
			String html = node.toHtml();
			// ���ñ���
			// parser = Parser.createParser(html, "utf-8");
			// �Զ���һ��Filter�����ڹ���<Frame >��ǩ��Ȼ��ȡ�ñ�ǩ�е�src����ֵ
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
			// ����̨���ԣ��Ƿ񲶾�������
			System.out.println(node.toPlainTextString());
			// ȡ�þ��������
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
			parser.setEncoding(SightDetails.encode); // ���ñ����ʽ
			NodeFilter filter1 = new TagNameFilter("div");
			NodeFilter filter2 = new HasAttributeFilter("class", "toggle_s");
			NodeFilter filter3 = new HasAttributeFilter("class", "toggle_l");
			NodeFilter filter12 = new AndFilter(filter1, filter2);
			NodeFilter filter13 = new AndFilter(filter1, filter3); // ����ҳ���������
			NodeFilter filter = new OrFilter(filter12, filter13);
			NodeList nodeList = parser.extractAllNodesThatMatch(filter13);

			Node node = nodeList.elementAt(0);
			// ����̨���ԣ��Ƿ񲶻񾰵������ڵ�html���Կ�
			// System.out.println("���ǵ�"+ i + "��html��" + node.toHtml());
			// ����ö��а�����������Ϣ
			// System.out.println("���ǵ�" + i +"�ν��ܣ�" +node.toPlainTextString());
			sightIntroduction = node.toPlainTextString();

			// һ���滻�����õ��������ı��е������ַ�
			sightIntroduction = sightIntroduction.replace("&ldquo", "");
			sightIntroduction = sightIntroduction.replace("&rdquo", "");
			// ȥ�����еĻ��з�
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
