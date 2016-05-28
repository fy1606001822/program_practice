package cn.ustc.sightdatacollector.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class HtmlPaserPool {
	// ���������ɼ�������Ϣ���̳߳ǳ�
	private static ThreadPoolExecutor htmlpaserPool = (ThreadPoolExecutor) Executors
				.newCachedThreadPool();
	//���̳߳��ύһ���µ������߳�
	public static void commitParser(String url){
		htmlpaserPool.submit(new HtmlPaserThread(url));
	} 
	
	public static void main(String[] args) {
		String temp = "http://you.ctrip.com/sight/suzhou11/47072.html";
		
		commitParser(temp);

	}

}
