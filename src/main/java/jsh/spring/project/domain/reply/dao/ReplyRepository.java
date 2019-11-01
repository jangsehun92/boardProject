package jsh.spring.project.domain.reply.dao;

import java.util.List;

import jsh.spring.project.domain.reply.domain.Reply;

public interface ReplyRepository {
	public List<Reply> list(int articleId);
	// 댓글list
	// 댓글입력
	// 댓글수정
	// 댓글삭제
}
