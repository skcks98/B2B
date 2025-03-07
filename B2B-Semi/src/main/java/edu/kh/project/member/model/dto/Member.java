package edu.kh.project.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberNickname;
	private String memberTel;
	private String memberEmail;
	private String memberAddress;
	private String profileImg;
	private String enrollDate;
	private Boolean memberDelFl;
	private int memberAuth;
	private String memberBookCategory;

}
