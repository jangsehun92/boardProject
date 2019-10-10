package jsh.spring.project.domain.member.service;

import jsh.spring.project.domain.member.dto.RegisterRequest;

public interface MemberRegisterService {
	//회원가입 방식이 두가지(일반 회원가입, SNS 회원가입)
	public boolean singUp(RegisterRequest dto);
	
}
