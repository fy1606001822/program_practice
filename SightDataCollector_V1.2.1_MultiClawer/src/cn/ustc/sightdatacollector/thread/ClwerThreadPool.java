package cn.ustc.sightdatacollector.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ClwerThreadPool {
	// ���������ɼ�������Ϣ���̳߳ǳ�
		private static ThreadPoolExecutor htmlpaserPool = (ThreadPoolExecutor) Executors
					.newCachedThreadPool();
		//���̳߳��ύһ���µ������߳�
		public static void commitClwer(String[] seeds){
			htmlpaserPool.submit(new ClwerThread(seeds));
		} 

}
