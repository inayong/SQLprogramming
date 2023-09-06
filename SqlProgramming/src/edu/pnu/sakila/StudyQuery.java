package edu.pnu.sakila;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class StudyQuery {

	public static void main(String[] args) throws Exception {

		StudyQuery sq = new StudyQuery(); // 메소드 만들기

		// 데이터베이스 연결 (con) 여기서 for
		Connection con = sq.getConnection();
		Scanner sc = new Scanner(System.in);
		{
			System.out.println("tablename : ");
			String tablename = sc.next();

			// 질의 (결과로서 배열을 받는다)
			List<List<String>> list = sq.getQueryResult(con, tablename);
			// 결과 출력
			sq.printResult(list);
		}
	}

	private void printResult(List<List<String>> list) {
		
	}

	private List<List<String>> getQueryResult(Connection con, String tablename) throws Exception {
		// preparedStatement/statement
		// resultset ==> List<String>
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(String.format("select * from %s ",tablename));
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		
		System.out.println("Table : " + tablename);
		while(rs.next()) {
			
		}
		
		
		return null;
	}

	private Connection getConnection() throws Exception {
		
		String url= "jdbc:mysql://localhost:3306/sakila";
		String name = "scott";
		String password = "tiger";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(url, name, password);
	}
}
