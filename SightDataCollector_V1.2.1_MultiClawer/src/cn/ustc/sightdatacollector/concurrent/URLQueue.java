package cn.ustc.sightdatacollector.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ���������ṩ�ɲ������ʵĵ�URL����
 * @author fangshuseng
 *
 */
public class URLQueue {
	private static final  ConcurrentLinkedQueue<String> urlqueue = new  ConcurrentLinkedQueue();
	
	//�ṩһ��˽�еĹ��췽������������繹��ö���
	private URLQueue(){
		
	}
	
	//��url�����м���url
	public  static boolean add(String url){
		return urlqueue.add(url);
	}
	
	//����url���е�ǰ����
	public static int  size(){
		return urlqueue.size();
	}
	
	//�ж��Ƿ�����Ƿ�Ϊ��
	public static boolean isEmpty(){
		return urlqueue.isEmpty();
	}
	
	//�Ӷ���ͷɾ��һ��Ԫ��
	public static String remove(){
		return urlqueue.remove();
	}
	

}
