package com.mvc.model.biz;

import java.util.List;

import com.mvc.model.dao.LoginBoardDao;
import com.mvc.model.dao.LoginBoardDaoImpl;
import com.mvc.model.dto.LoginBoardDto;

public class LoginBoardBizImpl implements LoginBoardBiz {

	
	private LoginBoardDao dao = new LoginBoardDaoImpl();
	
	public LoginBoardDto login(String myid, String mypw) {
		return dao.login(myid, mypw);
	}
	public List<LoginBoardDto> selectList(){
		return dao.selectList();
	}
	
	public List<LoginBoardDto> selectEnabled() {
		return dao.selectEnabled();
	}
	
	public int updateUserRole(LoginBoardDto dto) {
		return dao.updateUserRole(dto);
	}
	
	public LoginBoardDto idCheck(String myid) {
		return dao.idCheck(myid);
	}
	
	public int insertUser(LoginBoardDto dto) {
		return dao.insertUser(dto);
	}
	
	public LoginBoardDto selectUser() {
		return dao.selectUser();
	}
	
	public int updateUser(LoginBoardDto dto) {
		return dao.updateUser(dto);
	}
	
	public int deleteUser(int myno) {
		return dao.deleteUser(myno);
	}
}
