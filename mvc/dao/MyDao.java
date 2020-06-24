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
				// �̷��Ե� ����
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
		// ���⼭ MYNO�� �ȹ޾ƿ��� jsp������ selectOne�� �������ų� ����
		// dto.getMyno()�̰� �������´� �ֳ��ϸ� �츮�� ���ϴ� ��ȣ�� �ο���� �޾ƿ�����
		// �ٽ� selectOne �ο츦 �������ִµ� �������ָ� dto�� myno�� �ʱ�ȭ�� �ȵż� 
		// �ʱⰪ�� 0�� ����� dto�� �Ѿ�⶧���� 
		// ������Ʈ�� �׷������� �޾ƿͼ� dto.getMyno()�� ���ָ� 0�� ���������� ���.
		// ���⼭ sql������ MYNO�� �Ƚ��ٰŸ� �츮��
		// request.getParameter�� �޾ƿ� myno�� ���־�� �߳Ѿ�� �� �޾�����.
		// �̰Ŵ� �߿��ϴϱ� ���������..
		
		
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
