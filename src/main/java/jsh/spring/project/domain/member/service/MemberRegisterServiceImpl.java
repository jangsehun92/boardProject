package jsh.spring.project.domain.member.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jsh.spring.project.domain.member.dao.AuthRepository;
import jsh.spring.project.domain.member.dao.MemberRepository;
import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;
import jsh.spring.project.global.util.AuthKey;
import jsh.spring.project.global.util.MailUtils;

@Service
public class MemberRegisterServiceImpl implements MemberRegisterService {
	private final MemberRepository memberRepository;
	private final JavaMailSender mailSender;
	private final AuthRepository authRepository;
	
	public MemberRegisterServiceImpl(MemberRepository memberRepository, AuthRepository authRepository, JavaMailSender mailSender) {
		this.memberRepository = memberRepository;
		this.authRepository = authRepository;
		this.mailSender = mailSender;
	}
	
	@Override
	@Transactional
	public void singUp(RegisterRequest dto) throws Exception{
		memberRepository.checkEmail(dto.getEmail());
		memberRepository.save(dto);
		
		String authKey = new AuthKey().getKey(64, false);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", dto.getEmail());
		paramMap.put("authKey", authKey);
		paramMap.put("type", 10);
		authRepository.save(paramMap);
		
		MailUtils sendMail = new MailUtils(mailSender);
		sendMail.setSubject("[ JSH Board Project ] 회원가입 이메일 인증");
		sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
				.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<a href='http://localhost:8081/member/registerConfirm?email=")
				.append(dto.getEmail())
				.append("&authKey=")
				.append(authKey)
				.append("' target='_blenk'>인증하기</a>")
				.toString());
		sendMail.setFrom("jangsehun1992@gmail.com", "관리자");
		sendMail.setTo(dto.getEmail());
		sendMail.send();
		
	}

	@Override
	@Transactional
	public void updateStatus(RegisterConfirmRequest dto) throws Exception {
		memberRepository.updateStatus(dto);
		authRepository.expire(dto);
		
		MailUtils sendMail = new MailUtils(mailSender);
		sendMail.setSubject("[ JSH Board Project ] 이메일 인증 완료");
		sendMail.setText(new StringBuffer().append("<h1>[ 이메일 인증 완료 ]</h1>")
				.append("<p>인증이 완료 되었습니다 감사합니다.</p>")
				.toString());
		sendMail.setFrom("jangsehun1992@gmail.com", "관리자");
		sendMail.setTo(dto.getEmail());
		sendMail.send();
	}

	@Override
	@Transactional
	public void resendEmail(String email) throws Exception {
		memberRepository.checkEmail(email);
		String authKey = new AuthKey().getKey(64, false);
		authRepository.updateAuthKey(new RegisterConfirmRequest(email, authKey));
		
		MailUtils sendMail = new MailUtils(mailSender);
		sendMail.setSubject("[ JSH Board Project ](재발송)회원가입 이메일 인증");
		sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
				.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<a href='http://localhost:8081/member/registerConfirm?email=")
				.append(email)
				.append("&authKey=")
				.append(authKey)
				.append("' target='_blenk'>인증하기</a>")
				.toString());
		sendMail.setFrom("jangsehun1992@gmail.com", "관리자");
		sendMail.setTo(email);
		sendMail.send();
	}

}
