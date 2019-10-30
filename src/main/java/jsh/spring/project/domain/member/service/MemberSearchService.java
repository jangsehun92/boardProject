package jsh.spring.project.domain.member.service;

import java.util.Map;

import jsh.spring.project.domain.member.domain.Member;
import jsh.spring.project.domain.member.dto.LoginRequest;

public interface MemberSearchService {
	//login하는방법이 두가지 이상 (일반 로그인, SNS 로그인)
	public Member signIn(LoginRequest dto);
	
	public Map<String, Object> searchMember(int memberId, int page);
}
