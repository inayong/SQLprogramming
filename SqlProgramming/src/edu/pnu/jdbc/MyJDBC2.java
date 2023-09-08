package edu.pnu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyJDBC2 {
	
	private String driver;
	private String url;
	private String user;
	private String pwd;
	
	public MyJDBC2(String driver, String url, String user, String pwd) {
		this.driver =driver;
		this.url = url;
		this.user = user;
		this.pwd = pwd;
	}
	
	private Connection getConnection() throws Exception {
		Class.forName(driver);
		return DriverManager.getConnection(url, user, pwd);
		
	}
	
	public List<List<String>> queryWithStatement(String query) {
		
		try {
			Connection con = getConnection();
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData md = rs.getMetaData(); //ResultSet의 메타데이터 알수있음
			
			List<List<String>> list = new ArrayList<>();
			
			while (rs.next()) {
				List<String> ls = new ArrayList<>();
				for (int i = 1; i <= md.getColumnCount(); i++) {
					ls.add(rs.getString(i));
				}
				list.add(ls);
			}
			
			rs.close();
			st.close();
			con.close();
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void printList(List<List<String>> list) {
		for (List<String> l : list) {
			for (String s : l)
				System.out.print(s + " ");
			System.out.println();
		}
	}
	


}