package edu.kh.project.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.book.model.dto.Book;
import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
//		List<Member> memberList = Adservice.selectMemberList();
//		
//		for(Member member : memberList) {
//			model.addAttribute("memberId", member.getMemberId());
//			model.addAttribute("memberNickname", member.getMemberNickname());
//			model.addAttribute("enrollDate", member.getEnrollDate());
//			model.addAttribute("memberDelFl", member.getMemberDelFl());
//		}
		
		return "adminBoard/memberManage";
	}
	
	@ResponseBody
	@GetMapping("selectMemberList")
	public List<Member> selectMemberList() {
		
		List<Member> memberList = Adservice.selectMemberList();
		
		return memberList;
		
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
	
	@GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}")
	public String boardDetail(@PathVariable("boardCode") int boardCode, @PathVariable("boardNo") int boardNo, Model model, RedirectAttributes ra, HttpServletRequest req, HttpServletResponse resp) {
		
		Map<String, Integer> map = new HashMap<>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		
		Board board = Adservice.selectOne(map);
		
		String path = null;
		
		if(board == null) {
			path = "redirect:/adminBoard/boardManage" + boardCode;
			ra.addFlashAttribute("message", "게시글이 존재하지 않습니다.");
		}
		path = "adminBoard/boardManage";
		
		model.addAttribute("board", board);
		
		return path;
	}

	
	
	
	/** 회원 정보 수정
	 * @param memberId
	 * @param model
	 * @return
	 */
	@GetMapping("updateMember")
	public String updateMember(@RequestParam("memberId") String memberId, Model model) {
		
		Member selectedMember = Adservice.selectedMember(memberId);
		
		log.debug("selectedMember : " + selectedMember);
		
		String path = null;
		
		if(selectedMember != null) {
			path = "adminBoard/updateMember";
			
			model.addAttribute("member", selectedMember);
			
			String[] arr = selectedMember.getMemberAddress().split("\\^\\^\\^");
			model.addAttribute("postcode", arr[0]);
			model.addAttribute("address", arr[1]);
			model.addAttribute("detailAddress", arr[2]);
			
		}
		else {
			path = "redirect:memberManage";
		}
		return path;
	}
	
	/** 회원 정보 수정
	 * @param ra
	 * @param inputMember
	 * @param memberId
	 * @param memberAddress
	 * @return
	 */
	@PostMapping("info")
	public String updateInfo(RedirectAttributes ra,Member inputMember, @RequestParam(value="memberId") String memberId, @RequestParam("memberAddress") String[] memberAddress) {

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
	
	/** 검색을 통한 회원 검색
	 * @param paramMap
	 * @param model
	 * @return
	 */
	@GetMapping("searchMember")
	public String searchMember(@RequestParam Map<String, Object> paramMap, Model model) {
		
		List<Member> memberList = Adservice.searchMember(paramMap);
		
		log.debug("memberList :" + memberList); // map :{MEMBER_DEL_FL=N, ENROLL_DATE=2024년 11월 22일, MEMBER_ID=user03, MEMBER_NICKNAME=유저삼}

		model.addAttribute("memberList", memberList);
		
		return "adminBoard/memberManage";
	}
	
	
	/** 활성/탈퇴 복구
	 * @param paramMap
	 * @return
	 */
	@ResponseBody
	@PostMapping("updateStatus")
	public ResponseEntity<?> updateStatus(@RequestBody Map<String, Object> paramMap) {
		
		List<String> memberIds = (List<String>)paramMap.get("memberIds");
		String action = (String) paramMap.get("action");
		
		boolean updateY = action.equals("탈퇴");
		
		log.debug("memberIds :" + memberIds);
		
		int result = Adservice.updateStatus(memberIds, updateY);
		
		if(result > 0) {
			return ResponseEntity.ok(Map.of("success", true));
		}
		else {
			return ResponseEntity.ok(Map.of("success", false, "message", "업데이트 실패.."));
		}
		
	}
	
	
	
	
	
	
	
	
}