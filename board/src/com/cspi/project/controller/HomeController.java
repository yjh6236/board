package com.cspi.project.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cspi.project.dao.BoardDao;
import com.cspi.project.domain.BoardVO;
import com.cspi.project.service.BoardService;

@Controller
public class HomeController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private BoardDao boardDao;
	/*
	 * private static final Logger logger =
	 * LoggerFactory.getLogger(HomeController.class);
	 * 
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * home(Locale locale, Model model) {
	 * logger.info("Welcome home! The client locale is {}.", locale);
	 * 
	 * Date date = new Date(); DateFormat dateFormat =
	 * DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	 * 
	 * String formattedDate = dateFormat.format(date);
	 * 
	 * model.addAttribute("serverTime", formattedDate );
	 * 
	 * /* BoardVO vo = new BoardVO();
	 * 
	 * vo.setBno(100); vo.setContent("강아지를 찾아주세요"); vo.setName("강백호");
	 * vo.setPhone("01093301234"); vo.setTitle("강아지를 찾아주세요");
	 * boardDao.insert(vo);
	 * 
	 * System.out.println(vo); //System.out.println(boardService);
	 */

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "home";
		
	}

}
