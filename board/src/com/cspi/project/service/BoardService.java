package com.cspi.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cspi.project.domain.BoardVO;
import com.cspi.project.domain.Criteria;
import com.cspi.project.domain.SearchCriteria;

public interface BoardService {
	
	public void insert(HttpServletRequest request);
	
	//public List<BoardVO> list();
	
	public BoardVO detail(int bno);
	
	public void delete(int bno);
	
	public BoardVO updateView(int bno);
	
	public void update(HttpServletRequest request); 
	
	public int totalCount(SearchCriteria criteria);
	
	public List<BoardVO> getlist(SearchCriteria criteria);



}
