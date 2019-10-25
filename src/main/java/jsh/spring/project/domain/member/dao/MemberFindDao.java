package jsh.spring.project.domain.member.dao;

import jsh.spring.project.domain.member.domain.Member;
import jsh.spring.project.domain.member.dto.LoginRequest;
import jsh.spring.project.domain.member.dto.MemberResponse;

public interface MemberFindDao {
	//로그인
	public Member login(LoginRequest dto);
	
	public MemberResponse search(int number);
}
