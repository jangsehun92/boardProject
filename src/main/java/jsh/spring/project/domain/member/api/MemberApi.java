package jsh.spring.project.domain.member.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jsh.spring.project.domain.board.domain.Article;
import jsh.spring.project.domain.member.domain.Member;
import jsh.spring.project.domain.member.dto.LoginRequest;
import jsh.spring.project.domain.member.dto.MemberPasswordChangeRequest;
import jsh.spring.project.domain.member.dto.MemberProfileUpdateRequest;
import jsh.spring.project.domain.member.dto.MemberResponse;
import jsh.spring.project.domain.member.dto.RegisterConfirmRequest;
import jsh.spring.project.domain.member.dto.RegisterRequest;
import jsh.spring.project.domain.member.service.MemberProfileService;
import jsh.spring.project.domain.member.service.MemberRegisterService;
import jsh.spring.project.domain.member.service.MemberSearchService;
import jsh.spring.project.global.common.request.Pagination;

@Controller
@RequestMapping("/member")
public class MemberApi {
	private static final Logger logger = LoggerFactory.getLogger(MemberApi.class);
	
	private final MemberRegisterService memberRegisterService;
	private final MemberSearchService memberSearchService;
	private final MemberProfileService memberProfileService;
	
	public MemberApi(MemberRegisterService memberRegisterService, MemberSearchService memberSearchService, MemberProfileService memberProfileService) {
		this.memberRegisterService = memberRegisterService;
		this.memberSearchService = memberSearchService;
		this.memberProfileService = memberProfileService;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Model model, RegisterRequest dto) throws Exception {
		memberRegisterService.singUp(dto);
		model.addAttribute("email", dto.getEmail());
		return "memberPages/sendEmail";
	}
	
	@RequestMapping(value = "/registerConfirm", method = RequestMethod.GET)
	public String registerConfirm(Model model, RegisterConfirmRequest dto) throws Exception {
		memberRegisterService.updateStatus(dto);
		return "memberPages/login";
	}
	
	@RequestMapping(value = "/resendEmail/{email:.+}", method = RequestMethod.GET)
	public String resendEmail(Model model, String email) throws Exception {
		memberRegisterService.resendEmail(email);
		return "memberPages/sendEmail";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session, LoginRequest dto) {
		if(session.getAttribute("member") != null) {
			session.removeAttribute("member");
		}
		Member member = memberSearchService.signIn(dto);
		
		if(!member.checkStatus()) {
			model.addAttribute("email", member.getEmail());
			return "memberPages/sendEmail";
		}
		session.setAttribute("member", member);
		return "home";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "memberPages/login";
	}

	@RequestMapping(value = "/info/{memberId}", method = RequestMethod.GET)
	public String profile(HttpSession session, Model model, @PathVariable("memberId")int memberId, @RequestParam(defaultValue="1")int page) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap = memberSearchService.searchMember(memberId, page);
//		MemberResponse memberResponse = (MemberResponse)resultMap.get("memberResponse");
//		List<Article> articleList = (List<Article>)resultMap.get("articleList");
//		Pagination pagination = (Pagination)resultMap.get("pagination");
//		model.addAttribute("memberResponse", memberResponse);
//		model.addAttribute("articleList", articleList);
//		model.addAttribute("pagination", pagination);
		
		model.addAttribute("resultMap",resultMap);
		return "memberPages/info";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String profileUpdate() {
		return "memberPages/updateProfile";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String profileUpdate(HttpSession session, MemberProfileUpdateRequest dto) {
		Member member = (Member)session.getAttribute("member");
		dto.setId(member.getId());
		memberProfileService.updateProfile(dto);
		
		member.setEmail(dto.getEmail());
		member.setNickname(dto.getNickname());
		
		session.removeAttribute("member");
		session.setAttribute("member", member);
		return "memberPages/updateProfile";
	}
	
	@RequestMapping(value = "/passwordChange", method = RequestMethod.GET)
	public String passwordChange() throws IOException {
		return "memberPages/passwordChange";
	}
		
	@RequestMapping(value = "/passwordChange", method = RequestMethod.POST)
	public String passwordChange(HttpSession session, HttpServletResponse response, MemberPasswordChangeRequest dto) throws IOException {
		Member member = (Member)session.getAttribute("member");
		dto.setId(member.getId());
		
		if(!memberProfileService.changePassword(dto)) {
			return "memberPages/passwordChange";
		}
		
		memberProfileService.changePassword(dto);
		session.invalidate();
		return "memberPages/login";
	}

}
