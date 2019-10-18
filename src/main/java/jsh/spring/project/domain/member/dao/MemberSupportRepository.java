package jsh.spring.project.domain.member.dao;

import jsh.spring.project.domain.member.dto.MemberPasswordChangeRequest;
import jsh.spring.project.domain.member.dto.MemberProfileUpdateRequest;

public interface MemberSupportRepository {
	//회원정보 수정
	public int updateProfile(MemberProfileUpdateRequest dto);

	//비밀번호 변경
	public int changePassword(MemberPasswordChangeRequest dto);
}
