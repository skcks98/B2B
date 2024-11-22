package edu.kh.project.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.book.model.dto.Book;
import edu.kh.project.book.model.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("book")
@RequiredArgsConstructor
@Slf4j
public class BookController {

	private final BookService service;
	
	@PostMapping("insertBook")
	public String insertBook() {
		
		int result = service.insertBook();
		
		return "";
	}
	
	
	@ResponseBody
	@PostMapping("srchListBook")
	public List<Book> srchListBook(@RequestBody String title,
			Model model) {
		
		List<Book> srchBookList = service.srchBookList(title);
		
		return srchBookList;
	}
	
	
}
