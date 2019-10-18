package jsh.spring.project.domain.member.service;

import org.springframework.stereotype.Service;

import jsh.spring.project.domain.member.dao.MemberFindDao;
import jsh.spring.project.domain.member.dto.LoginRequest;
import jsh.spring.project.domain.member.dto.MemberResponse;

@Service
public class MemberSearchServiceImpl implements MemberSearchService{
	
	private final MemberFindDao memberFindDao;
	
	public MemberSearchServiceImpl(MemberFindDao MemberFindDao) {
		this.memberFindDao = MemberFindDao;
	}
	
	@Override
	public MemberResponse signIn(LoginRequest dto) {
		//비밀번호 암호화 필요함
		return memberFindDao.login(dto);
	}

}
