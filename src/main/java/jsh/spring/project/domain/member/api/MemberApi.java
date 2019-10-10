package jsh.spring.project.domain.member.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jsh.spring.project.domain.member.dto.RegisterRequest;
import jsh.spring.project.domain.member.service.MemberRegisterService;

@Controller
@RequestMapping("/member")
public class MemberApi {
	
	
	/*
	@Inject
	private MemberLoginService memberLoginService;
	*/
	
	@Autowired
	private MemberRegisterService memberRegisterService;
	

	// 회원가입
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(RegisterRequest dto) {
		if(memberRegisterService.singUp(dto)) {
			return "memberPages/login";
		}
		return "memberPages/join";
	}

	// 회원탈퇴
	public String delete() {
		return "";
	}

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login() {
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
