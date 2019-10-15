package jsh.spring.project.domain.member.domain;

import java.sql.Date;

public class Member {
	private int member_no;
	private String member_id;
	private String member_pw;
	private String member_email;
	private String member_nickname;
	private String member_authkey;
	private String member_authstatus;
	private Date member_regDate;
	
	public Member() {
		
	}
	
	public Member(int member_no, String member_id, String member_pw, String member_email, String member_nickname, String member_authkey, String member_authstatues, Date member_regDate) {
		this.member_no = member_no;
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_email = member_email;
		this.member_nickname = member_nickname;
		this.member_authkey = member_authkey;
		this.member_authstatus = member_authstatues;
		this.member_regDate = member_regDate;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_nickname() {
		return member_nickname;
	}

	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}

	public String getMember_authkey() {
		return member_authkey;
	}

	public void setMember_authkey(String member_authkey) {
		this.member_authkey = member_authkey;
	}

	public String getMember_authstatus() {
		return member_authstatus;
	}

	public void setMember_authstatus(String member_authstatues) {
		this.member_authstatus = member_authstatues;
	}

	public Date getMember_regDate() {
		return member_regDate;
	}

	public void setMember_regDate(Date member_regDate) {
		this.member_regDate = member_regDate;
	}
	
}
