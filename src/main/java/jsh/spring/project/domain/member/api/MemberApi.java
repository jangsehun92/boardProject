package jsh.spring.project.domain.member.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;
import jsh.spring.project.domain.member.service.MemberRegisterService;

@Controller
@RequestMapping("/member")
public class MemberApi {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberApi.class);
	
	@Autowired
	private MemberRegisterService memberRegisterService;
	
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
	
	@RequestMapping(value = "resendEmail/{email}", method = RequestMethod.GET)
	public String resendEmail(Model model, @PathVariable("email") String email) throws Exception {
		memberRegisterService.resendEmail(email);
		model.addAttribute("email", email);
		return "memberPages/sendEmail";
	}

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login() {
		//이메일 인증이 된 member
		
		//이메일 인증이 되지 않은 member
		return "";
	}

	// 로그아웃
	public String logout() {
		return "";
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
