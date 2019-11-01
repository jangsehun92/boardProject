package jsh.spring.project.domain.reply.service;

import java.util.List;

import jsh.spring.project.domain.reply.domain.Reply;
import jsh.spring.project.domain.reply.dto.ReplyCreateRequest;

public interface ReplyService {
	public List<Reply> list(int articleId);
	//댓글입력
	
	public void create(ReplyCreateRequest dto);
	//댓글수정
	//댓글삭제
}
