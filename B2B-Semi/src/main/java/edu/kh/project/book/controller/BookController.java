package edu.kh.project.book.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.project.book.model.service.BookService;
import edu.kh.project.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("book")
@Controller
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
@Slf4j
public class BookController {

	private final BookService service;
	
	
	/** 도서 목록 조회
	 * @param model
	 * @return
	 */
	@GetMapping("bookList")
	public String bookList(Model model,
			@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap
			) {
		
		Map<String, Object> map = null;
		
		if (!paramMap.isEmpty()) {
		    paramMap.remove("cp"); // paramMap에서 cp 제거
		}
		
		if(paramMap.isEmpty()) {
			// 도서 목록 조회
			map = service.bookList(cp);
			
		} else {
			// 도서 목록 검색 조회
			map = service.bookSearchList(cp, paramMap);
			
		}
		
		
		// 데이터 전달
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("bookList", map.get("bookList"));
		
		log.debug("rowNum : " + map.get("bookList"));
		
		
		// 검색 데이터 전달
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("searchType", paramMap.get("searchType"));
		model.addAttribute("searchInput", paramMap.get("searchInput"));
		model.addAttribute("genreFilter", paramMap.get("genreFilter"));
		model.addAttribute("ratingFilter", paramMap.get("ratingFilter"));
		
		
		return "book/bookList";
	}
	
	
	/** 해당 도서 리뷰 목록 조회
	 * @param bookId
	 * @return
	 */
	@ResponseBody
	@GetMapping("selectReviewList")
	public List<Map<String, Object>> selectReviewList(@RequestParam("bookId") int bookId) {
		return service.selectReviewList(bookId);
	}
	
	
	
	/** 책 리뷰 작성
	 * @param paramMap
	 * @return
	 */
	@ResponseBody
	@PostMapping("insertBookReview")
	public int insertBookReview(@RequestBody Map<String, Object> paramMap,
								@SessionAttribute(value="loginMember", required = false) Member loginMember) {
		return service.insertBookReview(paramMap);
	}
	
}
