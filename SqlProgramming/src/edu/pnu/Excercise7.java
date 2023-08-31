package edu.pnu;

import java.sql.Connection;

public class Excercise7 {

	public static void main(String[] args) {
		
		Connection con = null;
//		try { //db 연결
//			String driver = "com.mysql.cj.jdbc.driver";
//			String url = "jdbc:mysql://localhost:3306/world";
//			String username = "scott";
//			String password	= "tiger";
//			
//			
//		}
	}
	
	
	
}

//select count(name) as kor_city
//from city
//where CountryCode = 'kor';
//
//select count(name) as c_city
//from city
//group by CountryCode;
//
//select co.name, count(c.name) as c_city
//from city c, country co
//where c.countrycode = co.Code
//group by Co.Code;
//
//select co.name, count(c.name) as c_city
//from city c, country co
//where c.countrycode = co.Code 
//group by Co.Code
//having c_city >= 10
//order by c_city desc;


