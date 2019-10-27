package jsh.spring.project.domain.member.api;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jsh.spring.project.domain.member.domain.Member;
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
		if(session.getAttribute("member") != null) {
			session.removeAttribute("member");
		}
		Member member = memberSearchService.signIn(dto);
		
		if(member.checkStatus()) {
			session.setAttribute("member", member);
			return "home";
		}
		
		model.addAttribute("email", member.getEmail());
		return "memberPages/sendEmail";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "memberPages/login";
	}

	@RequestMapping(value = "/info/{number}", method = RequestMethod.GET)
	public String profile(HttpSession session, Model model, @PathVariable int number) {
		MemberResponse memberResponse = memberSearchService.searchMember(number);
		model.addAttribute("memberResponse", memberResponse);
		//게시판 service에서 사용자가 작성한 글을 가져와야한다.
		return "memberPages/info";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String profileUpdate() {
		return "memberPages/updateProfile";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String profileUpdate(HttpSession session, MemberProfileUpdateRequest dto) {
		Member member = (Member)session.getAttribute("member");
		dto.setNumber(member.getNumber());
		memberProfileService.updateProfile(dto);
		
		member.setEmail(dto.getEmail());
		member.setNickname(dto.getNickname());
		
		session.removeAttribute("member");
		session.setAttribute("member", member);
		return "memberPages/updateProfile";
	}
	
	@RequestMapping(value = "/passwordChange", method = RequestMethod.GET)
	public String passwordChange() {
		return "memberPages/passwordChange";
	}
		
	@RequestMapping(value = "/passwordChange", method = RequestMethod.POST)
	public String passwordChange(HttpSession session, MemberPasswordChangeRequest dto) {
		Member member = (Member)session.getAttribute("member");
		logger.info("before 해당 회원 번호 : " + dto.getNumber());
		logger.info("기존 비밀번호 : " + dto.getPassword());
		logger.info("새 비밀번호 : " + dto.getNewPassword());
		
		dto.setNumber(member.getNumber());
		logger.info("after 해당 회원 번호 : " + dto.getNumber());
		memberProfileService.changePassword(dto);
		session.invalidate();
		return "memberPages/login";
	}
}
