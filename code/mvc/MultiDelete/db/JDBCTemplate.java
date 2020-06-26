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
		// ���ؼ� ��ü�� �ִ��� ������ �˻��ϴ� �޼ҵ�
		
		try {
			if(con == null || con.isClosed()) {
				// con�� null�̰ų� con�� �����ִٸ�
				valid = false;	// ���� �ȵǾ��־�� ��� ��
			}
		} catch (SQLException e) {
			valid = false;
			// ���ܰ��ǵ� ������ �ȵȰŴϱ� ���ܿ����� false�� �ٲپ�
			e.printStackTrace();
		}
		// ���������� null�� �ƴϰ� �� ���������� true ����
		return valid;
	}
	
	public static void close(Connection con) {
		// ���ʿ� ���ؼ� ��ü�� ������ close�� �ʿ䰡 ������ �̰� �����
		if(isConnection(con)) {
			// ���� ���ؼ��� ������ �Ǿ��ִٸ�
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null) {
			// Statement ��ü�� �ִٸ�
			
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
			// ���ؼ��� ����Ǿ��ִٸ�
			
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
