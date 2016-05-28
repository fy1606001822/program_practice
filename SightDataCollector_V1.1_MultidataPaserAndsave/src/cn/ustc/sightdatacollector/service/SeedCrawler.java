package cn.ustc.sightdatacollector.service;

import java.util.Set;

public class SeedCrawler {
	
	public Set<String> seedCrawler(String rootURL){
		LinkFilter filter = new LinkFilter() {
			@Override
			public boolean accept(String url) {
				if (url.contains("you.ctrip.com/place/")) {
					return true;
				} else {
					return false;
				}
			}
		};
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
