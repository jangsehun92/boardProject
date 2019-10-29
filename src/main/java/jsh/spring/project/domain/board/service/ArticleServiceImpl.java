package jsh.spring.project.domain.board.service;

import org.springframework.stereotype.Service;

import jsh.spring.project.domain.board.dao.ArticleRepository;
import jsh.spring.project.domain.board.domain.Article;
import jsh.spring.project.domain.board.dto.ArticleCreateRequest;
import jsh.spring.project.domain.board.dto.ArticleUpdateRequest;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	private final ArticleRepository articleRepository;
	
	public ArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public Article detail(int id) {
		return articleRepository.detail(id);
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
