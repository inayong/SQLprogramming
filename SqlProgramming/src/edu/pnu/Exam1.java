package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Exam1 {
	public static void main(String[] args) {
		
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String name = "scott";
			String password = "tiger";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			
			PreparedStatement pt = con.prepareStatement("select id, name, countrycode, distirict, population from city where name=?");
			pt.setString(1, "Seoul");
			ResultSet rs = pt.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getInt("id") + ",");
				System.out.println(rs.getString("name")+",");
				System.out.println(rs.getString("countrycode") + ",");
				System.out.println(rs.getString("distirict") + ",");
				System.out.println(rs.getInt("population") + ",");
			}
			rs.close();
			pt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		}
	}
}
