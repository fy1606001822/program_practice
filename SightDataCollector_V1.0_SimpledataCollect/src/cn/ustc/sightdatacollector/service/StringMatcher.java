package cn.ustc.sightdatacollector.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatcher {
	
	public static boolean linkfilter(String regex, String url){
		boolean flag = false;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);
		if(matcher.matches()){
			flag = true;
		}
		return flag;
	}
	//����ƥ�������������ַ���
	public static String newlinkByfilter(String regex, String url) {

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);
		boolean flag = matcher.find();
		System.out.println(flag);
		String newUrl = matcher.group();
		return newUrl;
	}

}
