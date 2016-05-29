package cn.ustc.sightdatacollector.concurrent;

import java.util.concurrent.ConcurrentHashMap;

public class VisitedUrlSet {
	private static final ConcurrentHashMap<String,Object> visitedurl = new ConcurrentHashMap(); 
	private static final String  obj = new String("");
	
	private VisitedUrlSet(){}
	
	//���ϵ�ǰԪ�ظ���
	public static int size(){
		return visitedurl.size();
	}
	
	//�������Ƿ������ǰ��Ԫ��
	public static boolean contains(String url){
		return visitedurl.containsKey(url);
	}
	
	//�򼯺��м��뵱ǰ����
	public static void add(String url){
		visitedurl.put(url, obj);
	}
	
	//�ж�set�Ƿ�Ϊ��
	public static boolean isEmpty(){
		return visitedurl.isEmpty();
	}
}
