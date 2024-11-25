package edu.kh.project.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member")
@SessionAttributes({"loginMember"})
@RequiredArgsConstructor
public class MemberController {

	private final MemberService service;

	@GetMapping("login")
	public String login() {
		return "/member/login";
	}
	
	@PostMapping("login")
	public String login(/*@ModelAttribute*/ Member inputMember, 
						RedirectAttributes ra,
						Model model,
						@RequestParam(value="saveId", required = false) String saveId,
						HttpServletResponse resp ) {
		
		// 체크박스
		// - 체크가    된 경우 : "on"
		// - 체크가  안된 경우 : null
		
		// 로그인 서비스 호출
		Member loginMember = service.login(inputMember);
		
		// 로그인 실패 시
		if(loginMember == null) {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
		} else {
			
			// Session scope에 loginMember 추가
			model.addAttribute("loginMember", loginMember);
			// 1단계 : request scope 에 세팅됨
			// 2단계 : 클래스 위에 @SessionAttributes() 어노테이션 작성하여
			//		   session scope로 이동
			
			// **********Cookie************
			
			// 이메일 저장 
			
			// 쿠키 객체 생성 (K:V)
			Cookie cookie = new Cookie("saveId", loginMember.getMemberId());
			// saveId=user01@kh.or.kr
			
			// 쿠키가 적용될 경로 설정
			// -> 클라이언트가 어떤 요청을 할 때 쿠키가 첨부될지 지정
			
			// ex)  "/"  :  IP 또는 도메인 또는 localhost
			//			   --> 메인페이지 + 그 하위 주소 모두 
			cookie.setPath("/");
			
			// 쿠키의 만료 기간 지정
			cookie.setMaxAge(60 * 60 * 24 * 30); // 30일 (초 단위로 지정)
			
			// 응답 객체에 쿠키 추가 -> 클라이언트 전달
			resp.addCookie(cookie);
			
		}
		
		
		return "redirect:/"; // 메인페이지 재요청
	}
	
	@GetMapping("submit")
	public String submit() {
		return "/member/submit";
	}
	
	@PostMapping("firstProfile")
	public String signUpComplete() {
		return "/member/firstProfile";
	}
	
	@GetMapping("signUp")
	public String signUp() {
		return "/member/signUp";
	}
	
	
}
