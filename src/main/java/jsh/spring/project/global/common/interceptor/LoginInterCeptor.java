package jsh.spring.project.global.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jsh.spring.project.domain.member.domain.Member;

public class LoginInterCeptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterCeptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("Controller 요청 전 interceptor preHandel 진입");
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		
		logger.info("**************** request.getMethod() : " + request.getMethod());
		String[] arr = request.getRequestURI().split("/");
		logger.info("**************** request.getRequestURI() : " + arr[1]);
		
		if(arr[1].equals("article") && request.getMethod().equals("GET")) {
			return true;
		}
		
		if(arr[1].equals("reply") && request.getMethod().equals("GET")) {
			return true;
		}
		
		if(member == null) {
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
}
