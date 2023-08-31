package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Excercise5 {

	public static void main(String[] args) {
		
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			
			showData(con, "city", "name, population", "countrycode = 'KOR' and Population > 1000000");
//			showData(con, "city", "name, population", "where  CountryCode = 'KOR' order by Population desc limit 10");
//			showData(con, "city", "name, population", "where  CountryCode = 'KOR' and Population between '1000000' and '5000000'");
//			showData(con, "city", "name", "");
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	String sql = "SELECT Name, CountryCode, Population"
//			+ "FROM city"
//			+ "where  CountryCode = 'KOR' and Population > 1000000";
//	
//			SELECT Name, CountryCode, Population
//			FROM city
//			where  CountryCode = 'KOR'
//			order by Population desc limit 10;
//
//			SELECT Name, CountryCode, Population
//			FROM city
//			where  CountryCode = 'KOR' and Population > 1000000 and Population < 5000000;
	
	private static void showData(Connection con, String tablename, String fields, String cond) throws Exception {
		Statement st = con.createStatement();
		ResultSet rs = null ;
		if (cond.isEmpty())
			rs = st.executeQuery(String.format("select %s from %s ", fields, tablename));
		else 
			rs = st.executeQuery(String.format("select %s from %s where %s", fields, tablename, cond));
		
		ResultSetMetaData rsmd = rs.getMetaData(); 
		int count = rsmd.getColumnCount();

		System.out.println("-".repeat(30));
		System.out.println(tablename + " 국가코드[KOR]"); 																
		System.out.println("(도시명 - 인구수)");
		System.out.println("-".repeat(30));
//		System.out.println();
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
