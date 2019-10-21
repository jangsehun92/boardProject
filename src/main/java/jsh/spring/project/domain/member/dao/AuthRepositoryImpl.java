package jsh.spring.project.domain.member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;

@Repository
public class AuthRepositoryImpl implements AuthRepository{

	private final SqlSession sqlSession;
	
	public AuthRepositoryImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int save(Map<String, Object> paramMap) {
		return sqlSession.insert("auth.save", paramMap);
	}

	@Override
	public int expired(String email) {
		return sqlSession.update("auth.expired", email);
	}

	@Override
	public int updateAuthKey(RegisterConfirmRequest dto) {
		return sqlSession.update("auth.updateAuthKey", dto);
	}

}
