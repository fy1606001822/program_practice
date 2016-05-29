package cn.ustc.sightdatacollector.thread;

public class ClwerThread implements Runnable{
	private String[] seeds = null;
	
	public ClwerThread(String[] seeds){
		this.seeds = seeds;
	}
	
	public void run(){
		MyConcurrentCrawler clwer = new MyConcurrentCrawler(seeds);
		clwer.crawling();
	}

}
