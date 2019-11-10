package jsh.spring.project.domain.member.service;

import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;

public interface MemberRegisterService {
	public int checkEmail(String email);
	public void singUp(RegisterRequest dto) throws Exception;
	public void updateStatus(RegisterConfirmRequest dto) throws Exception;
	public void resendEmail(String email) throws Exception;
}
