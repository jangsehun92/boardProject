package jsh.spring.project.domain.member.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jsh.spring.project.domain.member.domain.Member;
import jsh.spring.project.domain.member.dto.LoginRequest;
import jsh.spring.project.domain.member.dto.MemberPasswordChangeRequest;
import jsh.spring.project.domain.member.dto.MemberProfileUpdateRequest;
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
	
	@RequestMapping(value = "/email/{email:.+}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> checkEmail(@PathVariable("email")String email){
		logger.info("[ MemberApi ] checkEmail : "+ email);
		return new ResponseEntity<String>(""+memberRegisterService.checkEmail(email), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Model model, RegisterRequest dto) throws Exception {
//		memberRegisterService.checkEmail(dto.getEmail());
		memberRegisterService.singUp(dto);
		model.addAttribute("email", dto.getEmail());
		return "memberPages/sendEmail";
	}
	
	@RequestMapping(value = "/email", method = RequestMethod.GET)
		public String emailPage() {
			return "memberPages/sendEmail";
		}
	
	@RequestMapping(value = "/registerConfirm", method = RequestMethod.GET)
	public String registerConfirm(Model model, RegisterConfirmRequest dto) throws Exception {
		memberRegisterService.updateStatus(dto);
		return "memberPages/login";
	}
	
	@RequestMapping(value = "/resendEmail/{email:.+}", method = RequestMethod.GET)
	public String resendEmail(Model model, String email) throws Exception {
		memberRegisterService.resendEmail(email);
		return "memberPages/sendEmail";
	}
	/*
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session, LoginRequest dto) {
		if(session.getAttribute("member") != null) {
			session.removeAttribute("member");
		}
		Member member = memberSearchService.signIn(dto);
		
		if(!member.checkStatus()) {
			model.addAttribute("email", member.getEmail());
			return "memberPages/sendEmail";
		}
		session.setAttribute("member", member);
		return "home";
	}
	*/
	
	@RequestMapping(value = "/login", produces = "application/json; charset=UTF8", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> login_(Model model, HttpSession session, LoginRequest dto) {
		Member member = memberSearchService.signIn(dto);
		
		//email&password 검색 결과 회원이고, email 인증도 완료한 상태 
		if(member != null && member.checkStatus()) {
			session.invalidate();
			session.setAttribute("member", member);
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		}
		
		//email&password 검색 결과 회원이고, email 인증이 안된 상태
		if(member != null && !member.checkStatus()) {
			session.setAttribute("email", member.getEmail());
			return new ResponseEntity<String>(member.getEmail(), HttpStatus.FOUND);
		}
		//email&password 검색 결과 없을때
		return new ResponseEntity<String>("이메일 또는 비밀번호가 다릅니다.", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "memberPages/login";
	}

	@RequestMapping(value = "/info/{memberId}", method = RequestMethod.GET)
	public String profile(HttpSession session, Model model, @PathVariable("memberId")int memberId, @RequestParam(defaultValue="1")int page) {
		session.removeAttribute("category");
		Map<String, Object> resultMap = new HashMap<>();
		resultMap = memberSearchService.searchMember(memberId, page);
		model.addAttribute("resultMap",resultMap);
		return "memberPages/info";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String profileUpdate(HttpSession session) {
		session.removeAttribute("category");
		return "memberPages/updateProfile";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String profileUpdate(HttpSession session, MemberProfileUpdateRequest dto) {
		Member member = (Member)session.getAttribute("member");
		dto.setId(member.getId());
		memberProfileService.updateProfile(dto);
		
		member.setEmail(dto.getEmail());
		member.setNickname(dto.getNickname());
		
		session.removeAttribute("member");
		session.setAttribute("member", member);
		return "memberPages/updateProfile";
	}
	
	@RequestMapping(value = "/passwordChange", method = RequestMethod.GET)
	public String passwordChange(HttpSession session) throws IOException {
		session.removeAttribute("category");
		return "memberPages/passwordChange";
	}
		
	@RequestMapping(value = "/passwordChange", method = RequestMethod.POST)
	public String passwordChange(HttpSession session, HttpServletResponse response, MemberPasswordChangeRequest dto) throws IOException {
		Member member = (Member)session.getAttribute("member");
		dto.setId(member.getId());
		
		if(!memberProfileService.changePassword(dto)) {
			return "memberPages/passwordChange";
		}
		
		memberProfileService.changePassword(dto);
		session.invalidate();
		return "memberPages/login";
	}

}
