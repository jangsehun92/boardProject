package jsh.spring.project.domain.member.domain;

import java.sql.Date;

public class Member {
	private Long member_no;
	private String member_id;
	private String member_pw;
	private String member_email;
	private String member_nickname;
	private String member_authkey;
	private String member_authstatues;
	private Date member_regDate;
	
	public Member() {
		
	}
	
	public Member(Long member_no, String member_id, String member_pw, String member_email, String member_nickname, String member_authkey, String member_authstatues, Date member_regDate) {
		this.member_no = member_no;
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_email = member_email;
		this.member_nickname = member_nickname;
		this.member_authkey = member_authkey;
		this.member_authstatues = member_authstatues;
		this.member_regDate = member_regDate;
	}
}
