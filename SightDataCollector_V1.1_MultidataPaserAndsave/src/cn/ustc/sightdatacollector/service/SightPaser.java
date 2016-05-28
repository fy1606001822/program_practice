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
 * �������ڽ���Ŀ����ҳҳ�沢��ȡ��Ϣ
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
			parser.setEncoding("utf-8"); // ���ñ����ʽ

			// ���þ������ֹ�������
			NodeFilter nfilter1 = new TagNameFilter("div");
			NodeFilter nfilter2 = new HasAttributeFilter("class", "f_left");
			NodeFilter nfilter = new AndFilter(nfilter1, nfilter2);

			// ���÷�����������
			NodeFilter cfilter1 = new TagNameFilter("span");
			NodeFilter cfilter2 = new HasAttributeFilter("class", "score");
			NodeFilter cfilter = new AndFilter(cfilter1, cfilter2);

			// ���þ�����ܹ�������
			NodeFilter ifilter1 = new TagNameFilter("div");
			// NodeFilter ifilter2 = new HasAttributeFilter("class",
			// "toggle_s");
			NodeFilter ifilter3 = new HasAttributeFilter("class", "toggle_l");
			// NodeFilter ifilter12 = new AndFilter(ifilter1, ifilter2);
			NodeFilter ifilter13 = new AndFilter(ifilter1, ifilter3); // ����ҳ���������
			NodeFilter ifilter = ifilter13;

			// �������۹�������
			NodeFilter comfilter1 = new TagNameFilter("li");
			NodeFilter comfilter2 = new HasAttributeFilter("class", "main_con");
			NodeFilter comfilter3 = new HasAttributeFilter("itemprop",
					"description");
			NodeFilter comfilter23 = new AndFilter(comfilter2, comfilter3);
			NodeFilter comfilter = new AndFilter(comfilter1, comfilter23);

			// ��������
			NodeFilter filter1 = new OrFilter(nfilter, cfilter);
			NodeFilter filter2 = new OrFilter(comfilter, ifilter);
			NodeFilter filter = new OrFilter(filter1, filter2);

			// ִ�й���
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
			for (int i = 0; i < nodeList.size(); i++) {
				Node node = nodeList.elementAt(i);
				html = html + node.toHtml();
			}
			System.out
					.println("******************һ����ȫ�����Ľ��**********************"
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
			NodeFilter filter12 = new AndFilter(filter1, filter2); // ����ҳ���������
			NodeList nodeList = parser.extractAllNodesThatMatch(filter12);
			Node node = nodeList.elementAt(0);

			String thtml = node.toHtml();
			// ���Խ������
			// System.out.println("******************************����(����)����ع�����ҳ******************************"
			// + '\n' + html);

			parser = Parser.createParser(thtml, "utf-8");
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
			Parser parser = Parser.createParser(html, "utf-8");
			NodeFilter filter1 = new TagNameFilter("span");
			NodeFilter filter2 = new HasAttributeFilter("class", "score");
			NodeFilter filter12 = new AndFilter(filter1, filter2); // ����ҳ���������
			NodeList nodeList = parser.extractAllNodesThatMatch(filter12);
			Node node = nodeList.elementAt(0);
			// ����̨���ԣ��Ƿ񲶻񾭵��������ڵ�html���Կ�
			// System.out.println(node.toHtml());

			// �����������ݳ乹��html�ĵ�
			// String html = node.toHtml();
			// System.out.println("*******************************���ԣ��������������ع�����ҳ��**********************"
			// + '\n' + html);

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
			Parser parser = Parser.createParser(html, "utf-8");
			NodeFilter filter1 = new TagNameFilter("div");
			NodeFilter filter2 = new HasAttributeFilter("class", "toggle_s");
			NodeFilter filter3 = new HasAttributeFilter("class", "toggle_l");
			NodeFilter filter12 = new AndFilter(filter1, filter2);
			NodeFilter filter13 = new AndFilter(filter1, filter3); // ����ҳ���������
			NodeFilter filter = new OrFilter(filter12, filter13);
			NodeList nodeList = parser.extractAllNodesThatMatch(filter13);

			Node node = nodeList.elementAt(0);

			// ����̨���ץȡ��ҳ��
			// String html = node.toHtml();
			// System.out.println("*********************************���ԣ�������ܣ�����ع�����ҳ��*********************"
			// + '\n' + html);

			sightIntroduction = node.toPlainTextString();

			// һ���滻�����õ��������ı��е������ַ�
			sightIntroduction = sightIntroduction.replace("&ldquo", "");
			sightIntroduction = sightIntroduction.replace("&rdquo", "");
			// ȥ�����еĻ��з�
			sightIntroduction = sightIntroduction.replace('\n', ' ');
			sightIntroduction = sightIntroduction.replace('\r', ' ');
			sightIntroduction = sightIntroduction.replace(" ", "");
			System.out.println("������ܣ�" + sightIntroduction);

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

				// ����̨��������Ƿ���ȷץȡ����
				String temp = node.toPlainTextString();
				// ȥ���������ַ�
				temp = temp.replace("չ������", "");
				temp = temp.replace(" ", "");
				temp = temp.replace("\n", "");
				temp = temp.replace("\r", "");
				
				// ��ʾ����
				// System.out.println("������Ϣ��");
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
