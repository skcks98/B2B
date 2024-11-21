package edu.kh.project.myPage.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.myPage.model.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@RestController // @ResponseBody 계속 설정해줄 필요 없음


//fetch - 비동기 요청
// "/myPage" 요청이 오면 해당 컨트롤러에서 잡아서 처리함
// @ResponseBody를 매번 메서드에 추가

@Controller
@RequestMapping("myPage")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

	private final MyPageService service;

	// 내정보 화면 이동
	@GetMapping("info") // /myPage/info GET 방식 요청
	public String info() {
		return "myPage/myPage-info";
	}
	
	
	// 찜한 도서 목록 이동
	@GetMapping("favBook") // /myPage/favBook GET 방식 요청
	public String favBook() {
		return "myPage/myPage-favBook";
	}
	
	// 게시글 목록 이동
	@GetMapping("boardList") // /myPage/boardList GET 방식 요청
	public String boardList() {
		return "myPage/myPage-boardList";
	}
	
	// 댓글 목록 이동
	@GetMapping("commentList") // /myPage/commentList GET 방식 요청
	public String commentList() {
		return "myPage/myPage-commentList";
	}
	
	
	// 비밀번호 변경 이동
	@GetMapping("changePw") // /myPage/changePw  GET 방식 요청
	public String changePw() {
		return "myPage/myPage-changePw";
	}
	

	
	
	
	//---------------------------- POST -------------------------------------
	
	
	
	
	/** 내정보 수정
	 * @param inputMember : 커맨드 객체(@ModelAttribute 생략된 상태) 제출된 회원 닉네임, 전화번호, 주소
	 * @param loginMember : 로그인한 회원 정보 (회원 번호 사용할 예정)
	 * @param memberAddress : 주소만 따로 받은 String[]
	 * @param ra : 리다이렉트 시 request scope로 message같은 데이터 전달
	 * @return
	 */
	@PostMapping("info")
	public String updateInfo() {
		
		// POST 요청에 대한 처리 로직 작성
		// 예를 들어, 폼에서 전달된 데이터를 처리하고 DB에 업데이트

		return "redirect:/myPage/info"; // POST 후 다시 GET 요청으로 리다이렉트 (정보 갱신 후 내 정보 페이지로 돌아감)
	}
	
	// 로그인
	@GetMapping("login")
	public String login() {
		return "header-footer-side";
	}
	//회원가입
	@GetMapping("signup")
	public String signup() {
		return "header-footer";
	}
	
	
	
	/** 찜한 도서 목록 출력
	 * @return
	 */
	@PostMapping("favBook")
	public String uploadFavbook() {
		
		
		
		return null;
		
	}
	
	
	
	/** 내가 작성한 게시글 목록 출력
	 * @return
	 */
	@PostMapping("boardList")
	public String uploadBoardList() {
		
		
		
		
		return null;
		
	}
	
	
	/** 내가 작성한 댓글 목록 출력
	 * @return
	 */
	@PostMapping("commentList")
	public String uploadCommentList() {
		
		
		
		return null;
		
	}
	

	
	
	/** 비밀번호 변경
	 * @param paramMap
	 * @param ra
	 * @return
	 */
	@PostMapping("changePw") // 		/myPage/changePw POST 요청 매핑
	public String changePw(/*@SessionAttribute("loginMember") Member loginMember*/
							@RequestParam Map<String, Object> paramMap,
							RedirectAttributes ra
							) {
		
		return "";
	}
	
	
	
	
	
	

}
