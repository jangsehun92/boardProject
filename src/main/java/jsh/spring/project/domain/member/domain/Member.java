package jsh.spring.project.domain.member.domain;

import java.sql.Date;

public class Member {
	private int memberNumber;
	private String memberEmail;
	private String memberPassword;
	private String memberNickname;
	private String memberStatus;
	private Date memberRegisterDate;
	
	public Member() {
		
	}
	public Member(int memberNumber, String memberEmail, String memberPassword, String memberNickname, String memberStatus, Date memberRegisterDate) {
		this.memberNumber = memberNumber;
		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
		this.memberNickname = memberNickname;
		this.memberStatus = memberStatus;
		this.memberRegisterDate = memberRegisterDate;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	public Date getMemberRegisterDate() {
		return memberRegisterDate;
	}
	public void setMemberRegisterDate(Date memberRegisterDate) {
		this.memberRegisterDate = memberRegisterDate;
	}
	
	
	
}
