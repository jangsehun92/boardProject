package jsh.spring.project.domain.member.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jsh.spring.project.domain.member.api.MemberApi;
import jsh.spring.project.domain.member.dao.AuthRepository;
import jsh.spring.project.domain.member.dao.MemberRepository;
import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;
import jsh.spring.project.global.util.AuthKey;
import jsh.spring.project.infra.email.EmailService;

@Service
public class MemberRegisterServiceImpl implements MemberRegisterService{
	private static final Logger logger = LoggerFactory.getLogger(MemberRegisterServiceImpl.class);
	
	private final MemberRepository memberRepository;
	private final AuthRepository authRepository;
	private final EmailService emailService;
	
	public MemberRegisterServiceImpl(MemberRepository memberRepository, AuthRepository authRepository, EmailService emailService) {
		this.memberRepository = memberRepository;
		this.authRepository = authRepository;
		this.emailService = emailService;
	}
	
	@Override
	public int checkEmail(String email) {
		return memberRepository.checkEmail(email);
	}
	
	@Override
	@Transactional
	public void singUp(RegisterRequest dto) throws Exception{
		memberRepository.save(dto);
		String authKey = new AuthKey().getKey(64, false);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", dto.getEmail());
		paramMap.put("authKey", authKey);
		paramMap.put("type", 10);
		authRepository.save(paramMap);
		emailService.sendEmail(dto.getEmail(), authKey);
	}

	@Override
	@Transactional
	public boolean updateStatus(RegisterConfirmRequest dto) throws Exception {
		if(memberRepository.checkStatus(dto.getEmail())==0) {
			logger.info("[ MemberRegisterServiceImpl ] updateStatus : 인증 작업");
			memberRepository.updateStatus(dto);
			authRepository.expire(dto);
			emailService.singUpEmail(dto.getEmail());
			return true;
		}
		logger.info("[ MemberRegisterServiceImpl ] updateStatus : 이미 인증되었음");
		return false;
	}
	
	@Override
	@Transactional
	public void resendEmail(String email) throws Exception {
		memberRepository.checkEmail(email);
		String authKey = new AuthKey().getKey(64, false);
		authRepository.updateAuthKey(new RegisterConfirmRequest(email, authKey));
		emailService.resendEmail(email, authKey);
	}
}
