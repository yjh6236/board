package com.cspi.project.service;

import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cspi.project.domain.LoginDTO;
import com.cspi.project.domain.UserVO;
import com.cspi.project.domain.UserDTO;

public interface UserService {

	// 로그인을 처리해주는 메서드
	public UserVO login(LoginDTO logindto);

	public void updateSession(String id, String sessionKey, Date sessionLimit);

	// 자동로그인이 체크되어 있어서 쿠키에 세션의 값을 저장 했을 때
	// 로그인을 시도하면 자동로그인 할 수 있도록 아이디와 비밀번호를 찾아오는 메서드
	public UserVO userCheck(String sessionKey);
	
	
	
	
	// id 중복체크를 해주는 메서드
	// 결과를 맵으로 리턴
	public Map<String, Object> idCheck(String id);

	// 회원가입을 처리해주는 메서드
	// userDTO는 사용자가 입력한 내용을
	// HttpServletRequest 는 파일 업로드 처리를 위해서
	public void register(UserDTO dto, HttpServletRequest request);

}
