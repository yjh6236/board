package com.cspi.project.dao;

import java.util.List;

import com.cspi.project.domain.BoardVO;
import com.cspi.project.domain.Criteria;
import com.cspi.project.domain.SearchCriteria;

public interface BoardDao {

	// 게시글 작성을 위한 메서드 선언
	public void insert(BoardVO vo);

	//public List<BoardVO> list();
	//public List<BoardVO> list(Criteria criteria);
	
	
	// 상세보기를 위한 메서드
	public BoardVO detail(int bno);

	// 상세보기를 수행할 때 조회수를 1 증가시키기 위한 메서드
	public void updateReadcnt(int bno);

	// 글번호를 가지고 게시물을 삭제하는 메서드
	public void delete(int bno);

	// 게시글을 수정하는 메서드
	public void update(BoardVO vo);
	
	//전체 데이터 개수를 가져오는 메서드
	//public int totalCount();
	public int totalCount(SearchCriteria criteria);

	public List<BoardVO> getList(SearchCriteria criteria);
	
	

}
