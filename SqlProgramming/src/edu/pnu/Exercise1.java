package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class Exercise1 {

	public static void main(String[] args) {

		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";

			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);

			showData(con, "city", "id,name,countrycode,district,population");
			showData(con, "country", "code,name,continent,region,surfacearea,indepyear,population,lifeexpectancy,gnp,gnpold,localname,governmentform,headofstate");
			showData(con,"countrylanguage", "countrycode,language,isofficial,percentage");

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void showData(Connection con, String tablename, String fields) throws Exception {
		//질의
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(String.format("select %s from %s limit 10", fields, tablename));
		ResultSetMetaData rsmd = rs.getMetaData(); //요게뭐지
		int count = rsmd.getColumnCount();

		System.out.println("=".repeat(80));
		System.out.println("Table : " + tablename); 																
		System.out.println("=".repeat(80));
		while (rs.next()) {
			for (int i = 1; i <= count; i++) {
				if (i != 1)
					System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		rs.close();
		st.close();
	}
}
