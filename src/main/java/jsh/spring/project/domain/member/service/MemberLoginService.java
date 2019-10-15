package jsh.spring.project.domain.member.service;

import jsh.spring.project.domain.member.domain.Member;
import jsh.spring.project.domain.member.dto.LoginRequest;

public interface MemberLoginService {
	//login하는방법이 두가지 이상 (일반 로그인, SNS 로그인)
	
	public Member signin(LoginRequest dto);
}
