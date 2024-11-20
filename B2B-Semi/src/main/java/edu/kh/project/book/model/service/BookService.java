package edu.kh.project.book.model.service;

import java.util.List;

import edu.kh.project.book.model.dto.Book;

public interface BookService {

	int insertBook();

	List<Book> srchBookList(String title);

}
