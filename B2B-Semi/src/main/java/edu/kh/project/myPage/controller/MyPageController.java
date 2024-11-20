package edu.kh.project.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.project.myPage.model.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@RestController // @ResponseBody 계속 설정해줄 필요 없음
@Controller
@RequestMapping("myPage")
@RequiredArgsConstructor
@Slf4j

//fetch - 비동기 요청
// "/myPage" 요청이 오면 해당 컨트롤러에서 잡아서 처리함
// @ResponseBody를 매번 메서드에 추가

public class MyPageController {

	private final MyPageService service;

	// 내정보 화면 이동
	@GetMapping("info") // /myPage/info GET 방식 요청
	public String info() {

		
		
		return "myPage/myPage-info";
	}

	
	
	
	@PostMapping("info")
	public String updateInfo() {
		
		// POST 요청에 대한 처리 로직 작성
		// 예를 들어, 폼에서 전달된 데이터를 처리하고 DB에 업데이트

		return "redirect:/myPage/info"; // POST 후 다시 GET 요청으로 리다이렉트 (정보 갱신 후 내 정보 페이지로 돌아감)
	}

}
