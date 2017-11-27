package com.cspi.project.service;

import java.util.List;

import com.cspi.project.domain.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> list(int bno);
	
	//원본 글번호를 가지고 댓글의 개수를 찾아오는 메서드
	public int count(int bno);
	
	//댓글을 삽입하는 메서드
	public void insert(ReplyVO vo);
	
	//댓글을 수정하는 메서드
	public void update(ReplyVO vo);
	
	//댓글을 삭제하는 메서드
	public void delete(int rno);

}
