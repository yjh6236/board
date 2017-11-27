package com.cspi.project.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspi.project.dao.UserDao;
import com.cspi.project.domain.LoginDTO;
import com.cspi.project.domain.UserDTO;
import com.cspi.project.domain.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserVO login(LoginDTO dto) {
		
		return userDao.login(dto);
	}

	@Override
	public void updateSession(String id, String sessionKey, Date sessionLimit) {
		Map<String,Object> map = 
				new HashMap<String, Object>();
		map.put("id", id);
		map.put("sessionkey", sessionKey);
		map.put("sessionlimit", sessionLimit);
		
		userDao.updateSession(map);
		
	}

	@Override
	public UserVO userCheck(String sessionKey) {
		return userDao.checkUser(sessionKey);	
	}

	
	
	
	//회원가입
	@Override
	public Map<String, Object> idCheck(String id) {
		UserVO vo = userDao.idcheck(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if(vo == null){
			map.put("result", "true");
		}
		else{
			map.put("result", "false");
		}
		return map;
	}

	@Override
	public void register(UserDTO dto, HttpServletRequest request) {

		UserVO vo = new UserVO();
		
		vo.setId(dto.getId());
		vo.setPw(dto.getPw());
		vo.setName(dto.getName());
		vo.setPhone(dto.getPhone());
		
		userDao.insert(vo);
		
	}

}
