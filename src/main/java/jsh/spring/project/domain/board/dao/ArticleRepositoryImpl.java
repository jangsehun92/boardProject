package jsh.spring.project.domain.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jsh.spring.project.domain.board.domain.Article;
import jsh.spring.project.domain.board.dto.ArticleCreateRequest;
import jsh.spring.project.domain.board.dto.ArticleUpdateRequest;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository{
	
	private final SqlSession sqlSession;
	
	public ArticleRepositoryImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Article detail(int id) {
		return sqlSession.selectOne("articleMapper.detail", id);
	}

	@Override
	public int create(ArticleCreateRequest dto) {
		return sqlSession.insert("articleMapper.save",dto);
	}

	@Override
	public int update(ArticleUpdateRequest dto) {
		return sqlSession.update("articleMapper.update", dto);
	}

	@Override
	public int delete(int id) {
		return sqlSession.delete("articleMapper.delete",id);
	}
	

}
