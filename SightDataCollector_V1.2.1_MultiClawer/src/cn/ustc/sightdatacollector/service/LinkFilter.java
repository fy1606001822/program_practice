package cn.ustc.sightdatacollector.service;  
  
//���ӿ�������Ĺ������������ж�url�Ƿ����ڱ���������Χ��  
public interface LinkFilter {  
    public boolean accept(String url);  
}  
