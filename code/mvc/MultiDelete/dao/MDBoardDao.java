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
		// SQL문을 데이터베이스에 보내기 위한 객체
		
		ResultSet rs = null;
		// SQL 문장을 실행하고 결과를 ResultSet으로 리턴
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
				   + " FROM MDBOARD "
				   + " ORDER BY SEQ DESC ";
		
		try {
			stmt = con.createStatement();
			System.out.println("3 쿼리 준비");
			
			rs = stmt.executeQuery(sql);
			System.out.println("4 쿼리 실행 및 리턴");
			
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
		// seq는 int였는데!!
		// 사실 넘어온건 chk의 벨류임 히힣
		// 그리고 sql구문에서 seq = ? ?부분을 우리가 넘버를 그냥 1로써주는거랑 '1'로 써주는거랑 같음
		// 그래서 스트링으로 받아줫고
		// 그리고 클라이언트에서 우리가 chk의 벨류를 dto에서 int형태로 받아왓지만
		// 그걸 from으로 muldel.jsp로 넘겨줬을때 문자형태인 chk벨류가 가버림
		// 그걸 다시 여기 dao에서 받아줘야하기때문에 string으로 받아줫고
		// 그걸 그대로 sql구문에 넣어줄수있는이유는 1이랑 '1'이랑 같게 취급하기때문 sql구문은
		
		// 컨넥션 용량이 꽉차면 더이상 연결이 안됨
		// 컨넥션 연결해서 한번에 데이터 삭제해줘야함
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		
		int[] cnt = null;
		
		try {
			pstm = con.prepareStatement(sql);
			
			// 들어올 seq가 배열이고 우리가 여러개 체크하면 여러개 삭제해야해서
			// 반복문을 돌려서 sql구문에 계속 넣어줌
			for (int i = 0; i < seq.length; i++) {
				pstm.setString(1, seq[i]);
				// 근데 sql구문이 한개밖에 없는데 이걸 여러개 넣는게 가능한가?
				// 밑에 설명말고 pstm으로 컨네션으로 데이터베이스와 연결한뒤
				// sql구문을 날려주고 실행준비를 시켜주는데
				// pstm.setString(1, seq[0])이 들어가는순가 그 문구는
				// 실행이된다 그뒤에 addBatch에 저장되고
				// sql구문 그대로 seq = ? 까지 남아있다
				// 그걸다시 1번지 2번지 넣으면서 실행이되면 addBatch에 계속
				// 적재시켜주는것이다.
				// 가능한지는 executeBatch 밑에 설명할거다. 
				pstm.addBatch();
				// 메모리에 적재 후, executeBatch() 메소드가 호출될 떄 한번에 실행
				System.out.println("삭제할 번호 : " +seq[i]);
			}
			System.out.println("3 쿼리 준비 : " + sql);
			
			cnt = pstm.executeBatch();	// addBatch에서 받아준 쿼리문을 한번에 실행
			// addBatch로 메모리에 적재해놔서 모아서 한번에 실행하는거라서 값이 많으므로 배열에 담아줌
			
			System.out.println("4 쿼리 실행 및 리턴");
			
			// 성공 : -2 / 실패 : -3
			// 삭제 성공 실패 유무
			for (int i = 0; i < cnt.length; i++) {
				if(cnt[i] == -2) {
					res++;
				}
			}
			// 결국 제크한것들을 다 잘 삭제되면 res의 값이랑
			// seq.length의 길이랑 같음
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
