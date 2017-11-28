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
		rttr.addFlashAttribute("msg", "寃뚯떆湲� �옉�꽦�뿉 �꽦怨듯븯�뀲�뒿�땲�떎.");
		return "redirect:/board/list";

	}

	/*
	 * @RequestMapping(value="/board/list", method = RequestMethod.GET) public
	 * void list(Model model){ model.addAttribute("list", boardService.list());
	 * }
	 * 로컬과 원격저장소가 다름을 보는 테스트, 로컬과 원격저장소가 다름을 보는 테스트
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
		rttr.addFlashAttribute("msg", "寃뚯떆湲� �궘�젣�뿉 �꽦怨듯븯�뀲�뒿�땲�떎.");
		return "redirect:/board/list";
	}
	*/
	
	@RequestMapping("/board/delete/{bno}")
	public String delete(@PathVariable int bno,
			RedirectAttributes attr){
		boardService.delete(bno);
		//紐⑸줉 蹂닿린濡� �씠�룞 - redirect
		attr.addFlashAttribute("msg", "寃뚯떆湲� �궘�젣�뿉 �꽦怨듯븯�뀲�뒿�땲�떎.");
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
		rttr.addFlashAttribute("msg", "寃뚯떆湲� �닔�젙�뿉 �꽦怨듯븯�뀲�뒿�땲�떎.");
		return "redirect:/board/list";
	}
	*/
	
	@RequestMapping("/board/updateboard")
	public String updateBoard(HttpServletRequest request,
			RedirectAttributes attr){
		boardService.update(request);
		attr.addFlashAttribute("msg", "�닔�젙�뿉 �꽦怨듯븯�뀲�뒿�땲�떎.");
		
		return "redirect:/board/list";
	}

	// �럹�씠吏� 泥섎━瑜� �쐞�븳 �슂泥� 泥섎━ 硫붿꽌�뱶
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
	
	//�럹�씠吏� 泥섎━瑜� �쐞�븳 �슂泥� 泥섎━ 硫붿꽌�뱶
			@RequestMapping("/board/list")
			//�쁽�옱 �럹�씠吏� 踰덊샇�� �뜲�씠�꽣 媛쒖닔瑜� �뙆�씪誘명꽣濡� 諛쏆븘�꽌 泥섎━
			public String getList(
					@ModelAttribute("criteria") SearchCriteria criteria, Model model){
				//�뜲�씠�꽣 媛��졇�삤湲�
				List<BoardVO> list = boardService.getlist(criteria);
				//�뜲�씠�꽣瑜� ���옣
				model.addAttribute("list", list);
				
				//�럹�씠吏� 泥섎━瑜� �븘�슂�븳 �뜲�씠�꽣 媛��졇�삤湲�
				//異쒕젰�쓣 �쐞�빐�꽌 �깮�꽦
				PageMaker pageMaker = new PageMaker();
				//�쁽�옱 �럹�씠吏� 踰덊샇�� 異쒕젰�븷 �뜲�씠�꽣 媛쒖닔媛� pagemaker�뿉 ���옣
				pageMaker.setCriteria(criteria);
				//pagemaker�뿉 totalcount�뿉 �뜲�씠�꽣 �쟾泥� 媛쒖닔瑜� �꽕�젙�븯怨�
				//�굹癒몄�
				pageMaker.setTotalCount(boardService.totalCount(criteria));
				model.addAttribute("pageMaker", pageMaker);
				
				return "/board/list";
			}

}
