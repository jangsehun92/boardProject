package jsh.spring.project.domain.reply.dto;

public class ReplyDeleteRequest {
	private int id;
	private int memberId;
	
	public ReplyDeleteRequest(int id, int memberId) {
		this.id = id;
		this.memberId = memberId;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
}
