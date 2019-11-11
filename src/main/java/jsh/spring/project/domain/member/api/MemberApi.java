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
		return memberRegisterService.updateStatus(dto)==true?"memberPages/login":"memberPages/authComplete";
	}
	
	@RequestMapping(value = "/resendEmail", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> resendEmail(Model model, HttpSession session) throws Exception {
		try {
			memberRegisterService.resendEmail((String)session.getAttribute("email"));
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/login", produces = "application/json; charset=UTF8", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> login(Model model, HttpSession session, LoginRequest dto) {
		Member member = memberSearchService.signIn(dto);
		
		if(member != null && member.checkStatus()) {
			session.removeAttribute("eamil");
			session.setAttribute("member", member);
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		}
		
		if(member != null && !member.checkStatus()) {
			session.setAttribute("email", member.getEmail());
			return new ResponseEntity<String>(member.getEmail(), HttpStatus.FOUND);
		}
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
		
	@RequestMapping(value = "/passwordChange", produces = "application/json; charset=UTF8", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> passwordChange(HttpSession session, HttpServletResponse response, MemberPasswordChangeRequest dto) throws IOException {
		Member member = (Member)session.getAttribute("member");
		dto.setId(member.getId());
		
		if(memberProfileService.changePassword(dto)) {
			session.invalidate();
			return new ResponseEntity<String>("login", HttpStatus.OK);
		}
		return new ResponseEntity<String>("passwordChange", HttpStatus.NOT_FOUND);
		
	}

}
