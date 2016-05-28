package cn.ustc.sightdatacollector.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class HtmlPaserPool {
	// 用于启动采集评论消息的线程城池
	private static ThreadPoolExecutor htmlpaserPool = (ThreadPoolExecutor) Executors
				.newCachedThreadPool();
	//向线程池提交一个新的任务线程
	public static void commitParser(String url){
		htmlpaserPool.submit(new HtmlPaserThread(url));
	} 
	
	public static void main(String[] args) {
		String temp = "http://you.ctrip.com/sight/suzhou11/47072.html";
		
		commitParser(temp);

	}

}
