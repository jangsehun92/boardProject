package jsh.spring.project.domain.member.service;

import java.util.Map;

import jsh.spring.project.domain.member.domain.Member;
import jsh.spring.project.domain.member.dto.LoginRequest;

public interface MemberSearchService {
	public Member signIn(LoginRequest dto);
	
	public Map<String, Object> searchMember(int memberId, int page);
}
