package edu.kh.project.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	private int memberNo;					// 회원 번호
	private String memberId; 				// 회원 아이디
	private String memberPw; 				// 회원 비밀번호
	private String memberNickname; 			// 회원 닉네임
	private String memberTel; 				// 회원 전화번호
	private String memberAddress; 			// 회원 주소
	private String profileImg; 				// 회원 프로필 이미지
	private String enrollDate; 				// 회원 가입일
	private String memberDelFl;				// 회원 탈퇴 여부
	private int memberAuth; 				// 회원 인증키
	private String memberBookCategory; 		// 회원 도서 선호 장르
	
}
