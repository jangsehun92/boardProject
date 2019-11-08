package jsh.spring.project.domain.board.api;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jsh.spring.project.domain.board.dto.ArticleCreateRequest;
import jsh.spring.project.domain.board.dto.ArticleUpdateRequest;
import jsh.spring.project.domain.board.service.ArticleService;
import jsh.spring.project.domain.member.domain.Member;

@Controller
@RequestMapping("/article")
public class ArticleApi {
	private static final Logger logger = LoggerFactory.getLogger(ArticleApi.class);

	private ArticleService articleService;
	
	public ArticleApi(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	//CREATE
	@RequestMapping(method = RequestMethod.POST)
	public String create(HttpSession session, Model model, ArticleCreateRequest dto) {
		Member member = (Member)session.getAttribute("member");
		dto.setMemberId(member.getId());
		articleService.create(dto);
		
		return "redirect:/article/"+dto.getId();
	}

	//READ
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable("id") int id) {
		Map<String, Object> resultMap = articleService.detail(id);
		model.addAttribute("resultMap", resultMap);
		return "boardPages/detail";
	}

	//UPDATE
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(HttpSession session,Model model, @PathVariable("id")int id, ArticleUpdateRequest dto) {
		Member member = (Member)session.getAttribute("member");
		dto.setMemberId(member.getId());
		
		articleService.update(dto);
		return "redirect:/article/"+dto.getId();
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String update(HttpSession session, Model model, @PathVariable("id")int id) {
		model.addAttribute("article",articleService.detail(id));
		return "boardPages/update";
	}

	//DEFETE
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(HttpSession session, @PathVariable("id")int id) {
		articleService.delete(id);
		return "redirect:/articles/"+(String)session.getAttribute("category");
	}
}
