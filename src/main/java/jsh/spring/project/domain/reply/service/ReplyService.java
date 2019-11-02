package jsh.spring.project.domain.reply.service;

import java.util.List;

import jsh.spring.project.domain.reply.domain.Reply;
import jsh.spring.project.domain.reply.dto.ReplyCreateRequest;
import jsh.spring.project.domain.reply.dto.ReplyDeleteRequest;
import jsh.spring.project.domain.reply.dto.ReplyUpdateRequest;

public interface ReplyService {
	public List<Reply> list(int articleId);
	public void create(ReplyCreateRequest dto);
	public void update(ReplyUpdateRequest dto);
	public void delete(ReplyDeleteRequest dto);
}
