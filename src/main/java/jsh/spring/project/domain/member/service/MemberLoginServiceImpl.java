package jsh.spring.project.domain.member.service;

import org.springframework.stereotype.Service;

import jsh.spring.project.domain.member.domain.Member;

@Service
public class MemberLoginServiceImpl implements MemberLoginService{
	
	public MemberLoginServiceImpl() {
		System.out.println("MemberLoginServiceImpl 생성됨.");
	}

	@Override
	public Member signin() {
		System.out.println("MemberLoginServiceImpl.signin() 실행");
		return null;
	}

}
