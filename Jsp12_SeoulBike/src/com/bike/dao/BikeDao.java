package com.bike.dao;

import static com.bike.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.bike.dto.BikeDto;

public class BikeDao {

	public int insertAll(List<BikeDto> bikes) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		int[] cnt = null;
		String sql = " INSERT INTO BIKE_TB "
				   + " VALUES (?,?,?,?,?,?,?) ";
		
		try {
			pstm = con.prepareStatement(sql);
			
			for(int i = 0; i < bikes.size(); i++) {
				pstm.setString(1, bikes.get(i).getAddr_gu());
				pstm.setInt(2, bikes.get(i).getContent_id());
				pstm.setString(3, bikes.get(i).getContent_nm());
				pstm.setString(4, bikes.get(i).getNew_addr());
				pstm.setInt(5, bikes.get(i).getCradle_count());
				pstm.setDouble(6, bikes.get(i).getLongitude());
				pstm.setDouble(7, bikes.get(i).getLatitude());
				
				pstm.addBatch();
			}
			
			// for(BikeDto dto : bikes) {
			// 		pstm.setString(1,dto.getAddr_gu());
			//		.. 이렇게도가능
			//		pstm.addBatch();
			// }
			// 성공 -2, 실패 -3 [-2,-2,....
			cnt = pstm.executeBatch();
			
			for(int i = 0; i < cnt.length; i++) {
				if(cnt[i] == -2 ) {
					res++;
				}
			}
			
			if(bikes.size() == res) {
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
		return (res == bikes.size())? 1 : 0;
		// 전부 성공했으면 1리턴 하나라도 실패했으면 0리턴
	}
	
	public boolean delete() {
		Connection con = getConnection();
		Statement stmt = null;
		int res = 0;
		
		String sql = " DELETE FROM BIKE_TB ";
		
		try {
			stmt = con.createStatement();
			
			res = stmt.executeUpdate(sql);
			
			if(res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con,stmt);
		}
		
		
		return (res > 0)? true : false;
	}
}
