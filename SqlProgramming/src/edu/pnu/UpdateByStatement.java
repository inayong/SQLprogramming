package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateByStatement {

	public static void main(String[] args) {
		
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/sqltest";
			String name = "scott";
			String password = "tiger";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			
			Scanner sc = new Scanner(System.in);
			System.out.println("enable : ");
			String upd1 = sc.next();
			System.out.println("username : ");
			String upd2 = sc.next();
			//요렇게 사용하는건 PreparedStatement 쓸때
			
			Statement st = con.createStatement();
			
			String query = "update user set enable ="+upd1+" where username like '"+upd2+"'"; 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
