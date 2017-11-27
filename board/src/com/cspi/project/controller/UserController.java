package com.cspi.project.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cspi.project.domain.LoginDTO;
import com.cspi.project.domain.UserVO;
import com.cspi.project.service.UserService;
import com.cspi.project.domain.UserDTO;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/user/login")
	public void login() {
	}

	@RequestMapping("/user/loginpost")
	public void login(LoginDTO loginDTO, HttpSession session, Model model) {
		// 로그인을 시도
		UserVO vo = userService.login(loginDTO);
		if (loginDTO.isUseCookie()) {
			String id = loginDTO.getId();
			String sessionKey = session.getId();

			// 일주일 후
			Date sessionLimit = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
			// 데이터베이스에 세션 키 저장
			userService.updateSession(id, sessionKey, sessionLimit);
		}

		// 로그인 여부를 vo에 저장
		model.addAttribute("userVO", vo);

	}

	@RequestMapping("/user/logout")
	public String logout(HttpSession session) {
		// 세션을 삭제하는 작업 - 로그아웃
		session.removeAttribute("LOGIN");
		return "redirect:/";
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	public void register() {

	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public String register(UserDTO dto, HttpServletRequest request, RedirectAttributes attr) {
		userService.register(dto, request);
		attr.addFlashAttribute("msg", "회원가입에 성공하셨습니다!");
		return "redirect:/";
	}

}
