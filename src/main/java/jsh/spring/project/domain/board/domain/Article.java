package jsh.spring.project.domain.board.domain;

import java.sql.Date;

public class Article {
	public int id;
	public int memberId;
	public String category;
	public String writer;
	public String title;
	public String content;
	public Date regDate;
	public int viewCount;
	public int likeCount;
	public int replyCount;
	
	public Article() {
		
	}
	
	public Article(int id, int memberId, String category, String writer, String title, String content, Date regDate, int viewCount, int likeCount, int replyCount) {
		this.id = id;
		this.memberId = memberId;
		this.category = category;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.replyCount = replyCount;
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
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
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

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	
	
}
