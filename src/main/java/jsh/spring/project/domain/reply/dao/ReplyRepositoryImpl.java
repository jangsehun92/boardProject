package jsh.spring.project.domain.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jsh.spring.project.domain.reply.domain.Reply;

@Repository
public class ReplyRepositoryImpl implements ReplyRepository{
	
	private final SqlSession sqlSession;
	
	public ReplyRepositoryImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Reply> list(int articleId) {
		return sqlSession.selectList("replyMapper.list",articleId);
	}
	
	

}
