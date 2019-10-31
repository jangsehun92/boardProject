package jsh.spring.project.domain.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jsh.spring.project.domain.member.dto.MemberPasswordChangeRequest;
import jsh.spring.project.domain.member.dto.MemberProfileUpdateRequest;

@Repository
public class MemberSupportRepositoryImpl implements MemberSupportRepository{
	
	private final SqlSession sqlSession;
	
	public MemberSupportRepositoryImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int updateProfile(MemberProfileUpdateRequest dto) {
		return sqlSession.update("supportMapper.profileUpdate",dto);
	}

	@Override
	public int changePassword(MemberPasswordChangeRequest dto) {
		return sqlSession.update("supportMapper.passwordChange", dto);
	}

}
