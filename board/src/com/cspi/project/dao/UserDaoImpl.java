package com.cspi.project.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cspi.project.domain.LoginDTO;
import com.cspi.project.domain.UserVO;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserVO login(LoginDTO dto) {
		
		return sqlSession.selectOne("user.login", dto);
		
	}

	@Override
	public void updateSession(Map<String, Object> map) {
		sqlSession.update("user.updateSession", map);
		
	}

	@Override
	public UserVO checkUser(String sessionKey) {
		return sqlSession.selectOne("user.checkUser", sessionKey);
	}

	
	
	
	
	@Override
	public UserVO idcheck(String id) {
		
		return sqlSession.selectOne("user.idcheck", id);
	}

	@Override
	public void insert(UserVO vo) {
		sqlSession.insert("user.insert", vo);
		
	}

}
