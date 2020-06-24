package com.my.model.dao;

import static com.my.model.db.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.model.dto.MyDto;

public class MyDao {
	
	public List<MyDto> selectList(){
		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYDATE "
				   + " FROM MYBOARD ";
				
		List<MyDto> list = new ArrayList<MyDto>();
		
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				MyDto dto = new MyDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMydate(rs.getDate(4));
				// MyDto dto = new MyDto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				// 이렇게도 가능
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return list;
	}
	
	public MyDto selectOne(int myno) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT "
				   + " FROM MYBOARD "
				   + " WHERE MYNO = ? ";
		// 여기서 MYNO를 안받아오면 jsp파일중 selectOne을 가져오거나 쓸때
		// dto.getMyno()이걸 못가져온다 왜냐하면 우리가 원하는 번호의 로우들을 받아오지만
		// 다시 selectOne 로우를 내보내주는데 내보내주면 dto의 myno는 초기화가 안돼서 
		// 초기값인 0이 담아진 dto가 넘어가기때문에 
		// 업데이트나 그런곳에서 받아와서 dto.getMyno()를 써주면 0이 가져와진다 계속.
		// 여기서 sql구문에 MYNO를 안써줄거면 우리가
		// request.getParameter로 받아온 myno로 써주어야 잘넘어가고 잘 받아진다.
		// 이거는 중요하니깐 까먹지말자..
		
		
		MyDto dto = new MyDto();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			rs = pstm.executeQuery();
			while (rs.next()) {
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return dto;
	}
	
	public int insert(MyDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MYBOARD VALUES(MYNOSEQ.NEXTVAL,?,?,?,SYSDATE) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMytitle());
			pstm.setString(3, dto.getMycontent());
			
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
	
	public int update(MyDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MYBOARD SET MYTITLE = ?,MYCONTENT = ? "
				   + " WHERE MYNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMytitle());
			pstm.setString(2, dto.getMycontent());
			pstm.setInt(3, dto.getMyno());
			
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;
	}
	public int delete(int myno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		int res = 0;
		String sql = " DELETE FROM MYBOARD WHERE MYNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;
	}
}
