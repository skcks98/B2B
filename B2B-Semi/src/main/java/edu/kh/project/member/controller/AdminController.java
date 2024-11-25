package edu.kh.project.member.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	private final AdminService Adservice;
	
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
		
		List<Book> bookList = Adservice.selectBookList();
		
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
			map = Adservice.selectBoardList(boardCode, cp);
		}
		
		else {
			paramMap.put("boardCode", boardCode);
			
			map = Adservice.searchList(paramMap, cp);
		}
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("boardList", map.get("boardList"));
		
		return "adminBoard/boardManage";
	}
	
	
	@ResponseBody
	@GetMapping("selectMemberList")
	public List<Member> selectMemberList() {
		
		List<Member> memberList = Adservice.selectMemberList();
		
		return memberList;
		
	}
	
	
	@GetMapping("updateMember")
	public String updateMember(@RequestParam("memberId") String memberId, Model model) {
		
		Member selectedMember = Adservice.selectedMember(memberId);
		
		String path = null;
		
		if(selectedMember != null) {
			path = "adminBoard/updateMember";
			
			model.addAttribute("member", selectedMember);
		}
		else {
			path = "redirect:memberManage";
		}
		return path;
	}
	
	@GetMapping("info")
	public String info(Member inputMember, Model model) {

		String memberAddress = inputMember.getMemberAddress();
		
		if(memberAddress != null) {
			String[] arr = memberAddress.split("\\^\\^\\^");
			
			model.addAttribute("postcode", arr[0]);
			model.addAttribute("address", arr[1]);
			model.addAttribute("detailAddress", arr[2]);
		}
		
		return "redirect:info";
	}
	
	@PostMapping("info")
	public String updateInfo(RedirectAttributes ra,Member inputMember, @RequestParam(value="memberId") String memberId, @RequestParam("memberAddress") String[] memberAddress) {
		
		Member member = Adservice.selectedMember(memberId);
		
		int result = Adservice.updateInfo(inputMember, memberAddress);

		String message = null;
		
		if(result > 0) {
			message = "회원 정보 수정 성공함.";
		}
		else {
			message = "정보 수정 실패...";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:memberManage";
	}
	
}
