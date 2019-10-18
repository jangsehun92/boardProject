package jsh.spring.project.domain.member.service;

import org.springframework.stereotype.Service;

import jsh.spring.project.domain.member.dao.MemberSupportRepository;
import jsh.spring.project.domain.member.dto.MemberPasswordChangeRequest;
import jsh.spring.project.domain.member.dto.MemberProfileUpdateRequest;

@Service
public class MemberProfileServiceImpl implements MemberProfileService{
	
	private final MemberSupportRepository memberSupportRepository;
	
	public MemberProfileServiceImpl(MemberSupportRepository memberSupportRepository) {
		this.memberSupportRepository = memberSupportRepository;
	}

	@Override
	public void updateProfile(MemberProfileUpdateRequest dto) {
		memberSupportRepository.updateProfile(dto);
	}

	@Override
	public void changePassword(MemberPasswordChangeRequest dto) {
		memberSupportRepository.changePassword(dto);
	}

}
