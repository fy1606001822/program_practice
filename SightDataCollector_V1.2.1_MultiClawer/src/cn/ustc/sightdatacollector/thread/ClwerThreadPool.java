package cn.ustc.sightdatacollector.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ClwerThreadPool {
	// 用于启动采集评论消息的线程城池
		private static ThreadPoolExecutor htmlpaserPool = (ThreadPoolExecutor) Executors
					.newCachedThreadPool();
		//向线程池提交一个新的任务线程
		public static void commitClwer(String[] seeds){
			htmlpaserPool.submit(new ClwerThread(seeds));
		} 

}
