package edu.kh.project.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.book.model.dto.Book;
import edu.kh.project.common.util.Pagination;
import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

	private final AdminMapper mapper;
	

	@Override
	public Map<String, Object> selectBoardList(int boardCode, int cp) {

		int listCount = mapper.getListCount(boardCode);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Board> boardList = mapper.selectBoardList(boardCode, rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		return map;
	}

	@Override
	public Board selectOne(Map<String, Integer> map) {

		return mapper.selectOne(map);
	}
	
	@Override
	public Map<String, Object> boardList(int cp) {
		
		int boardCount = mapper.boardCount();
		
		Pagination pagination = new Pagination(cp, boardCount);
		
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Board> boardList = mapper.boardList(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		return map;
	}
	
	@Override
	public Map<String, Object> bookList(int cp) {

		int bookCount = mapper.bookCount();
		
		Pagination pagination = new Pagination(cp, bookCount);
		
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Book> bookList = mapper.bookList(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("bookList", bookList);
		
		return map;
	}
	
	@Override
	public Map<String, Object> bookSearchList(int cp, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public Map<String, Object> boardSearchList(int cp, Map<String, Object> paramMap) {
		
		return null;
	}
	
	
	
	
	@Override
	public List<Member> selectMemberList() {

		return mapper.selectMemberList();
	}
	
	@Override
	public int updateMember(Member inputMember) {
		
		return mapper.updateMember(inputMember);
	}
	
	
	@Override
	public int updateInfo(Member inputMember, String[] memberAddress) {

		if(memberAddress == null || memberAddress.length == 0) {
			inputMember.setMemberAddress(null);
		}
		
		else {
			String address = String.join("^^^", memberAddress);
			inputMember.setMemberAddress(address);
		}
		
		return mapper.updateInfo(inputMember);
	}
	
	
	@Override
	public Member selectedMember(String memberId) {

		return mapper.selectedMember(memberId);
	}
	
	@Override
	public List<Member> searchMember(Map<String, Object> paramMap) {
		
//		Map<String, Object> map = new HashMap<>();
//		
//		map.put("key", paramMap.get("key"));
//		map.put("search", paramMap.get("search"));

		return mapper.searchMember(paramMap);
	}
	
	@Override
	public List<Board> searchBoard(Map<String, Object> paramMap) {

		return mapper.searchBoard(paramMap);
	}
	
	
	@Override
	public int updateStatus(List<String> memberIds, boolean updateY) {
		String status = updateY ? "Y" : "N";
		
		return mapper.updateStatus(memberIds, status);
	}
	
	@Override
	public int updateBoardStatus(List<String> boardList, boolean updateY) {
		String status = updateY ? "Y" : "N";
		
		return mapper.updateBoardStatus(boardList, status);
	}

	
	@Override
	public int updateBookStatus(List<String> bookList, boolean updateY) {

		String status = updateY ? "Y" : "N";
		return mapper.updateBookStatus(bookList, status);
	}
	
	
	@Override
	public int insertNewBook(Map<String, Object> paramMap) {

		return mapper.insertNewBook(paramMap);
	}
	
}