package jsh.spring.project.domain.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
	// resultMap을 정의하여 리턴해주자
	private final SqlSession sqlSession;

	public MemberRepositoryImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// 회원가입 시 가입된 이메일이 있는지 확인
	public int checkEmail(String email) {
		return sqlSession.selectOne("register.checkEmail", email);
	}

	// DB저장
	public int save(RegisterRequest dto) {
		return sqlSession.insert("register.save", dto);
	}

	// 이메일 인증 처리(Status update)
	public int updateStatus(RegisterConfirmRequest dto) {
		return sqlSession.update("register.updateStatus", dto);
	}

	// 이메일 재발송(AuthKey update)
	public int updateAuthKey(RegisterConfirmRequest dto) {
		return sqlSession.update("register.updateAuthKey", dto);
	}

}
