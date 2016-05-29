package cn.ustc.sightdatacollector.service;  
  
//本接口所定义的过滤器，用于判断url是否属于本次搜索范围。  
public interface LinkFilter {  
    public boolean accept(String url);  
}  
