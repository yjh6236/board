package com.cspi.project.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cspi.project.domain.UserVO;


public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// 로그인 시도 후 리턴되는 데이터를 가져오기
		ModelMap modelMap = arg3.getModelMap();
		Object userVO = modelMap.get("userVO");

		HttpSession session = arg0.getSession();
		if (userVO == null) {
			// 로그인 실패한 경우
			session.setAttribute("fail", "아이디가 없거나 비밀번호가 일치하지 않습니다.");
			arg1.sendRedirect("/project/user/login");

		} else {
			session.removeAttribute("fail");
			session.setAttribute("LOGIN", userVO);
			
			
			// 자동 로그인 체크여부를 확인해서 쿠키를 브라우저에 저장
			// System.out.println(arg0.getParameter("useCookie"));
			if (arg0.getParameter("useCookie") != null) {
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				// 쿠키를 사용할 수 있는 패스 설정
				loginCookie.setPath("/");
				// 쿠키의 유효시간은 일주일
				loginCookie.setMaxAge(60 * 60 * 24 * 7);
				// System.out.println(loginCookie);
				// 쿠키 저장
				arg1.addCookie(loginCookie);
			} else {
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				// 쿠키를 사용할 수 있는 패스 설정
				loginCookie.setPath("/");
				// 쿠키의 유효시간은 없음 - 쿠키삭제
				loginCookie.setMaxAge(0);
				// 쿠키 저장
				arg1.addCookie(loginCookie);
			}
			
			
			// 로그인이 필요한 요청에 의해서 온 것인지 확인하기 위해서
			// session에 저장한 dest 값을 가져오기
			Object dest = arg0.getSession().getAttribute("dest");
			UserVO vo = (UserVO) session.getAttribute("LOGIN");

			if (vo.getId().equals("system")) {

				arg1.sendRedirect((String) dest);
			} 
			else {

				arg1.sendRedirect("../");
			}

		}

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {

		// 세션에 로그인 정보가 남아있으면 삭제
		HttpSession session = arg0.getSession();
		if (session.getAttribute("LOGIN") != null) {
			session.removeAttribute("LOGIN");

		}
		// true 리턴 시 controller로 처리하고 이동하고
		// false를 리턴하면 controller로 가지 않습니다.
		return true;

	}
}
