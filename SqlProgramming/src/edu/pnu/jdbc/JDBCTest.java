package edu.pnu.jdbc;

import java.util.List;

public class JDBCTest {

	public static void main(String[] args) {
		
		MyJDBC mj = new MyJDBC("com.mysql.cj.jdbc.Driver",
								"jdbc:mysql://localhost:3306/world",
								"scott",
								"tiger");
		
		List<List<String>> list = mj.queryWithStatement("select * from country"); 
		
		
	
//		List<List<String>> list = queryPrepare
		
		
		mj.printList(list);
	}
}
