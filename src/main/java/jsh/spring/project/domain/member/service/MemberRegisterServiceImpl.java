package jsh.spring.project.domain.member.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jsh.spring.project.domain.member.dao.MemberRepository;
import jsh.spring.project.domain.member.domain.AuthKey;
import jsh.spring.project.domain.member.domain.MailUtils;
import jsh.spring.project.domain.member.dto.RegisterRequest;
import jsh.spring.project.global.util.MailUtils;

@Service
public class MemberRegisterServiceImpl implements MemberRegisterService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
<<<<<<< HEAD
	public boolean singUp(RegisterRequest dto){
=======
	public boolean singUp(RegisterRequest dto) throws Exception{
>>>>>>> member
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
<<<<<<< HEAD
		//인증 이메일을 발송한다.
		//mailSender(dto.getMember_email(), authKey);
		return false;
	}
	
	//회원가입 완료 후, 인증 이메일 재발송 때 보내줘야한다(고민좀 해보기)
	private void mailSender(String email, String authKey) throws Exception {
		MailUtils sendMail = new MailUtils(mailSender);
		
		sendMail.setSubject("[ JSH Board Project ] 회원가입 이메일 인증");
		sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
				.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<a href='http://localhost:8081/member/registerConfirm?member_email=")
				.append(email)
				.append("&authKey=")
				.append(authKey)
				.append("' target='_blenk'>이메일 인증 확인</a>")
				.toString());
		sendMail.setFrom("관리자 ", "관리자명");
		sendMail.setTo(email);
		sendMail.send();
	}
=======
		
		return false;
	}
	
>>>>>>> member
	
}
