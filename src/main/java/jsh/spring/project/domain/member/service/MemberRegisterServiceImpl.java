package jsh.spring.project.domain.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsh.spring.project.domain.member.dao.MemberRepository;
import jsh.spring.project.domain.member.domain.AuthKey;
import jsh.spring.project.domain.member.dto.RegisterRequest;

@Service
public class MemberRegisterServiceImpl implements MemberRegisterService{

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public boolean singUp(RegisterRequest dto) {
		if(memberRepository.checkEmail(dto.getMember_email()) != 0) {
			return false;
		}
		//해당 이메일로 가입 가능 하기 때문에 유저 인증번호 생성(Email 인증을 위해)
		String authKey = new AuthKey().getKey(64, false);
		//dto에 authCode를 넣어줌
		dto.setMember_authKey(authKey);
		//save를 위해 dto를 dao로 보내준다.
		if(memberRepository.save(dto) != 0) {
			return true;
		}
		//인증 이메일을 발송한다.
		
		return false;
	}
	
}
