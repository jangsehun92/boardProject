package jsh.spring.project.domain.reply.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jsh.spring.project.domain.reply.dao.ReplyRepository;
import jsh.spring.project.domain.reply.domain.Reply;
import jsh.spring.project.domain.reply.dto.ReplyCreateRequest;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	private final ReplyRepository replyRepository;
	
	public ReplyServiceImpl(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	@Override
	public List<Reply> list(int articleId) {
		return replyRepository.list(articleId);
	}

	@Override
	public void create(ReplyCreateRequest dto) {
		
	}

}
