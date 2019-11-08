package jsh.spring.project.domain.board.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import jsh.spring.project.domain.board.dao.ArticleRepository;
import jsh.spring.project.domain.board.dto.ArticleCreateRequest;
import jsh.spring.project.domain.board.dto.ArticleUpdateRequest;
import jsh.spring.project.domain.reply.dao.ReplyRepository;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	private final ArticleRepository articleRepository;
	private final ReplyRepository replyRepository;
	
	public ArticleServiceImpl(ArticleRepository articleRepository, ReplyRepository replyRepository) {
		this.articleRepository = articleRepository;
		this.replyRepository = replyRepository;
	}

	@Override
	public Map<String, Object> detail(int id) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("article", articleRepository.detail(id));
		resultMap.put("replyList", replyRepository.list(id));
		return resultMap;
	}

	@Override
	public int create(ArticleCreateRequest dto) {
		return articleRepository.create(dto);
	}

	@Override
	public int update(ArticleUpdateRequest dto) {
		return articleRepository.update(dto);
	}

	@Override
	public void delete(int id) {
		articleRepository.delete(id);
	}

}
