package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;

public class Excercise6 {

	public static void main(String[] args) {
		
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql.//localhost:3306/world";
			String username = "scott";
			String password = "tiger";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			
//			showData(con, "country" );
			
//			select language, isofficial, percentage  
//			from country c, countrylanguage cl
//			where cl.countrycode=c.code and c.code='kor';
//
//			select language, isofficial, percentage  
//			from country c
//				join countrylanguage cl
//			    on cl.countrycode=c.code and c.code='kor';
//
//			select language, isofficial, percentage      
//			from countrylanguage cl
//			where exists
//				(select * from country c where cl.countrycode=c.code and c.code='kor');
			
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
