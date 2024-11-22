package edu.kh.project.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.book.model.dto.Book;
import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("adminBoard")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService service;
	
	
	@GetMapping("dashAdmin")
	public String dashAdmin() {
		return "adminBoard/dashAdmin";
	}
	
	
	@GetMapping("bookManage")
	public String bookManage() {
		return "adminBoard/bookManage";
	}
	
	
	@GetMapping("boardManage")
	public String boardManage() {
		return "adminBoard/boardManage";
	}
	
	
	@GetMapping("memberManage")
	public String memberManage() {
		return "adminBoard/memberManage";
	}
	
	
	@ResponseBody
	@GetMapping("selectBookList")
	public List<Book> selectBookList() {
		
		List<Book> bookList = service.selectBookList();
		
		return bookList;
	}
	
	@ResponseBody
	@GetMapping("{boardCode:[0-9]+}")
	public String selectBoardList(@PathVariable("boardCode") int boardCode,
			@RequestParam(value="cp", required=false, defaultValue = "1") int cp,
			Model model,
			@RequestParam Map<String, Object> paramMap) {
		
		
		Map<String, Object> map = null;
		
		if(paramMap.get("key") == null) {
			map = service.selectBoardList(boardCode, cp);
		}
		
		else {
			paramMap.put("boardCode", boardCode);
			
			map = service.searchList(paramMap, cp);
		}
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("boardList", map.get("boardList"));
		
		return "adminBoard/boardManage";
	}
	
	
	@ResponseBody
	@GetMapping("selectMemberList")
	public List<Member> selectMemberList() {
		
		List<Member> memberList = service.selectMemberList();
		
		return memberList;
		
	}
	
	
	
	
	
	
	
	
	
	
}
