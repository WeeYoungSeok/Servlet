package com.mvc.model.biz;

import java.util.List;

import com.mvc.model.dto.LoginBoardDto;

public interface LoginBoardBiz {

	
	public LoginBoardDto login(String myid, String mypw);
	public List<LoginBoardDto> selectList();
	public List<LoginBoardDto> selectEnabled();
	public int updateUserRole(LoginBoardDto dto);
	public LoginBoardDto idCheck(String myid);
	public int insertUser(LoginBoardDto dto);
	public LoginBoardDto selectUser();
	public int updateUser(LoginBoardDto dto);
	public int deleteUser(int myno);
}
