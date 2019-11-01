package jsh.spring.project.domain.reply.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jsh.spring.project.domain.reply.dto.ReplyCreateRequest;
import jsh.spring.project.domain.reply.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyApi {
	private static final Logger logger = LoggerFactory.getLogger(ReplyApi.class);
	
	private final ReplyService replyService;
	
	public ReplyApi(ReplyService replyService) {
		this.replyService = replyService;
	}
	//댓글 요청
	@RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
	public @ResponseBody Object list(@PathVariable("articleId")int articleId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("message","ok");
		resultMap.put("replyList",replyService.list(articleId));
		return "resultMap";
	}
	//댓글 입력
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Object create(ReplyCreateRequest dto) {
		return "";
	}
	
	//댓글 수정
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Object update() {
		return "";
	}
	
	//댓글 삭제
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody Object delete() {
		return "";
	}
}
