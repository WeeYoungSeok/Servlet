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
		// SQL���� �����ͺ��̽��� ������ ���� ��ü
		
		ResultSet rs = null;
		// SQL ������ �����ϰ� ����� ResultSet���� ����
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
				   + " FROM MDBOARD "
				   + " ORDER BY SEQ DESC ";
		
		try {
			stmt = con.createStatement();
			System.out.println("3 ���� �غ�");
			
			rs = stmt.executeQuery(sql);
			System.out.println("4 ���� ���� �� ����");
			
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
		// seq�� int���µ�!!
		// ��� �Ѿ�°� chk�� ������ ���R
		// �׸��� sql�������� seq = ? ?�κ��� �츮�� �ѹ��� �׳� 1�ν��ִ°Ŷ� '1'�� ���ִ°Ŷ� ����
		// �׷��� ��Ʈ������ �޾ƢZ��
		// �׸��� Ŭ���̾�Ʈ���� �츮�� chk�� ������ dto���� int���·� �޾ƿ�����
		// �װ� from���� muldel.jsp�� �Ѱ������� ���������� chk������ ������
		// �װ� �ٽ� ���� dao���� �޾�����ϱ⶧���� string���� �޾ƢZ��
		// �װ� �״�� sql������ �־��ټ��ִ������� 1�̶� '1'�̶� ���� ����ϱ⶧�� sql������
		
		// ���ؼ� �뷮�� ������ ���̻� ������ �ȵ�
		// ���ؼ� �����ؼ� �ѹ��� ������ �����������
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		
		int[] cnt = null;
		
		try {
			pstm = con.prepareStatement(sql);
			
			// ���� seq�� �迭�̰� �츮�� ������ üũ�ϸ� ������ �����ؾ��ؼ�
			// �ݺ����� ������ sql������ ��� �־���
			for (int i = 0; i < seq.length; i++) {
				pstm.setString(1, seq[i]);
				// �ٵ� sql������ �Ѱ��ۿ� ���µ� �̰� ������ �ִ°� �����Ѱ�?
				// �ؿ� ������ pstm���� ���׼����� �����ͺ��̽��� �����ѵ�
				// sql������ �����ְ� �����غ� �����ִµ�
				// pstm.setString(1, seq[0])�� ���¼��� �� ������
				// �����̵ȴ� �׵ڿ� addBatch�� ����ǰ�
				// sql���� �״�� seq = ? ���� �����ִ�
				// �װɴٽ� 1���� 2���� �����鼭 �����̵Ǹ� addBatch�� ���
				// ��������ִ°��̴�.
				// ���������� executeBatch �ؿ� �����ҰŴ�. 
				pstm.addBatch();
				// �޸𸮿� ���� ��, executeBatch() �޼ҵ尡 ȣ��� �� �ѹ��� ����
				System.out.println("������ ��ȣ : " +seq[i]);
			}
			System.out.println("3 ���� �غ� : " + sql);
			
			cnt = pstm.executeBatch();	// addBatch���� �޾��� �������� �ѹ��� ����
			// addBatch�� �޸𸮿� �����س��� ��Ƽ� �ѹ��� �����ϴ°Ŷ� ���� �����Ƿ� �迭�� �����
			
			System.out.println("4 ���� ���� �� ����");
			
			// ���� : -2 / ���� : -3
			// ���� ���� ���� ����
			for (int i = 0; i < cnt.length; i++) {
				if(cnt[i] == -2) {
					res++;
				}
			}
			// �ᱹ ��ũ�Ѱ͵��� �� �� �����Ǹ� res�� ���̶�
			// seq.length�� ���̶� ����
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
