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
			parser.setEncoding(encode); // ���ñ����ʽ
			NodeFilter filter1 = new TagNameFilter("div");
			NodeFilter filter2 = new HasAttributeFilter("class", "f_left");
			NodeFilter filter12 = new AndFilter(filter1, filter2); // ����ҳ���������
			NodeList nodeList = parser.extractAllNodesThatMatch(filter12);
			Node node = nodeList.elementAt(0);
			
			// �����������ݳ乹��html�ĵ�			
			String html = node.toHtml();
//			System.out.println("******************************����(����)����ع�����ҳ******************************" + '\n' + html);
			// ���ñ���
			parser = Parser.createParser(html, encode);
			filter1 = new TagNameFilter("a");
			nodeList = parser.extractAllNodesThatMatch(filter1);
			node = nodeList.elementAt(0);
			// ����̨���ԣ��Ƿ񲶾�������
			System.out.println("���֣�" + node.toPlainTextString());
			// ȡ�þ��������
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
			parser.setEncoding(encode); // ���ñ����ʽ
			NodeFilter filter1 = new TagNameFilter("span");
			NodeFilter filter2 = new HasAttributeFilter("class", "score");
			NodeFilter filter12 = new AndFilter(filter1, filter2); // ����ҳ���������
			NodeList nodeList = parser.extractAllNodesThatMatch(filter12);
			Node node = nodeList.elementAt(0);
			
			// �����������ݳ乹��html�ĵ�
//			String html = node.toHtml();
//			System.out.println("*******************************���ԣ��������������ع�����ҳ��**********************" + '\n' + html);
			
			// ����̨���ԣ��Ƿ񲶾�������
			System.out.println("���������" + node.toPlainTextString());
			// ȡ�þ��������
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
			parser.setEncoding(encode); // ���ñ����ʽ
			NodeFilter filter1 = new TagNameFilter("div");
			NodeFilter filter2 = new HasAttributeFilter("class", "toggle_s");
			NodeFilter filter3 = new HasAttributeFilter("class", "toggle_l");
			NodeFilter filter12 = new AndFilter(filter1, filter2);
			NodeFilter filter13 = new AndFilter(filter1, filter3); // ����ҳ���������
			NodeFilter filter = new OrFilter(filter12, filter13);
			NodeList nodeList = parser.extractAllNodesThatMatch(filter13);

			Node node = nodeList.elementAt(0);
		
			//����ץȡ����ҳ��Ϣ
//			String html = node.toHtml();
//			System.out.println("*********************************���ԣ�������ܣ�����ع�����ҳ��*********************" + '\n' + html);
			
			sightIntroduction = node.toPlainTextString();

			// һ���滻�����õ��������ı��е������ַ�
			sightIntroduction = sightIntroduction.replace("&ldquo", "");
			sightIntroduction = sightIntroduction.replace("&rdquo", "");
			// ȥ�����еĻ��з�
			sightIntroduction = sightIntroduction.replace('\n', ' ');
			sightIntroduction = sightIntroduction.replace('\r', ' ');
			sightIntroduction = sightIntroduction.replace(" ", "");
			System.out.println("������ܣ�" +  sightIntroduction);

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
			parser.setEncoding(encode); // ���ñ����ʽ
			NodeFilter filter1 = new TagNameFilter("li");
			NodeFilter filter2 = new HasAttributeFilter("class", "main_con");
			NodeFilter filter3 = new HasAttributeFilter("itemprop",
					"description");

			NodeFilter filter23 = new AndFilter(filter2, filter3);
			// NodeFilter filter13 = new AndFilter(filter1, filter3); //
			// ����ҳ���������
			NodeFilter filter = new AndFilter(filter1, filter23);
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);

			for (int i = 0; i < nodeList.size(); i++) {
				Node node = nodeList.elementAt(i);

				// ����̨��������Ƿ���ȷץȡ����
				String temp = node.toPlainTextString();
				temp = temp.replace("չ������", "");
				System.out.println(temp);
				comments.add(temp);
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		return comments;
	}
}
