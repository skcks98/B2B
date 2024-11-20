package edu.kh.project.myPage.model.service;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class) // 모든 예외 발생시 롤백 / 아니면 커밋
@RequiredArgsConstructor
@Slf4j
@PropertySource("classpath:/config.properties")

public class MyPageServiceImpl implements MyPageService{

}
