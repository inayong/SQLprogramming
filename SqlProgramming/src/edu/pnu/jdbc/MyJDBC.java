package edu.pnu.jdbc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


//<주석달기>

class ColumnInfo {
	private String name;
	private int length;
	public ColumnInfo(String name, int length) {
		this.name = name;
		
		if (length <= 255) {
			this.length = (length < name.length()) ? name.length() : length;
		} else {
			this.length = name.length();
		}
	}
	public String getName() { return name; }
	public int getLength() { return length; }
}

public class MyJDBC {

	private String driver;
	private String url; 
	private String username;
	private String password;

	private List<ColumnInfo> columnInfoList;
	
	public MyJDBC(String driver, String url, String username, String password) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
		
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to load Driver:" + e.getMessage());
		}
	}
	
	private Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Fail to create Connetion:" + e.getMessage());
		}
		return null;
	}
	
	public List<List<String>> queryWithStatement(String query) {
		Connection con = getConnection();
		if (con == null)	return null;

		List<List<String>> list = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData md = rs.getMetaData();

			// 질의 결과 컬럼 정보를 저장
			columnInfoList = new ArrayList<>();
			for (int i = 1 ; i <= md.getColumnCount() ; i++) {
				columnInfoList.add(new ColumnInfo(md.getColumnName(i), md.getColumnDisplaySize(i)));
			}
			
			while(rs.next()) {
				List<String> tl = new ArrayList<>();
				for (int i = 1 ; i <= md.getColumnCount() ; i++) {
					tl.add(rs.getString(i));
				}
				list.add(tl);
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<List<String>> queryWithPreparedStatement(String query, Object...objs) {
		Connection con = getConnection();
		if (con == null)	return null;

		List<List<String>> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			for (int i = 0 ; i < objs.length ; i++) {
				if (objs[i] instanceof Integer) 	{	pstmt.setInt(i+1, (Integer)objs[i]); }
				else if (objs[i] instanceof Double) {	pstmt.setDouble(i+1, (Double)objs[i]); }
				else if (objs[i] instanceof String) {	pstmt.setString(i+1, (String)objs[i]); }
			}
			
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData md = rs.getMetaData();

			// 질의 결과 컬럼 정보를 저장
			columnInfoList = new ArrayList<>();
			for (int i = 1 ; i <= md.getColumnCount() ; i++) {
				columnInfoList.add(new ColumnInfo(md.getColumnName(i), md.getColumnDisplaySize(i)));
			}
			
			while(rs.next()) {
				List<String> tl = new ArrayList<>();
				for (int i = 1 ; i <= md.getColumnCount() ; i++) {
					tl.add(rs.getString(i));
				}
				list.add(tl);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void printList(List<List<String>> list) { //list<String>을 저장하는 List
		
		int totallength = columnInfoList.get(0).getLength(); //첫번째 컬럼길이를 totallength에 저장
		System.out.print(String.format("%" + columnInfoList.get(0).getLength() + "s", 
				columnInfoList.get(0).getName())); //String.format을 사용해서 문자열을 지정된 포맷으로 출력, 포맷 문자열 내의 '%'문자는 후에 전달되는 문자열로 대체
		for (int i = 1 ; i < columnInfoList.size(); i++) { 
			System.out.print(String.format("|%" + columnInfoList.get(i).getLength() + "s",
					columnInfoList.get(i).getName())); //컬럼 정보를 순회하며 각 컬럼의 이름과 길이를 출력, total
			totallength += columnInfoList.get(i).getLength() + 1;
		}
		System.out.println();
		System.out.println("=".repeat(totallength));
		
		for (List<String> tl : list) {
			System.out.print(String.format("%" + columnInfoList.get(0).getLength() + "s", tl.get(0)));
			for (int i = 1 ; i < tl.size(); i++) {
				System.out.print(String.format("|%" + columnInfoList.get(i).getLength() + "s", tl.get(i)));
			}
			System.out.println();
		}
	}
}
