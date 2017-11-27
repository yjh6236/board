package com.cspi.project.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cspi.project.domain.BoardVO;
import com.cspi.project.domain.Criteria;
import com.cspi.project.domain.SearchCriteria;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(BoardVO vo) {

		sqlSession.insert("board.insert", vo);
		
	}
	
	/*
	@Override
	public List<BoardVO> list() {
		
		return sqlSession.selectList("board.list");
	}
	*/
	
	@Override
	public BoardVO detail(int bno) {
		
		return sqlSession.selectOne("board.detail", bno);

	}

	@Override
	public void updateReadcnt(int bno) {
		sqlSession.update("board.updateReadcnt", bno);

		
	}

	@Override
	public void delete(int bno) {
		sqlSession.delete("board.delete", bno);

		
	}

	@Override
	public void update(BoardVO vo) {
		sqlSession.delete("board.update", vo);

		
	}

	

	@Override
	public int totalCount(SearchCriteria criteria) {
		return sqlSession.selectOne("board.totalCount", criteria);
	}

	@Override
	public List<BoardVO> getList(SearchCriteria criteria) {
		return sqlSession.selectList(
				"board.getList", criteria);
	
	}

	
	/*
	@Override
	public List<BoardVO> list(Criteria criteria) {
		return sqlSession.selectList("board.getList", criteria);
	}
	*/
	
	/*
	@Override
	public int totalCount() {
		return sqlSession.selectOne("board.totalCount");
	}
	*/

}
