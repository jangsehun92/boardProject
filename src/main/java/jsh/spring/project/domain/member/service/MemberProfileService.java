package jsh.spring.project.domain.member.service;

import jsh.spring.project.domain.member.dto.MemberPasswordChangeRequest;
import jsh.spring.project.domain.member.dto.MemberProfileUpdateRequest;

public interface MemberProfileService {
	//회원정보 업데이트
	public void updateProfile(MemberProfileUpdateRequest dto);
	//비밀번호 변경
	public void changePassword(MemberPasswordChangeRequest dto);
}
