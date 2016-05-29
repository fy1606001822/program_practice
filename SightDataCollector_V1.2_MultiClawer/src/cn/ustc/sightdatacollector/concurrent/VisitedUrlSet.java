package cn.ustc.sightdatacollector.concurrent;

import java.util.concurrent.ConcurrentHashMap;

public class VisitedUrlSet {
	private static final ConcurrentHashMap<String,Object> visitedurl = new ConcurrentHashMap(); 
	private static final String  obj = new String("");
	
	private VisitedUrlSet(){}
	
	//集合当前元素个数
	public static int size(){
		return visitedurl.size();
	}
	
	//集合中是否包含当前的元素
	public static boolean contains(String url){
		return visitedurl.containsKey(url);
	}
	
	//向集合中加入当前对象
	public static void add(String url){
		visitedurl.put(url, obj);
	}
	
	//判断set是否为空
	public static boolean isEmpty(){
		return visitedurl.isEmpty();
	}
}
