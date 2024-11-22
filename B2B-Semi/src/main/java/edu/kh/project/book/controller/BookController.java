package edu.kh.project.book.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.project.book.model.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("book")
@Controller
@RequiredArgsConstructor
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
	
}
