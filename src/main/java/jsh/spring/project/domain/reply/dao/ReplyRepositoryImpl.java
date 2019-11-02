package jsh.spring.project.domain.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jsh.spring.project.domain.reply.domain.Reply;
import jsh.spring.project.domain.reply.dto.ReplyCreateRequest;
import jsh.spring.project.domain.reply.dto.ReplyDeleteRequest;
import jsh.spring.project.domain.reply.dto.ReplyUpdateRequest;

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

	@Override
	public int save(ReplyCreateRequest dto) {
		return sqlSession.insert("replyMapper.save",dto);
	}

	@Override
	public int update(ReplyUpdateRequest dto) {
		return sqlSession.update("replyMapper.update",dto);
	}

	@Override
	public int delete(ReplyDeleteRequest dto) {
		return sqlSession.delete("replyMapper.delete",dto);
	}
}
