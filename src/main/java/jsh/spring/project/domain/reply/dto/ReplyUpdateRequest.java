package jsh.spring.project.domain.reply.dto;

public class ReplyUpdateRequest {
	private int id;
	private int memberId;
	private String content;
	
	public ReplyUpdateRequest(int id, String content) {
		this.id = id;
		this.content = content;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
