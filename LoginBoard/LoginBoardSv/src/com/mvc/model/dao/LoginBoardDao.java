package com.mvc.model.dao;

import java.util.List;

import com.mvc.model.dto.LoginBoardDto;

public interface LoginBoardDao {

	String LOGIN = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
				 + " FROM MYMEMBER "
				 + " WHERE MYID = ? AND MYPW = ? AND MYENABLED = ? ";
	String idCheck = "";
	String SELECT_LIST_Y = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
						 + " FROM MYMEMBER "
						 + " WHERE MYENABLED = ?";
	String SELECT_LIST = " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
					   + " FROM MYMEMBER "
					   + " ORDER BY MYNO ";
	
	String SELECT_USER = " SELECT MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
					   + " FROM MYMEMBER "
					   + " WHERE MYNO = ?";
	
	
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
