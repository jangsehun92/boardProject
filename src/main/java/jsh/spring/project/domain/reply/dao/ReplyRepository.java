package jsh.spring.project.domain.reply.dao;

import java.util.List;

import jsh.spring.project.domain.reply.domain.Reply;
import jsh.spring.project.domain.reply.dto.ReplyCreateRequest;
import jsh.spring.project.domain.reply.dto.ReplyDeleteRequest;
import jsh.spring.project.domain.reply.dto.ReplyUpdateRequest;

public interface ReplyRepository {
	public List<Reply> list(int articleId);
	public int save(ReplyCreateRequest dto);
	public int update(ReplyUpdateRequest dto);
	public int delete(ReplyDeleteRequest dto);
}
