package edu.pnu.sp;

import java.sql.Connection;
import java.sql.DriverManager;

//프로시저 nationLanguage02()에서 출력 파라미터에 가장 높은 percentage를 되돌려 주도록 수정
public class Exam2 {
	public static void main(String[] args) throws Exception {

		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/world";
		String name = "scott";
		String password = "tiger";

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, name, password);

		exeProc1(con);
//		exeProc2(con);

		con.close();
	}
	public static void exeProc1(Connection connection) {
//		try {
//			
//		}
	}
}
