package com.cspi.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspi.project.dao.ReplyDao;
import com.cspi.project.domain.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDao replyDao;

	@Override
	public List<ReplyVO> list(int bno) {
		return replyDao.list(bno);
	}

	@Override
	public int count(int bno) {
		return replyDao.count(bno);
	}

	@Override
	public void insert(ReplyVO vo) {
		replyDao.insert(vo);
	}

	@Override
	public void update(ReplyVO vo) {
		replyDao.update(vo);
	}

	@Override
	public void delete(int rno) {
		replyDao.delete(rno);
	}
}
