package jsh.spring.project.domain.member.dto;

public class MemberResponse {
	private String member_nickname;
	private String member_authstatus;
	
	public MemberResponse() {
		
	}
	
	public MemberResponse(String member_nickname, String member_authstatus) {
		this.member_nickname = member_nickname;
		this.member_authstatus = member_authstatus;
	}

	public String getMember_nickname() {
		return member_nickname;
	}

	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}

	public String getMember_authstatus() {
		return member_authstatus;
	}

	public void setMember_authstatus(String member_authstatus) {
		this.member_authstatus = member_authstatus;
	}
	
	
}
