package jsh.spring.project.domain.member.dao;

import org.springframework.stereotype.Repository;

import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;

@Repository
public interface MemberRepository {
	public int checkEmail(String email);
	public int save(RegisterRequest dto);
	public int checkStatus(String email);
	public int updateStatus(RegisterConfirmRequest dto);
}
