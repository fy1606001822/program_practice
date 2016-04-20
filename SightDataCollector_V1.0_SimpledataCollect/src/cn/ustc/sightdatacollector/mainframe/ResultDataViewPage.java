package cn.ustc.sightdatacollector.mainframe;
import cn.ustc.sightdatacollector.DAO.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class ResultDataViewPage {
	private JPanel fatherpanel = null;

	public ResultDataViewPage(JPanel fatherpanel) {
		this.fatherpanel = fatherpanel;
	}

	@SuppressWarnings("null")
	public void showAllSightdetail() throws Exception {
		JPanel whole = new JPanel();
		BorderLayout layout = new BorderLayout(10, 50);
		whole.setLayout(layout);

		JPanel searchPanel = new JPanel();
		// 在搜索栏面板中增加搜索框和锁锁按钮等组件
		GridLayout searchLyout = new GridLayout(1,2);
		JTextField searchbox = new JTextField("请输入关键字");
		searchbox.setColumns(60);
		JButton searchButton = new JButton("搜索");

		searchPanel.add(searchbox);
		searchPanel.add(searchButton);
		searchbox.setSize(80, 30);

		ResultSet rs = AccessDB.getAllSight(); // 这个参数由数据库查询函数提供
		// 下面的panel用于呈现数据库内容
		JPanel result = new JPanel();
		int count = 0;
		JLabel countl = new JLabel("总共");
		JLabel countr = new JLabel("条记录");
		String[] title = { "NO.", "name", "core", "intruduction" };
		JTable table = null;
	
		if (rs != null) {
			try {
				rs.last();
				count = rs.getRow();
				rs.beforeFirst();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[][] sightdetail = new String[count][];

			int i = 0;
			while (rs.next()) {
				String no = rs.getString(1);
				String name = rs.getString(2);
				String core = rs.getString(3);
				String intruction = rs.getString(4);
				
				String[] sight= new String[4];
				
				sight[0]=no;
				sight[1]=name;
				sight[2]=core;
				sight[3]=intruction;
				sightdetail[i] = sight;
				i++;

			}
			table = new JTable(sightdetail, title);
		}

		JScrollPane scr = new JScrollPane(table);
		result.add(scr);

		whole.add(searchbox, BorderLayout.NORTH);
		whole.add(scr,BorderLayout.CENTER);
		this.fatherpanel.add(whole);

	}
}
