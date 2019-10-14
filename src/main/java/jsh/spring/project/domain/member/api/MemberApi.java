package jsh.spring.project.domain.member.api;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jsh.spring.project.domain.member.dto.LoginRequest;
import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;
import jsh.spring.project.domain.member.service.MemberLoginService;
import jsh.spring.project.domain.member.service.MemberRegisterService;

@Controller
@RequestMapping("/member")
public class MemberApi {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberApi.class);
	
	@Autowired
	private MemberRegisterService memberRegisterService;
	
	@Autowired
	private MemberLoginService memberLoginService;
	
	// 회원가입
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Model model, RegisterRequest dto) throws Exception {
		memberRegisterService.singUp(dto);
		model.addAttribute("email", dto.getMember_email());
		return "memberPages/sendEmail";
	}
	
	@RequestMapping(value = "/registerConfirm", method = RequestMethod.GET)
	public String registerConfirm(Model model, RegisterConfirmRequest dto) {
		memberRegisterService.updateAuthStatus(dto);
		return "memberPages/login";
	}
	
	//post방식으로 바꾸기(Object로 받기)
	@RequestMapping(value = "resendEmail/{email}", method = RequestMethod.GET)
	public String resendEmail(Model model, @PathVariable("email") String email) throws Exception {
		memberRegisterService.resendEmail(email);
		model.addAttribute("email", email);
		return "memberPages/sendEmail";
	}

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, LoginRequest dto) {
		if(memberLoginService.signin().getMember_authstatues() == "0") {
			return "memberPages/sendEmail";
		}
		return "home";
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "memberPages/login";
	}

	// member 정보 보기
	public String profile() {
		return "";
	}

	// member정보 수정
	public String profileUpdate() {
		return "";
	}

}
