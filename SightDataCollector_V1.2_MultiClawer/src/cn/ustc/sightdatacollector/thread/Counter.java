package cn.ustc.sightdatacollector.thread;

/**
 * 用于并发过程中的全局变量计数
 * @author fangshuseng
 *
 */
public class Counter {
	public  volatile static int objUrl = 0;
	public volatile static int sumurl = 0;

}
