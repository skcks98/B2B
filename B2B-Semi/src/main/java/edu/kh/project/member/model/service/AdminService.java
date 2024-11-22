package edu.kh.project.member.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.book.model.dto.Book;
import edu.kh.project.member.model.dto.Member;

public interface AdminService {

	/** 도서 목록 조회.
	 * @return
	 */
	public List<Book> selectBookList();

	public Map<String, Object> selectBoardList(int boardCode, int cp);

	public Map<String, Object> searchList(Map<String, Object> paramMap, int cp);

	public List<Member> selectMemberList();

}
