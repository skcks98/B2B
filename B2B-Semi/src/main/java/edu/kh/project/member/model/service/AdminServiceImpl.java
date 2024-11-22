package edu.kh.project.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.Pagination;
import edu.kh.project.book.model.dto.Book;
import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

	private final AdminMapper mapper;
	
	
	@Override
	public List<Book> selectBookList() {

		return mapper.selectBookList();
	}
	
	
	
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
	public Map<String, Object> searchList(Map<String, Object> paramMap, int cp) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Member> selectMemberList() {

		return mapper.selectMemberList();
	}
	
	
}
