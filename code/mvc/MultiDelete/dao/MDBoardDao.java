package com.muldel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.muldel.db.JDBCTemplate;
import com.muldel.dto.MDBoardDto;

public class MDBoardDao extends JDBCTemplate{

	public List<MDBoardDto> selectList(){
		Connection con = getConnection();
		
		Statement stmt = null;
		// SQL¹®À» µ¥ÀÌÅÍº£ÀÌ½º¿¡ º¸³»±â À§ÇÑ °´Ã¼
		
		ResultSet rs = null;
		// SQL ¹®ÀåÀ» ½ÇÇàÇÏ°í °á°ú¸¦ ResultSetÀ¸·Î ¸®ÅÏ
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
				   + " FROM MDBOARD "
				   + " ORDER BY SEQ DESC ";
		
		try {
			stmt = con.createStatement();
			System.out.println("3 Äõ¸® ÁØºñ");
			
			rs = stmt.executeQuery(sql);
			System.out.println("4 Äõ¸® ½ÇÇà ¹× ¸®ÅÏ");
			
			while(rs.next()) {
				MDBoardDto dto = 
						new MDBoardDto(rs.getInt(1),
									   rs.getString(2),
									   rs.getString(3),
									   rs.getString(4),
									   rs.getDate(5));	
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("error 3 4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return list;
	}
	 
	public MDBoardDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT REGDATE "
				   + " FROM MDBOARD "
				   + " WHERE SEQ = ? ";
		
		MDBoardDto dto = new MDBoardDto();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return dto;
	}
	
	public int insert(MDBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " INSERT INTO MDBOARD "
				   + " VALUES(MDBOARDSEQ.NEXTVAL,?,?,?,SYSDATE) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;
	}
	
	public int update(MDBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE MDBOARD SET TITLE = ?, CONTENT = ? "
				   + " WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;
	}
	
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " DELETE FROM MDBOARD "
				   + " WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
	
	public int multiDelete(String[] seq) {
		// seq´Â int¿´´Âµ¥!!
		// »ç½Ç ³Ñ¾î¿Â°Ç chkÀÇ º§·ùÀÓ È÷ÆR
		// ±×¸®°í sql±¸¹®¿¡¼­ seq = ? ?ºÎºĞÀ» ¿ì¸®°¡ ³Ñ¹ö¸¦ ±×³É 1·Î½áÁÖ´Â°Å¶û '1'·Î ½áÁÖ´Â°Å¶û °°À½
		// ±×·¡¼­ ½ºÆ®¸µÀ¸·Î ¹Ş¾Æ¢Z°í
		// ±×¸®°í Å¬¶óÀÌ¾ğÆ®¿¡¼­ ¿ì¸®°¡ chkÀÇ º§·ù¸¦ dto¿¡¼­ intÇüÅÂ·Î ¹Ş¾Æ¿ÓÁö¸¸
		// ±×°É fromÀ¸·Î muldel.jsp·Î ³Ñ°ÜÁáÀ»¶§ ¹®ÀÚÇüÅÂÀÎ chkº§·ù°¡ °¡¹ö¸²
		// ±×°É ´Ù½Ã ¿©±â dao¿¡¼­ ¹Ş¾ÆÁà¾ßÇÏ±â¶§¹®¿¡ stringÀ¸·Î ¹Ş¾Æ¢Z°í
		// ±×°É ±×´ë·Î sql±¸¹®¿¡ ³Ö¾îÁÙ¼öÀÖ´ÂÀÌÀ¯´Â 1ÀÌ¶û '1'ÀÌ¶û °°°Ô Ãë±ŞÇÏ±â¶§¹® sql±¸¹®Àº
		
		// ÄÁ³Ø¼Ç ¿ë·®ÀÌ ²ËÂ÷¸é ´õÀÌ»ó ¿¬°áÀÌ ¾ÈµÊ
		// ÄÁ³Ø¼Ç ¿¬°áÇØ¼­ ÇÑ¹ø¿¡ µ¥ÀÌÅÍ »èÁ¦ÇØÁà¾ßÇÔ
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		
		int[] cnt = null;
		
		try {
			pstm = con.prepareStatement(sql);
			
			// µé¾î¿Ã seq°¡ ¹è¿­ÀÌ°í ¿ì¸®°¡ ¿©·¯°³ Ã¼Å©ÇÏ¸é ¿©·¯°³ »èÁ¦ÇØ¾ßÇØ¼­
			// ¹İº¹¹®À» µ¹·Á¼­ sql±¸¹®¿¡ °è¼Ó ³Ö¾îÁÜ
			for (int i = 0; i < seq.length; i++) {
				pstm.setString(1, seq[i]);
				// ±Ùµ¥ sql±¸¹®ÀÌ ÇÑ°³¹Û¿¡ ¾ø´Âµ¥ ÀÌ°É ¿©·¯°³ ³Ö´Â°Ô °¡´ÉÇÑ°¡?
				// ¹Ø¿¡ ¼³¸í¸»°í pstmÀ¸·Î ÄÁ³×¼ÇÀ¸·Î µ¥ÀÌÅÍº£ÀÌ½º¿Í ¿¬°áÇÑµÚ
				// sql±¸¹®À» ³¯·ÁÁÖ°í ½ÇÇàÁØºñ¸¦ ½ÃÄÑÁÖ´Âµ¥
				// pstm.setString(1, seq[0])ÀÌ µé¾î°¡´Â¼ø°¡ ±× ¹®±¸´Â
				// ½ÇÇàÀÌµÈ´Ù ±×µÚ¿¡ addBatch¿¡ ÀúÀåµÇ°í
				// sql±¸¹® ±×´ë·Î seq = ? ±îÁö ³²¾ÆÀÖ´Ù
				// ±×°É´Ù½Ã 1¹øÁö 2¹øÁö ³ÖÀ¸¸é¼­ ½ÇÇàÀÌµÇ¸é addBatch¿¡ °è¼Ó
				// ÀûÀç½ÃÄÑÁÖ´Â°ÍÀÌ´Ù.
				// °¡´ÉÇÑÁö´Â executeBatch ¹Ø¿¡ ¼³¸íÇÒ°Å´Ù. 
				pstm.addBatch();
				// ¸Ş¸ğ¸®¿¡ ÀûÀç ÈÄ, executeBatch() ¸Ş¼Òµå°¡ È£ÃâµÉ ‹š ÇÑ¹ø¿¡ ½ÇÇà
				System.out.println("»èÁ¦ÇÒ ¹øÈ£ : " +seq[i]);
			}
			System.out.println("3 Äõ¸® ÁØºñ : " + sql);
			
			cnt = pstm.executeBatch();	// addBatch¿¡¼­ ¹Ş¾ÆÁØ Äõ¸®¹®À» ÇÑ¹ø¿¡ ½ÇÇà
			// addBatch·Î ¸Ş¸ğ¸®¿¡ ÀûÀçÇØ³ö¼­ ¸ğ¾Æ¼­ ÇÑ¹ø¿¡ ½ÇÇàÇÏ´Â°Å¶ó¼­ °ªÀÌ ¸¹À¸¹Ç·Î ¹è¿­¿¡ ´ã¾ÆÁÜ
			
			System.out.println("4 Äõ¸® ½ÇÇà ¹× ¸®ÅÏ");
			
			// ¼º°ø : -2 / ½ÇÆĞ : -3
			// »èÁ¦ ¼º°ø ½ÇÆĞ À¯¹«
			for (int i = 0; i < cnt.length; i++) {
				if(cnt[i] == -2) {
					res++;
				}
			}
			// °á±¹ Á¦Å©ÇÑ°ÍµéÀ» ´Ù Àß »èÁ¦µÇ¸é resÀÇ °ªÀÌ¶û
			// seq.lengthÀÇ ±æÀÌ¶û °°À½
			if(seq.length==res) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
	
	
	
}
