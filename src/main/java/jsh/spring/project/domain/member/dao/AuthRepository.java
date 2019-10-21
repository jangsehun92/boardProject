package jsh.spring.project.domain.member.dao;

import java.util.Map;

import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;

public interface AuthRepository {
	public int save(Map<String, Object> paramMap);
	public int updateAuthKey(RegisterConfirmRequest dto);
	public int expired(String email);
}
