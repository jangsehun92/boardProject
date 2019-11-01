package jsh.spring.project.domain.reply.domain;

import java.sql.Date;

public class Reply {
	private int id;
	private int articleId;
	private int memberId;
	private String nickname;
	private String content;
	private Date regDate;
	
	public Reply() {
		
	}
	
	public Reply(int id, int articleId, int memberId, String nickname, String content, Date regDate) {
		this.id = id;
		this.articleId = articleId;
		this.memberId = memberId;
		this.nickname = nickname;
		this.content = content;
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
