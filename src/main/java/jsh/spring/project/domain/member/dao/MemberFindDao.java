package jsh.spring.project.domain.member.dao;

import jsh.spring.project.domain.member.dto.LoginRequest;
import jsh.spring.project.domain.member.dto.MemberResponse;

public interface MemberFindDao {
	//로그인
	public MemberResponse login(LoginRequest dto);
}
