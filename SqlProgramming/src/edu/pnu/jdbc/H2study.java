package edu.pnu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class H2study {
	
	private String driver;
	private String url;
	private String user;
	private String pwd;
	
	public H2study(String driver, String url, String user, String pwd) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to load Driver:" + e.getMessage());
		}
	}
	
	private Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			System.out.println("Fail to create Connetion:" + e.getMessage());
		}
		return null;	
	}
	
	public List<List<String>> queryWithStatement(String query) {
		try {
			Connection con = getConnection();
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData md = rs.getMetaData();
			
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
