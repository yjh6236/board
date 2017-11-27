package com.cspi.project.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cspi.project.domain.ReplyVO;

@Repository
public class ReplyDaoImpl implements ReplyDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ReplyVO> list(int bno) {
		
		return sqlSession.selectList("reply.list", bno);
	}

	@Override
	public int count(int bno) {
		return sqlSession.selectOne("reply.cnt", bno);
	}

	@Override
	public void insert(ReplyVO vo) {
		sqlSession.insert("reply.insert", vo);
	}

	@Override
	public void update(ReplyVO vo) {
		sqlSession.update("reply.update", vo);
	}

	@Override
	public void delete(int rno) {
		sqlSession.delete("reply.delete", rno);
	}

}
