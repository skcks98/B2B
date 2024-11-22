package edu.kh.project.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService service;

	@GetMapping("login")
	public String login() {
		return "/member/login";
	}
	
	@GetMapping("signUp")
	public String signUp() {
		return "/member/signUp";
	}
	
	@PostMapping("firstProfile")
	public String signUpComplete() {
		return "/member/firstProfile";
	}
}
