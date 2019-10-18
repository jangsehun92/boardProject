package jsh.spring.project.domain.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jsh.spring.project.domain.member.dto.LoginRequest;
import jsh.spring.project.domain.member.dto.MemberResponse;

@Repository
public class MemberFindDaoImpl implements MemberFindDao{

	private final SqlSession sqlSession;
	
	public MemberFindDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public MemberResponse login(LoginRequest dto) {
		return sqlSession.selectOne("member.login", dto);
	}

}
