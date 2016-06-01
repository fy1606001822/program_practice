package cn.ustc.sightdatacollector.service;

import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlParserTool {
	// ��ȡһ����վ�ϵ�����,filter ȫ�ֹ�����
	public static synchronized Set<String> extracLinks(String url, LinkFilter filter) {

		Set<String> links = new HashSet<String>();
		try {
			Parser parser = new Parser(url);
			parser.setEncoding("utf-8");
			
			//����Filter������<a>��ǩ  
            NodeFilter aNodeFilter = new NodeClassFilter(LinkTag.class);  
      
			NodeList list = parser.extractAllNodesThatMatch(aNodeFilter);
			for (int i = 0; i < list.size(); i++) {

				Node tag = list.elementAt(i);
				if (tag instanceof LinkTag)// <a> ��ǩ
				{
					LinkTag link = (LinkTag) tag;
					String linkUrl = link.getLink();
					//ȥ��Url�еı���ê��
					//linkUrl = StringMatcher.newlinkByfilter("http://you.ctrip.com/(.)*.html", linkUrl);
					if (filter.accept(linkUrl))
						links.add(linkUrl);
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return links;
	}
}
