package jsh.spring.project.domain.member.dao;

import org.springframework.stereotype.Repository;

import jsh.spring.project.domain.member.domain.Member;
import jsh.spring.project.domain.member.dto.LoginRequest;
import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;

@Repository
public interface MemberRepository {
	
	//회원가입 시 가입된 이메일이 있는지 확인 
	public int checkEmail(String email);
	//회원가입(save)
	public int save(RegisterRequest dto);
	//이메일 인증 처리(Status update)
	public int updateStatus(RegisterConfirmRequest dto);
	//이메일 재발송(AuthKey update)
	public int updateAuthKey(RegisterConfirmRequest dto);
	//로그인
	public Member login(LoginRequest dto);
	
}
