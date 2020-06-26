package com.muldel.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			con = DriverManager.getConnection(url,user,password);
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static boolean isConnection(Connection con) {
		boolean valid = true;
		// 컨넥션 객체가 있는지 없는지 검사하는 메소드
		
		try {
			if(con == null || con.isClosed()) {
				// con이 null이거나 con이 닫혀있다면
				valid = false;	// 연결 안되어있어요 라는 뜻
			}
		} catch (SQLException e) {
			valid = false;
			// 예외가되도 연결이 안된거니깐 예외에서도 false로 바꾸엄
			e.printStackTrace();
		}
		// 최종적으로 null이 아니고 안 닫혀있으면 true 리턴
		return valid;
	}
	
	public static void close(Connection con) {
		// 애초에 컨넥션 객체가 없으면 close할 필요가 없어짐 이걸 만들면
		if(isConnection(con)) {
			// 만일 컨넥션이 연결이 되어있다면
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null) {
			// Statement 객체가 있다면
			
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void commit(Connection con) {
		if (isConnection(con)) {
			// 컨넥션이 연결되어있다면
			
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void rollback(Connection con) {
		if (isConnection(con)) {
			
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
