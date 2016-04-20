package cn.ustc.sightdatacollector.mainframe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;

/**
 * ����������ʾϵͳ������
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
		
		//����ߵ�panel��������
		GridLayout leftLay = new GridLayout(4,1,0,30);
		left.setLayout(leftLay);
		JButton  start = new JButton("��ʼץȡ");
		//�����Ƕ������ݲֿ����
		JPanel  dbbase = new JPanel();
		dbbase.setLayout(new BorderLayout(10,10));
		JButton sightdetal = new JButton("��������");
		JButton url = new JButton("url��");
		JButton comment = new JButton("����");
		dbbase.add(sightdetal, BorderLayout.NORTH);
		dbbase.add(url, BorderLayout.CENTER);
		dbbase.add(comment, BorderLayout.SOUTH);
		
		
		
		JButton  clearDBbase = new JButton("������ݿ�");
		
		//��������ڷ��û��Զ�������������Ϣ
		JPanel  mymession = new JPanel();
		BorderLayout mymessionLay = new BorderLayout(40,5);
		mymession.setLayout(mymessionLay);
		//�����б��
		String[] urls = {"http://you.ctrip.com/","http://you.ctrip.com/sight/suzhou11.html","http://you.ctrip.com/sight/Shanghai2.html"};
		JComboBox rootURL = new JComboBox(urls);
		rootURL.setBorder(BorderFactory.createTitledBorder("������������ѡ�����վ�㣺"));
		JLabel maxlabel = new JLabel("�����������Ϣ����");
		JTextField maxText = new JTextField("100");
		maxText.setColumns(25);
		JButton  submit = new JButton("�ύ");
		JButton  cancel = new JButton("ȡ��");
		//���ؼ����뵽����ֲ�mymession��
		mymession.add(rootURL ,BorderLayout.NORTH);
		mymession.add(maxlabel,BorderLayout.CENTER);
		mymession.add(maxText, BorderLayout.EAST);
		//���ڷŰ�ť��panel
		JPanel subandcan = new JPanel();
		subandcan.add(submit);
		subandcan.add(cancel);		
		mymession.add(subandcan,BorderLayout.SOUTH);
		
		left.add(mymession);
		left.add(start);
		left.add(dbbase);
		left.add(clearDBbase);
		left.setBackground(new Color(30,28,20));
		
		
		//���ұߵ�����������
		try {
			new ResultDataViewPage(right).showAllSightdetail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//�½��ָ�������
		JSplitPane mainlayout = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		mainlayout.setDividerSize(4);  //���÷ָ��ߵĿ��
		mainlayout.setOneTouchExpandable(true);   //����չ�����۵��ָ���
		
		//���ָ����Ƕ�뵽�������
		frame.add(mainlayout);
		frame.pack();
		frame.setVisible(true);
		
		return frame;
	}
	
	
	

}
