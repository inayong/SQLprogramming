package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Exam2 {
	public static void main(String[] args) {
		
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/sqltest";
			String name = "scott";
			String password = "tiger";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			
			Statement st = con.createStatement();

			for (int i =1; i <=100; i++) {
				String query = "insert into user (username, password, role, enable, joindate) values ('user"+i+"', 'password"+i+"', 'user', true, now())";
				System.out.println(st.executeUpdate(query));
			}
			st.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
