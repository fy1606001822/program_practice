package cn.ustc.sightdatacollector.mainframe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;

/**
 * 该类用于显示系统主界面
 * @author fangshuseng
 */
import javax.swing.*;

public class MainFrame {

	private  JFrame mframe = null;
	private  String mfName = null;
	
	public MainFrame(String mfName){
		this.mfName = mfName;
		this.mframe = createMainFrame(mfName);
	}
	
	public  JFrame createMainFrame(String mfName){
		JFrame frame  = new JFrame(mfName);
		Container cont = frame.getContentPane();
		
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		
		//向左边的panel中添加组件
		GridLayout leftLay = new GridLayout(4,1,0,30);
		left.setLayout(leftLay);
		JButton  start = new JButton("开始抓取");
		//下面是定义数据仓库面板
		JPanel  dbbase = new JPanel();
		dbbase.setLayout(new BorderLayout(10,10));
		JButton sightdetal = new JButton("景点详情");
		JButton url = new JButton("url库");
		JButton comment = new JButton("评论");
		dbbase.add(sightdetal, BorderLayout.NORTH);
		dbbase.add(url, BorderLayout.CENTER);
		dbbase.add(comment, BorderLayout.SOUTH);
		
		
		
		JButton  clearDBbase = new JButton("清空数据库");
		
		//该面板用于放用户自定义的相关设置信息
		JPanel  mymession = new JPanel();
		BorderLayout mymessionLay = new BorderLayout(40,5);
		mymession.setLayout(mymessionLay);
		//下拉列表框
		String[] urls = {"http://you.ctrip.com/","http://you.ctrip.com/sight/suzhou11.html","http://you.ctrip.com/sight/Shanghai2.html"};
		JComboBox rootURL = new JComboBox(urls);
		rootURL.setBorder(BorderFactory.createTitledBorder("请在下拉框中选择入口站点："));
		JLabel maxlabel = new JLabel("搜索的最大信息数：");
		JTextField maxText = new JTextField("100");
		maxText.setColumns(25);
		JButton  submit = new JButton("提交");
		JButton  cancel = new JButton("取消");
		//将控件加入到这个局部mymession中
		mymession.add(rootURL ,BorderLayout.NORTH);
		mymession.add(maxlabel,BorderLayout.CENTER);
		mymession.add(maxText, BorderLayout.EAST);
		//用于放按钮的panel
		JPanel subandcan = new JPanel();
		subandcan.add(submit);
		subandcan.add(cancel);		
		mymession.add(subandcan,BorderLayout.SOUTH);
		
		left.add(mymession);
		left.add(start);
		left.add(dbbase);
		left.add(clearDBbase);
		left.setBackground(new Color(30,28,20));
		
		
		//给右边的面板增加组件
		try {
			new ResultDataViewPage(right).showAllSightdetail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//新建分隔面板对象
		JSplitPane mainlayout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		mainlayout.setDividerSize(4);  //设置分割线的宽度
		mainlayout.setOneTouchExpandable(true);   //快速展开、折叠分隔条
		
		//将分隔面板嵌入到主框架上
		frame.add(mainlayout);
		frame.pack();
		frame.setVisible(true);
		
		return frame;
	}
	
	
	

}
