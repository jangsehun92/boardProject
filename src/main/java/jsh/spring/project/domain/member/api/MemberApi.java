package jsh.spring.project.domain.member.api;

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
	
	private final MemberRegisterService memberRegisterService;
	
	private final MemberSearchService memberSearchService;
	
	private final MemberProfileService memberProfileService;
	
	public MemberApi(MemberRegisterService memberRegisterService, MemberSearchService memberSearchService, MemberProfileService memberProfileService) {
		this.memberRegisterService = memberRegisterService;
		this.memberSearchService = memberSearchService;
		this.memberProfileService = memberProfileService;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Model model, RegisterRequest dto) throws Exception {
		memberRegisterService.singUp(dto);
		model.addAttribute("email", dto.getEmail());
		return "memberPages/sendEmail";
	}
	
	@RequestMapping(value = "/registerConfirm", method = RequestMethod.GET)
	public String registerConfirm(Model model, RegisterConfirmRequest dto) throws Exception {
		memberRegisterService.updateStatus(dto);
		return "memberPages/login";
	}
	
	@RequestMapping(value = "resendEmail/{email:.+}", method = RequestMethod.GET)
	public String resendEmail(Model model, @PathVariable("email") String email) throws Exception {
		memberRegisterService.resendEmail(email);
		model.addAttribute("email", email);
		return "memberPages/sendEmail";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session, LoginRequest dto) {
		//view 단에서 정규식을 통해 영어로 보내도록 하자(비밀번호가 한글로 들어오니까 오류발생)
		if(session.getAttribute("memberResponse") != null) {
			session.removeAttribute("memberResponse");
		}
		MemberResponse memberResponse = memberSearchService.signIn(dto);
		
		if(memberResponse.checkStatus()) {
			session.setAttribute("memberResponse", memberResponse);
			return "home";
		}
		
		model.addAttribute("email", memberResponse.getEmail());
		return "memberPages/sendEmail";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "memberPages/login";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(HttpSession session, Model model) {
		if(session.getAttribute("member") != null) {
			return "memberPages/profile";
		}
		return "memberPages/login";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profileUpdate(HttpSession session, MemberProfileUpdateRequest dto) {
		memberProfileService.updateProfile(dto);
		return "memberPages/profile";
	}
	
	//member 비밀번호 변경
	@RequestMapping(value = "/passwordChange", method = RequestMethod.POST)
	public String passwordChange(HttpSession session, MemberPasswordChangeRequest dto) {
		memberProfileService.changePassword(dto);
		if(session.getAttribute("memberResponse") == null) {
			session.invalidate();
		}
		return "memberRages/login";
	}
	

}
