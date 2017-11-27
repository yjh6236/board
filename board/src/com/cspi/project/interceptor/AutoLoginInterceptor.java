package com.cspi.project.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.cspi.project.dao.UserDao;
import com.cspi.project.domain.UserVO;


public class AutoLoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserDao userDAO;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		
		//스프링에서 특정 쿠키를 찾기
		Cookie cookie = WebUtils.getCookie(arg0, "loginCookie");
		HttpSession session = arg0.getSession();
		
		if(session.getAttribute("autologin")==null && cookie != null){
			UserVO vo = userDAO.checkUser(cookie.getValue());
			session.setAttribute("LOGIN", vo);
			//자동 로그인을 수행하면 autologin에 값을 저장
			session.setAttribute("autologin", 1);
			
		}
		return true;
	}

}
