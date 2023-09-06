package edu.pnu.sp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;



public class StoredProcedures {
	public static void main(String[] args) throws Exception { // exception 모든 예외
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/sqltest";
		String name = "scott";
		String password = "tiger";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, name, password);
		 
//		executeProcedure1(con); // sampledb
		executeProcedure2(con); //sqltest
		
		
		con.close();
		
	}
	
	public static void executeProcedure1(Connection connection) { 
			
		try {
			CallableStatement cs = connection.prepareCall("CALL myFirst(?)");
			
			cs.setString(1, "user3%");
			
			ResultSet rs  = cs.executeQuery();
			
			while (rs.next()) {
				System.out.print(rs.getString("cust_name") + ",");
				System.out.print(rs.getString("cust_contact") + ",");
				System.out.print(rs.getString("cust_email"));
				System.out.println();
			}
			rs.close();
			cs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void executeProcedure2(Connection connection) {
		try {
			String sql = "CALL myStoredProc2(?,?)";
			CallableStatement cs = connection.prepareCall(sql);
			cs.setString(1, "user3%");
			cs.registerOutParameter(2, Types.INTEGER); //4줄 중요
			
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
			System.out.println("-".repeat(10));
			System.out.println(cs.getInt(2));
			rs.close();
			cs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
