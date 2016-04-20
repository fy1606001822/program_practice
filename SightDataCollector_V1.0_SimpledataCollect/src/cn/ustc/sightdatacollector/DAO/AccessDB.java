package cn.ustc.sightdatacollector.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.ustc.sightdatacollector.model.SightData;
import cn.ustc.sightdatacollector.model.SightURL;

public class AccessDB {
	private static final SessionFactory sessionFactory = new org.hibernate.cfg.AnnotationConfiguration()
			.configure().buildSessionFactory();
	private static final String Driver = "com.mysql.jdbc.Driver"; // 驱动程序
	private static final String URL = "jdbc:mysql://localhost:3306/traveldata"; // 连接的URL,db_name为数据库名
	private static final String Username = "root"; // 用户名
	private static final String Password = "root"; // 密码
	private static Connection conn = null;
	//存贮景点信息
	public static void save(SightData sightData) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(sightData);
		session.getTransaction().commit();

	}

	//存储URL
	public static void save(SightURL url) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(url);
		session.getTransaction().commit();

	}
	
	public static void openConnection() {
		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			AccessDB.conn = DriverManager.getConnection(URL, Username, Password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return conn;
	}

	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ResultSet getAllSight() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select id,sightName,sightScore,sightIntruduction from sightdata";

		openConnection();
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	
	
}