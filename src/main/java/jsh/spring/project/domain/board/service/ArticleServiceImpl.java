package jsh.spring.project.domain.board.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import jsh.spring.project.domain.board.dao.ArticleRepository;
import jsh.spring.project.domain.board.domain.Article;
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
	public Article getArticle(int id) {
		return articleRepository.detail(id);
	}
	
	@Override
	public boolean checkLike(int id, int memberId) {
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("id", id);
		paramMap.put("memberId", memberId);
		
		if(articleRepository.checkLike(paramMap)==1) {
			return true;
		}
		return false;
	}
	
	@Override
	public int like(int id, int memberId) {
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("id", id);
		paramMap.put("memberId", memberId);
		
		System.out.println("****** articleSErviceImpl like : id - " + id + " memberId - " + memberId);
		if(articleRepository.checkLike(paramMap)==1) {
			articleRepository.deleteLike(paramMap);
			return 0;
		}
		articleRepository.insertLike(paramMap);
		return 1;
	}

	@Override
	public Map<String, Object> detail(int id) {
		articleRepository.viewCount(id);
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
