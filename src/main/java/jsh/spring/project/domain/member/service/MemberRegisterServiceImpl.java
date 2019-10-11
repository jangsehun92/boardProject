package jsh.spring.project.domain.member.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jsh.spring.project.domain.member.dao.MemberRepository;
import jsh.spring.project.domain.member.domain.AuthKey;
import jsh.spring.project.domain.member.dto.RegisterRequest;
import jsh.spring.project.global.util.MailUtils;

@Service
public class MemberRegisterServiceImpl implements MemberRegisterService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public boolean singUp(RegisterRequest dto) throws Exception{
		if(memberRepository.checkEmail(dto.getMember_email()) != 0) {
			return false;
		}
		//해당 이메일로 가입 가능 하기 때문에 유저 인증번호 생성(Email 인증을 위해)
		String authKey = new AuthKey().getKey(64, false);
		//dto에 authCode를 넣어줌
		dto.setMember_authKey(authKey);
		//save를 위해 dto를 dao로 보내준다.
		if(memberRepository.save(dto) != 0) {
			//인증 이메일을 발송한다.
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
			
			return true;
		}
		
		return false;
	}
	
	
}
