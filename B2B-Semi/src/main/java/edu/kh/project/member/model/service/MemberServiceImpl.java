package edu.kh.project.member.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

import edu.kh.project.member.model.dto.Member;
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberMapper mapper;
}
