package edu.pnu.sp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

//국가명 ‘South Kore’에서 사용되는 언어를 검색해서, language, isofficial, percentage를 출력하는 프로시저를 작성 - nationLanguage1();
public class Exam1 {
	public static void main(String[] args) throws Exception {
	
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/world";
		String name = "scott";
		String password = "tiger";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, name, password);
		
//		exeProcedure1(con);
		exeProcedure2(con);
		
		con.close();
}
	
	public static void exeProcedure1(Connection connection) {
		try {
			CallableStatement cs = connection.prepareCall("CALL nationLanguage01()"); 
			// mysql에서 호출을 call nationLanguage01();로 하니깐 물음표 들어갈 필요없읍!!
			
//			cs.setString(1, );//?안들어가니깐 이것도 필요없음
			
			ResultSet rs  = cs.executeQuery();
			
			while (rs.next()) {
				System.out.print(rs.getString("language") + ", ");
				System.out.print(rs.getString("isofficial") + ", ");
				System.out.print(rs.getString("percentage"));
				System.out.println();
			}
			rs.close();
			cs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void exeProcedure2(Connection connection) {
		try {
			String sql = "CALL nationLanguage02(?)";
			CallableStatement cs = connection.prepareCall(sql);
			cs.setString(1, "South Korea");
			
			ResultSet rs = cs.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			if (md.getColumnCount() > 0) {
				while (rs.next()) {
					System.out.print(rs.getString(1));
					for (int i = 2 ; i <= md.getColumnCount(); i++) {
						System.out.print("," + rs.getString(i));
					}
					System.out.println();
				}
			}
			rs.close();
			cs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
