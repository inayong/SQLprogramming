package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//city 테이블에서 국가코드가 'kor''chn''jpn'인 도시를 찾으세요 hint)in
public class Excercise4 {
	public static void main(String[] args) {
		
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
					
			String sql = "SELECT Name, CountryCode"
					+ "FROM city"
					+ "where  countrycode in ('KOR', 'CHN', 'JPN')";
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				System.out.print(rs.getString("name") + " ");
				System.out.println("'" + rs.getString("countrycode") + "'");
			}
			rs.close();
			st.close();
			con.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
