package jsh.spring.project.domain.member.service;

import jsh.spring.project.domain.member.dto.LoginRequest;
import jsh.spring.project.domain.member.dto.MemberResponse;

public interface MemberSearchService {
	//login하는방법이 두가지 이상 (일반 로그인, SNS 로그인)
	public MemberResponse signIn(LoginRequest dto);
}
