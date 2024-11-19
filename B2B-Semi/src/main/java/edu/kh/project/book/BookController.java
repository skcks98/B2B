package edu.kh.project.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.project.book.model.service.BookService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

	private final BookService service;
	
	@PostMapping("insertBook")
	public String insertBook() {
		
		int result = service.insertBook();
		
		return "";
	}
	
}
