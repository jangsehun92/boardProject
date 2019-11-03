package jsh.spring.project.domain.reply.api;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jsh.spring.project.domain.member.domain.Member;
import jsh.spring.project.domain.reply.dto.ReplyCreateRequest;
import jsh.spring.project.domain.reply.dto.ReplyDeleteRequest;
import jsh.spring.project.domain.reply.dto.ReplyUpdateRequest;
import jsh.spring.project.domain.reply.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyApi {
	private static final Logger logger = LoggerFactory.getLogger(ReplyApi.class);
	
	private final ReplyService replyService;
	
	public ReplyApi(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> list(int articleId) {
		logger.info("**************** ReplyApi.list(" + articleId + ")");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("message","ok");
		resultMap.put("replyList",replyService.list(articleId));
		return resultMap;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Object create(HttpSession session, ReplyCreateRequest dto) {
		Member member = (Member)session.getAttribute("member");
		dto.setMemberId(member.getId());
		
		replyService.create(dto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("message","ok");
		return resultMap;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Object update(ReplyUpdateRequest dto) {
		return "";
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody Object delete(ReplyDeleteRequest dto) {
		return "";
	}
}
