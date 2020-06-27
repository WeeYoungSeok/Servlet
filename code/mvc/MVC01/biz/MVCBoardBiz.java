package com.mvc.model.biz;

import java.util.List;

import com.mvc.model.dao.MVCBoardDao;
import com.mvc.model.dto.MVCBoardDto;

public class MVCBoardBiz {

	private MVCBoardDao dao = new MVCBoardDao();
	
	public List<MVCBoardDto> selectList() {
		return dao.selectList();
	}
	
	public MVCBoardDto selectOne(int seq) {
		return dao.selectOne(seq);
	}
	
	public int insert(MVCBoardDto dto) {
		return dao.insert(dto);
	}
	
	public int update(MVCBoardDto dto) {
		return dao.update(dto);
	}
	
	public int delete(int seq) {
		return dao.delete(seq);
	}
	
}
