package jsh.spring.project.domain.member.service;

import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;

public interface MemberRegisterService {
	//회원가입 방식이 두가지(일반 회원가입, SNS 회원가입)
	public void singUp(RegisterRequest dto) throws Exception;
	
	public void updateAuthStatus(RegisterConfirmRequest dto);
	
	public void resendEmail(String email) throws Exception;
	
}
