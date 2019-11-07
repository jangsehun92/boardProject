package jsh.spring.project.domain.reply.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<String> update(HttpSession session, @PathVariable("id")int id, @RequestBody ReplyUpdateRequest dto) {
		Member member = (Member)session.getAttribute("member");
		dto.setMemberId(member.getId());
		dto.setId(id);
		ResponseEntity<String> entity = null;
		try {
			replyService.update(dto);
			entity = new ResponseEntity<String>("UPDATE SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<String> delete(HttpSession session, @PathVariable("id")int id) {
		Member member = (Member)session.getAttribute("member");
		ReplyDeleteRequest dto = new ReplyDeleteRequest(id, member.getId());
		ResponseEntity<String> entity = null;
		try {
			replyService.delete(dto);
			entity = new ResponseEntity<String>("DELETE SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
