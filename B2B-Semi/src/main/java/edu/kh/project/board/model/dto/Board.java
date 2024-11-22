package edu.kh.project.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

	private int boardNo; 				// 게시글 번호
	private String boardTitle; 			// 게시글 제목
	private String boardContent; 		// 게시글 상세 내용
	private String boardWriteDate; 		// 게시글 작성일
	private String boardUpdateDate; 	// 게시글 마지막 수정일
	private int readCount; 				// 조회 수
	private String boardDelFl; 			// 게시글 삭제여부
	private int memberNo; 				// 회원 번호
	private int boardCode;				// 종류 코드번호
	private int bookId; 				// 도서 아이디
	private int recommentCount; 		// 추천수
	
	// MEMBER 테이블 조인.
	private String memberNickname;
	
	// 게시글 작성자 프로필 이미지.
	private String profileImg;
	
}
