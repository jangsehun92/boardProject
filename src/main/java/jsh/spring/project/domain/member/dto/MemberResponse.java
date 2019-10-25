package jsh.spring.project.domain.member.dto;

public class MemberResponse {
	private String nickname;
	
	public MemberResponse() {
		
	}
	
	public MemberResponse(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
