package jsh.spring.project.domain.member.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String register(RegisterRequest dto) throws Exception {
		if(memberRegisterService.singUp(dto)) {
			return "memberPages/login";
		}
		return "memberPages/join";
	}
	
	@RequestMapping(value = "/registerConfirm", method = RequestMethod.GET)
	public String registerConfirm(RegisterConfirmRequest dto) {
		
		logger.info(dto.getEmail());
		logger.info(dto.getAuthKey());
		//DB에 있는 email과 authKey를 비교하고 맞으면 authStatu를 업데이트 해주고 인증이 완료 되었다는 페이지로 보내주자.
		return "memberPage/login";
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
