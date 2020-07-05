package com.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.mvc.model.dto.LoginBoardDto;

import static com.db.JDBCTemplate.*;
public class LoginBoardDaoImpl implements LoginBoardDao {
	
	
	public LoginBoardDto login(String myid, String mypw) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		LoginBoardDto dto = null;
		
		try {
			pstm = con.prepareStatement(LOGIN);
			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			pstm.setString(3, "Y");
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto = new LoginBoardDto(rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getString(6),
										rs.getString(7),
										rs.getString(8),
										rs.getString(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con,pstm,rs);
		}
		
		
		return dto;
	}
	public List<LoginBoardDto> selectList(){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<LoginBoardDto> list = new ArrayList<LoginBoardDto>();
		
		LoginBoardDto dto = null;
		
		try {
			pstm = con.prepareStatement(SELECT_LIST);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto = new LoginBoardDto(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con,pstm,rs);
		}
		
		return list;
	}
	
	public List<LoginBoardDto> selectEnabled() {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<LoginBoardDto> list = new ArrayList<LoginBoardDto>();
		LoginBoardDto dto = null;
		try {
			pstm = con.prepareStatement(SELECT_LIST_Y);
			pstm.setString(1, "Y");
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto = new LoginBoardDto(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con,pstm,rs);
		}
		return list;
	}
	
	public int updateUserRole(LoginBoardDto dto) {
		return 0;
	}
	
	public LoginBoardDto idCheck(String myid) {
		return null;
	}
	
	public int insertUser(LoginBoardDto dto) {
		return 0;
	}
	
	public LoginBoardDto selectUser() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		LoginBoardDto dto = null;
		
		
		return null;
	}
	
	public int updateUser(LoginBoardDto dto) {
		return 0;
	}
	
	public int deleteUser(int myno) {
		return 0;
	}

}
