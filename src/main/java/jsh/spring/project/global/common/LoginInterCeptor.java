package jsh.spring.project.global.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jsh.spring.project.domain.member.dto.MemberResponse;

public class LoginInterCeptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterCeptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("Controller 요청 전 interceptor preHandel 진입");
		HttpSession session = request.getSession();
		MemberResponse memberResponse = (MemberResponse)session.getAttribute("memberResponse");
		
		if(memberResponse == null) {
			response.sendRedirect("member/login");
			return false;
		}
		return true;
	}
}
