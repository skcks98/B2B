package edu.kh.project.myPage.model.service;

import java.util.Map;

import edu.kh.project.member.model.dto.Member;

public interface MyPageService {

	
	// 회원 정보 수정
	int editInfo(Member inputMember, String[] memberAddress);
	
	// 비밀번호 변경
	int changePw(Map<String, Object> paramMap, int memberNo);

	

}
