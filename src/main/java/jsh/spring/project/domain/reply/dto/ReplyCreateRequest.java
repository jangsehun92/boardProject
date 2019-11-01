package jsh.spring.project.domain.reply.dto;

public class ReplyCreateRequest {
	private int articleId;
	private int memberId;
	private String content;
	
	public ReplyCreateRequest(int articleId, String content) {
		this.articleId = articleId;
		this.content = content;
	}

	public int getArticleId() {
		return articleId;
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
