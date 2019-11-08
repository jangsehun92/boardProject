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
		logger.info("[LoginInterCeptor] 로그인 필요한 요청 들어옴");
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		String[] arr = request.getRequestURI().split("/");
		logger.info("[LoginInterCeptor] request.getRequestURI() : " + request.getMethod() +" "+ request.getRequestURI());
		
		if(arr[1].equals("article") && request.getMethod().equals("GET")) {
			logger.info("[LoginInterCeptor] 게시글 요청");
			return true;
		}
		
		if(arr[1].equals("reply") && request.getMethod().equals("GET")) {
			logger.info("[LoginInterCeptor] 댓글 요청");
			return true;
		}
		
		if(member == null) {
			logger.info("[LoginInterCeptor] 허용되지않은 요청 ");
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
}
