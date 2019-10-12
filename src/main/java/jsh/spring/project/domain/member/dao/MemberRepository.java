package jsh.spring.project.domain.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;

@Repository
public class MemberRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입 시 가입된 이메일이 있는지 확인 
	public int checkEmail(String email) {
		return sqlSession.selectOne("register.checkEmail",email);
	}
	
	//회원가입(save)
	public int save(RegisterRequest dto) {
		return sqlSession.insert("register.save",dto);
	}
	
	public int updateStatus(RegisterConfirmRequest dto) {
		return sqlSession.update("register.updateStatus", dto);
	}
	
	public int updateAuthKey(RegisterConfirmRequest dto) {
		return sqlSession.update("register.updateAuthKey", dto);
	}
}
