package jsh.spring.project.domain.member.api;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jsh.spring.project.domain.member.dto.LoginRequest;
import jsh.spring.project.domain.member.dto.MemberPasswordChangeRequest;
import jsh.spring.project.domain.member.dto.MemberProfileUpdateRequest;
import jsh.spring.project.domain.member.dto.MemberResponse;
import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;
import jsh.spring.project.domain.member.service.MemberProfileService;
import jsh.spring.project.domain.member.service.MemberRegisterService;
import jsh.spring.project.domain.member.service.MemberSearchService;

@Controller
@RequestMapping("/member")
public class MemberApi {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberApi.class);
	
	@Inject
	private MemberRegisterService memberRegisterService;
	
	@Inject
	private MemberSearchService memberSearchService;
	
	@Inject
	private MemberProfileService memberProfileService;
	
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
	public String login(Model model, HttpSession session, LoginRequest dto) {
		//view 단에서 영어로 보내도록 하자(비밀번호가 한글로 들어오니까 오류발생)
		//session에 member 객체를 담아주기 전에 이미 session에 담겨있는지 확인
		if(session.getAttribute("member") != null) {
			session.removeAttribute("member");
		}
		//MemberResponse객체를 이용하자. 
		MemberResponse memberResponse = memberSearchService.signIn(dto);
		
		//email인증이 완료되지 않았다면
		if(memberResponse.getMember_authstatus().equals("0")) {
			model.addAttribute("email", memberResponse.getMember_eamil());
			return "memberPages/sendEmail";
		}
		
		session.setAttribute("memberResponse", memberResponse);
		return "home";
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "memberPages/login";
	}

	// member 정보 보기(필요없을 듯함. 세션에 담겨있는 값으로만 표시해도 충분할듯[이메일,닉네임 정보])
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(HttpSession session) {
		return "memberPages/profile";
	}

	// member정보 수정
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profileUpdate(MemberProfileUpdateRequest dto) {
		memberProfileService.updateProfile(dto);
		return "";
	}
	
	//member 비밀번호 변경
	@RequestMapping(value = "/passwordChange", method = RequestMethod.POST)
	public String passwordChange(MemberPasswordChangeRequest dto) {
		memberProfileService.changePassword(dto);
		return "";
	}
	

}
