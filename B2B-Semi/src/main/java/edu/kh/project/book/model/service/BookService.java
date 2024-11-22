package edu.kh.project.book.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.book.model.dto.Book;

public interface BookService {

	/** api 도서 등록 (테스트 버전)
	 * @return
	 */
	int insertBook();

	/** api 도서 검색 (테스트 버전)
	 * @param title
	 * @return
	 */
	List<Book> srchBookList(String title);

	/** 도서 목록 조회
	 * @return book
	 */
	Map<String, Object> bookList(int cp);

	/** 도서 목록 검색 조회
	 * @param cp
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> bookSearchList(int cp, Map<String, Object> paramMap);

}
