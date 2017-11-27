package com.cspi.project.dao;

import java.util.Map;

import com.cspi.project.domain.LoginDTO;
import com.cspi.project.domain.UserVO;

public interface UserDao {
	// 로그인 처리하는 메서드
	public UserVO login(LoginDTO dto);

	// 쿠키의 유효시간을 업데이트 하는 메서드
	// 매개변수는 유저의 id, 세션값, 유효시간
	public void updateSession(Map<String, Object> map);

	// 쿠키의 값을 가지고 id와 pw를 찾아오는 메서드
	public UserVO checkUser(String sessionKey);

	
	
	// 아이디 중복을 체크할 메서드
	public UserVO idcheck(String id);

	//회원가입 처리 메서드
	public void insert(UserVO vo);
}
