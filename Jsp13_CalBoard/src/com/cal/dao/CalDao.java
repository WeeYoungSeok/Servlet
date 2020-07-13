package com.cal.dao;

import static com.cal.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cal.dto.CalDto;
public class CalDao {
	
	// 일정 추가
	
	public int insertCalBoard(CalDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		int res = 0;
		String sql = " INSERT INTO CALBOARD "
				   + " VALUES (CALBOARDSEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE) ";
		
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setString(4, dto.getMdate());
			
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, pstm);
		}
		
		return res;
	}
	
	public List<CalDto> getCalList(String id, String yyyyMMdd) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		//String keyword = "%" + yyyyMMdd + "%";
		
		String sql = " SELECT SEQ, ID, TITLE, CONTENT, MDATE, REGDATE "
				   + " FROM CALBOARD "
				   + " WHERE ID = ? "
				   + " AND (MDATE LIKE ?||'%') ";
		
		System.out.println(sql);
		List<CalDto> list = new ArrayList<CalDto>();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				CalDto dto = new CalDto(rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getDate(6));
				
				list.add(dto);
						
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con,pstm);
		}
		
		return list;
	}
	
	// 일정 상세보기
	
	public CalDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = " SELECT SEQ, ID, TITLE, CONTENT, MDATE, REGDATE "
				   + " FROM CALBOARD "
				   + " WHERE SEQ = ? ";
		CalDto dto = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				dto = new CalDto(rs.getInt(1),
								 rs.getString(2),
								 rs.getString(3),
								 rs.getString(4),
								 rs.getString(5),
								 rs.getDate(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con,pstm,rs);
		}
		
		return dto;
	}
	
	public int update(CalDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE CALBOARD "
				   + " SET CONTENT = ?, TITLE = ?, MDATE = ? "
				   + " WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getContent());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getMdate());
			pstm.setInt(4, dto.getSeq());
			
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con,pstm);
		}
		return res;
	}
	
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " DELETE FROM CALBOARD WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con,pstm);
		}
		return res;
	}
	
	public int multiDelete(String[] seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int[] cnt = null;
		int res = 0;
		
		String sql = " DELETE FROM CALBOARD WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			
			for(int i = 0; i < seq.length; i++) {
				pstm.setString(1, seq[i]);
				pstm.addBatch();
				System.out.println(seq[i]);
			}
			
			cnt = pstm.executeBatch();
			
			for(int i = 0; i < cnt.length; i++) {
				if(cnt[i] == -2) {
					res++;
				}
			}
			
			if(seq.length == res) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con,pstm);
		}
		
		return (res == seq.length)? 1 : 0;
	}
}
