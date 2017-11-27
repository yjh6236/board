package com.cspi.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cspi.project.dao.BoardDao;
import com.cspi.project.dao.ReplyDao;
import com.cspi.project.domain.BoardVO;
import com.cspi.project.domain.Criteria;
import com.cspi.project.domain.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private ReplyDao replyDao;

	@Override
	public void insert(HttpServletRequest request) {
		// 매개변수가 request일 때는 파라미터를 읽습니다.
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		String phone = request.getParameter("phone");

		if (title.length() == 0) {
			title = "무제";
		}
		if (content.length() == 0) {
			title = "냉무";
		}

		// 필요한 데이터를 생성
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setid(id);
		vo.setPhone(phone);

		// DAO의 메서드를 호출
		boardDao.insert(vo);

	}

	/*
	 * @Override public List<BoardVO> list() {
	 * 
	 * return boardDao.list(); }
	 */

	@Override
	public BoardVO detail(int bno) {
		boardDao.updateReadcnt(bno);
		return boardDao.detail(bno);

	}

	@Override
	public void delete(int bno) {
		boardDao.delete(bno);

	}

	@Override
	public BoardVO updateView(int bno) {
		return boardDao.detail(bno);

	}

	@Override
	public void update(HttpServletRequest request) {
		// 매개변수가 request일 때는 파라미터를 읽습니다.
		int bno = Integer.parseInt(request.getParameter("bno"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		if (title.length() == 0) {
			title = "무제";
		}
		if (content.length() == 0) {
			content = "냉무";
		}
		// 필요한 데이터를 생성
		String phone = request.getParameter("phone");
		BoardVO vo = new BoardVO();
		vo.setBno(bno);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setPhone(phone);
		boardDao.update(vo);

	}

	@Override
	public int totalCount(SearchCriteria criteria) {
		if (criteria.getKeyword() != null)
			criteria.setKeyword(criteria.getKeyword().toUpperCase());
		return boardDao.totalCount(criteria);
	}

	@Override
	public List<BoardVO> getlist(SearchCriteria criteria) {
		if (criteria.getKeyword() != null)
			criteria.setKeyword(criteria.getKeyword().toUpperCase());
		List<BoardVO> list = boardDao.getList(criteria);

		// list의 모든 데이터 순회하면서 댓글 개수 저장
		for (BoardVO vo : list) {
			int r = replyDao.count(vo.getBno());
			vo.setReplyCnt(r);
		}

		return list;
	}

	/*
	 * @Override public int totalCount() throws Exception { return
	 * boardDao.totalCount(); }
	 * 
	 * @Override public List<BoardVO> list(Criteria criteria) { return
	 * boardDao.list(criteria); }
	 */

}
