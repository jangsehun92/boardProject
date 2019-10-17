package jsh.spring.project.domain.member.service;

import org.springframework.stereotype.Service;

import jsh.spring.project.domain.member.dao.MemberRepository;
import jsh.spring.project.domain.member.domain.Member;
import jsh.spring.project.domain.member.dto.LoginRequest;

@Service
public class MemberLoginServiceImpl implements MemberLoginService{
	
	private final MemberRepository memberRepository;
	
	public MemberLoginServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Override
	public Member signin(LoginRequest dto) {
		//비밀번호 암호화 필요함
		return memberRepository.login(dto);
	}

}
