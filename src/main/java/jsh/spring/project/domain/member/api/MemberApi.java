package jsh.spring.project.domain.member.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jsh.spring.project.domain.member.dto.ResiterRequest;

@Controller
@RequestMapping("/member")
public class MemberApi {

	// 회원가입
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(ResiterRequest resiterRequest) {
		System.out.println(resiterRequest.getMember_id());
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
