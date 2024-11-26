package edu.kh.project.member.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.book.model.dto.Book;
import edu.kh.project.member.model.dto.Member;

@Mapper
public interface AdminMapper {

	/** 도서 목록 조회
	 * @return
	 */
	List<Book> selectBookList();

	/** 게시글 수 조회.
	 * @param boardCode
	 * @return
	 */
	int getListCount(int boardCode);

	/** 특정 게시판의 지정된 페이지 목록 조회.
	 * @param boardCode
	 * @param rowBounds
	 * @return
	 */
	List<Board> selectBoardList(int boardCode, RowBounds rowBounds);

	List<Member> selectMemberList();

	/** 회원 정보 수정(관리자)
	 * @param inputMember
	 * @return
	 */
	int updateMember(Member inputMember);

	int updateInfo(Member inputMember);

	Member selectedMember(String memberId);

	List<Member> searchMember(Map<String, Object> map);

	int updateStatus(@Param("memberIds")List<String> memberIds, @Param("status") String status);


}
