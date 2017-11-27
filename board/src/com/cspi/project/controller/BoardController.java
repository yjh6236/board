package com.cspi.project.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cspi.project.controller.HomeController;
import com.cspi.project.dao.BoardDao;
import com.cspi.project.domain.BoardVO;
import com.cspi.project.domain.Criteria;
import com.cspi.project.domain.PageMaker;
import com.cspi.project.domain.SearchCriteria;
import com.cspi.project.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardDao boardDao;
	
	
	@RequestMapping(value = "/board/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) {
	}

	@RequestMapping(value = "/board/register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, RedirectAttributes rttr) {

		boardService.insert(request);
		rttr.addFlashAttribute("msg", "게시글 작성에 성공하셨습니다.");
		return "redirect:/board/list";

	}

	/*
	 * @RequestMapping(value="/board/list", method = RequestMethod.GET) public
	 * void list(Model model){ model.addAttribute("list", boardService.list());
	 * }
	 */
	
	/*
	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public void detail(@RequestParam("bno") int bno, Model model) {
		model.addAttribute("vo", boardService.detail(bno));
	}
	*/
	
	@RequestMapping("/board/detail")
	public String getDetail(@RequestParam("bno") int bno,
			@ModelAttribute("criteria") SearchCriteria criteria
			,Model model){
		BoardVO vo = boardService.detail(bno);
		model.addAttribute("vo", vo);
		return "/board/detail";
	}
	
	/*
	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) {
		boardService.delete(bno);
		rttr.addFlashAttribute("msg", "게시글 삭제에 성공하셨습니다.");
		return "redirect:/board/list";
	}
	*/
	
	@RequestMapping("/board/delete/{bno}")
	public String delete(@PathVariable int bno,
			RedirectAttributes attr){
		boardService.delete(bno);
		//목록 보기로 이동 - redirect
		attr.addFlashAttribute("msg", "게시글 삭제에 성공하셨습니다.");
		return "redirect:/board/list";
	}

	
	/*
	@RequestMapping(value = "/board/update", method = RequestMethod.GET)
	public void updateGET(int bno, Model model) throws Exception {
		model.addAttribute("vo", boardService.updateView(bno));
	}
	*/
	
	@RequestMapping("/board/update/{bno}")
	public String update(@PathVariable int bno,
			Model model){
		BoardVO vo = boardService.detail(bno);
		model.addAttribute("vo", vo);

		return "/board/update";
	}	
	
	/*
	@RequestMapping(value = "/board/update", method = RequestMethod.POST)
	public String updatePOST(HttpServletRequest request, RedirectAttributes rttr) {
		boardService.update(request);
		rttr.addFlashAttribute("msg", "게시글 수정에 성공하셨습니다.");
		return "redirect:/board/list";
	}
	*/
	
	@RequestMapping("/board/updateboard")
	public String updateBoard(HttpServletRequest request,
			RedirectAttributes attr){
		boardService.update(request);
		attr.addFlashAttribute("msg", "수정에 성공하셨습니다.");
		
		return "redirect:/board/list";
	}

	// 페이징 처리를 위한 요청 처리 메서드
	/*
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public void list(Criteria criteria, Model model) throws Exception {
		System.out.println(criteria);
		model.addAttribute("list", boardService.list(criteria));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(boardService.totalCount());
		model.addAttribute("pageMaker", pageMaker);
	}
	*/
	
	//페이징 처리를 위한 요청 처리 메서드
			@RequestMapping("/board/list")
			//현재 페이지 번호와 데이터 개수를 파라미터로 받아서 처리
			public String getList(
					@ModelAttribute("criteria") SearchCriteria criteria, Model model){
				//데이터 가져오기
				List<BoardVO> list = boardService.getlist(criteria);
				//데이터를 저장
				model.addAttribute("list", list);
				
				//페이징 처리를 필요한 데이터 가져오기
				//출력을 위해서 생성
				PageMaker pageMaker = new PageMaker();
				//현재 페이지 번호와 출력할 데이터 개수가 pagemaker에 저장
				pageMaker.setCriteria(criteria);
				//pagemaker에 totalcount에 데이터 전체 개수를 설정하고
				//나머지
				pageMaker.setTotalCount(boardService.totalCount(criteria));
				model.addAttribute("pageMaker", pageMaker);
				
				return "/board/list";
			}

}
