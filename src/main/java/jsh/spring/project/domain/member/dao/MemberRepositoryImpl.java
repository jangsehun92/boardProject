package jsh.spring.project.domain.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
	private final SqlSession sqlSession;

	public MemberRepositoryImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int checkEmail(String email) {
		return sqlSession.selectOne("register.checkEmail", email);
	}

	@Override
	public int save(RegisterRequest dto) {
		return sqlSession.insert("register.save", dto);
	}
	
	@Override
	public int updateStatus(RegisterConfirmRequest dto) {
		return sqlSession.update("register.updateStatus", dto);
	}

	@Override
	public int checkStatus(String email) {
		return sqlSession.selectOne("register.checkStatus",email);
	}

}
