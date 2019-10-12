package jsh.spring.project.domain.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jsh.spring.project.domain.member.dao.MemberRepository;
import jsh.spring.project.domain.member.domain.AuthKey;
import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;
import jsh.spring.project.global.util.MailUtils;

@Service
public class MemberRegisterServiceImpl implements MemberRegisterService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void singUp(RegisterRequest dto) throws Exception{
		memberRepository.checkEmail(dto.getMember_email());
		//인증키 생성 및 DB내 해당 member authKey 저장
		String authKey = new AuthKey().getKey(64, false);
		dto.setMember_authKey(authKey);
		
		//DB에 insert가 실패하면
		memberRepository.save(dto);
		//mail 발송
		MailUtils sendMail = new MailUtils(mailSender);
		sendMail.setSubject("[ JSH Board Project ] 회원가입 이메일 인증");
		sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
				.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<a href='http://localhost:8081/member/registerConfirm?email=")
				.append(dto.getMember_email())
				.append("&authKey=")
				.append(authKey)
				.append("' target='_blenk'>인증하기</a>")
				.toString());
		sendMail.setFrom("jangsehun1992@gmail.com", "관리자");
		sendMail.setTo(dto.getMember_email());
		sendMail.send();
	}

	@Override
	public void updateAuthStatus(RegisterConfirmRequest dto) {
		memberRepository.updateStatus(dto);
	}

	@Override
	public void resendEmail(String email) throws Exception {
		memberRepository.checkEmail(email);
		//인증키 재생성
		String authKey = new AuthKey().getKey(64, false);
		
		//해당 이메일로 인증키 update
		memberRepository.updateAuthKey(new RegisterConfirmRequest(email, authKey));
		
		//mail 발송
		MailUtils sendMail = new MailUtils(mailSender);
		sendMail.setSubject("[ JSH Board Project ] 회원가입 이메일 인증");
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
