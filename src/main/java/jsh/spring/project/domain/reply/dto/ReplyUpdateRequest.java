package jsh.spring.project.domain.reply.dto;

public class ReplyUpdateRequest {
	private int id;
	private int memberId;
	private String content;
	
	public ReplyUpdateRequest() {
		
	}
	
	public ReplyUpdateRequest(int id, String content) {
		this.content = content;
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
	
	public String getContent() {
		return content;
	}
	
}
