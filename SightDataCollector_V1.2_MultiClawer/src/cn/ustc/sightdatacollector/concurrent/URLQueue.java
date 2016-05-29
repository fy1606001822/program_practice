package cn.ustc.sightdatacollector.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 该类用于提供可并发访问的的URL队列
 * @author fangshuseng
 *
 */
public class URLQueue {
	private static final  ConcurrentLinkedQueue<String> urlqueue = new  ConcurrentLinkedQueue();
	
	//提供一个私有的构造方法，不允许外界构造该对象
	private URLQueue(){
		
	}
	
	//向url队列中加入url
	public  static boolean add(String url){
		return urlqueue.add(url);
	}
	
	//返回url队列当前个数
	public static int  size(){
		return urlqueue.size();
	}
	
	//判断是否对垒是否为空
	public static boolean isEmpty(){
		return urlqueue.isEmpty();
	}
	
	//从队列头删除一个元素
	public static String remove(){
		return urlqueue.remove();
	}
	

}
