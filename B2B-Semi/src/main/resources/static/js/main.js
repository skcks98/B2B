const myPageBtn = document.querySelector("#myPageBtn")

// 쿠키에 저장된 이메일 input 창에 뿌려놓기
// 로그인이 안된 경우에 수행

// 쿠키에서 매개변수로 전달받은 key가 일치하는 value 얻어오기 함수
const getCookie = (key) => {

	const cookies = document.cookie; // "K=V; K=V; ... "

	// console.log(cookies); // saveId=user01@kh.or.kr; testKey=testValue

	// cookies 문자열을 배열 형태로 변환
	const cookieList = cookies.split("; ") // ["K=V", "K=V"...]
		.map(el => el.split("=")); // ["K", "V"]...

	// console.log(cookieList); // ['saveId', 'user01@kh.or.kr'], ['testKey', 'testValue']

	// 배열.map(함수) : 배열의 각 요소를 이용해 함수 수행후
	//                 결과 값으로 새로운 배열을 만들어서 반환

	// 배열 -> 객체로 변환 (그래야 다루기 쉽다)

	const obj = {}; // 비어있는 객체 선언

	for (let i = 0; i < cookieList.length; i++) {
		const k = cookieList[i][0]; // key 값
		const v = cookieList[i][1]; // value 값
		obj[k] = v;
		// obj["saveId"] = "user01@kh.or.kr";
		// obbj["testKey"] = "testValue";
	}

	// console.log(obj); // {saveId: 'user01@kh.or.kr'}

	return obj[key]; // 매개변수로 전달받은 key와
	// obj 객체에 저장된 key가 일치하는 요소의 value값 반환

}

getCookie();

// 이메일 작성 input 태그 요소
const loginEmail = document.querySelector("#loginForm input[name='memberEmail']"); // 이메일 input 태그

if (loginEmail != null) { // 로그인창의 이메일 input 태그가 화면에 존재할 때

	// 쿠키 중 key 값이 "saveId" 인 요소의 value 얻어오기
	const saveId = getCookie("saveId"); // 이메일 또는 undefined

	// saveId 값이 있을 경우
	if (saveId != undefined) {
		loginEmail.value = saveId; // 쿠키에서 얻어온 이메일 값을 input 요소의 value에 세팅

		// 아이디 저장 체크박스에 체크해두기
		document.querySelector("input[name='saveId']").checked = true;
	}

}

// 이메일, 비밀번호 미작성 시 로그인 막기
const loginForm = document.querySelector("#loginForm"); // form태그
const loginPw = document.querySelector("#loginForm input[name='memberPw']"); // 비밀번호 input 태그

// #loginForm 이 화면에 존재할 때 (== 로그인 상태 아닐 때)
// -> 타임리프에 의해 로그인 되었다면 #loginForm 요소는 화면에 노출되지 않음
// -> 로그인 상태일 때 loginForm 을 이용한 코드가 수행된다면
// -> 콘솔창에 error 발생

if (loginForm != null) {

	// 제출 이벤트 발생 시 
	loginForm.addEventListener("submit", e => {

		// 이메일 미작성
		if (loginEmail.value.trim().length === 0) {
			alert("이메일을 작성해주세요!");
			e.preventDefault(); // 기본 이벤트(제출) 막기
			loginEmail.focus();
		}

		// 비밀번호 미작성
		if (loginPw.value.trim().length === 0) {
			alert("비밀번호를 작성해주세요!");
			e.preventDefault(); // 기본 이벤트(제출) 막기
			loginPw.focus();
		}

	});

}

const selectMemberList = document.querySelector("#selectMemberList");
const resetPw = document.querySelector("#resetPw");
const restorationBtn = document.querySelector("#restorationBtn");
const insertBook = document.querySelector("#insertBook");

if (selectMemberList != null) {

	// 조회 버튼 클릭시
	selectMemberList.addEventListener("click", e => {

		// 기존의 데이터 비워주기
		const memberList = document.querySelector("#memberList");
		memberList.innerHTML = "";

		// 등록된 모든 회원 목록 가져오기
		fetch("/member/mainMemberList", {
			method: "POST",
			headers: { "Content-Type": "application/json" }
		})
			.then(resp => resp.json())
			.then(result => {

				// console.log(result);

				if (result.length === 0) {
					const tr = document.createElement("tr");
					tr.innerHTML = `<td colspan="4">멤버가 없습니다</td>`;
					memberList.append(tr);
					return;
				}

				// 한 줄씩 회원 정보 출력
				for (let i = 0; i < result.length; i++) {
					let tr = document.createElement("tr");

					let memberNo = document.createElement("td");
					memberNo.append(result[i].memberNo);

					let memberEmail = document.createElement("td");
					memberEmail.append(result[i].memberEmail);

					let memberNickname = document.createElement("td");
					memberNickname.append(result[i].memberNickname);

					let memberDelFl = document.createElement("td");
					memberDelFl.append(result[i].memberDelFl);

					// td태그의 내용들을 tr에 추가
					tr.append(memberNo, memberEmail, memberNickname, memberDelFl);

					// tr태그를 tbody인 memberList에 추가
					memberList.append(tr);
				}


			});

	});

}


if (resetPw != null) {

	// 비밀번호 초기화 버튼 클릭시
	resetPw.addEventListener("click", e => {

		const memberNo = document.querySelector("#resetMemberNo");

		// 숫자만 입력 정규식
		let regExp = /^[0-9]*$/;

		// 숫자가 아닌 값 일때
		if (!regExp.test(memberNo.value)) {
			alert("회원번호가 유효하지 않습니다");
			memberNo.focus();
			return;
		}

		// 내용이 비어있을때
		if (!memberNo.value) {
			alert("초기화 하려는 회원번호를 입력 해주세요.");
			memberNo.focus();
			return;
		}

		// 비밀번호 초기화 진행
		fetch("/member/resetPw", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: memberNo.value
		})
			.then(resp => resp.text())
			.then(result => {

				if (result > 0) {
					alert("비밀번호가 초기화 되었습니다(초기 비밀번호 pass01!)");

				} else {
					alert("비밀번호 초기화 실패");

				}

				// 모든 작업이 완료후 input값을 비워준다
				memberNo.value = "";

			});

	});

}


if (restorationBtn != null) {

	// 복구하기 버튼 클릭시
	restorationBtn.addEventListener("click", e => {

		const memberNo = document.querySelector("#restorationMemberNo");

		// 숫자만 입력 정규식
		let regExp = /^[0-9]*$/;

		// 숫자가 아닌 값 일때
		if (!regExp.test(memberNo.value)) {
			alert("회원번호가 유효하지 않습니다");
			memberNo.focus();
			return;
		}

		// 내용이 비어있을때
		if (!memberNo.value) {
			alert("탈퇴 복구 하려는 회원번호를 입력 해주세요.");
			memberNo.focus();
			return;
		}

		// 탈퇴 복구 진행
		fetch("/member/restorationMember", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: memberNo.value
		})
			.then(resp => resp.text())
			.then(result => {

				if (result > 0) {
					alert("해당 회원 번호의 탈퇴를 복구하였습니다");

				} else {
					alert("탈퇴 복구 실패");

				}

				// 모든 작업이 완료후 input값을 비워준다
				memberNo.value = "";

			});

	});

}

if (insertBook != null) {
	insertBook.addEventListener("click", e => {

		fetch("/book/insertBook", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
		})
			.then(resp => resp.text())
			.then(result => {

				alert("success");

			});

	});

}

const srchBookList = document.querySelector("#srchBookList");

if(srchBookList != null) {
	
	srchBookList.addEventListener("click", e => {
		
		const title = document.querySelector("#bookTitle");
		const bookList = document.querySelector("#bookList");
		bookList.innerHTML = "";

		fetch("/book/srchListBook", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: title.value
		})
		.then(resp => resp.json())
		.then(result => {

			if (result.length === 0) {
				const tr = document.createElement("tr");
				tr.innerHTML = `<td>해당 제목의 책이 없습니다</td>`;
				memberList.append(tr);
				return;
			}

			// 한 줄씩 회원 정보 출력
			for (let i = 0; i < result.length; i++) {
				let tr = document.createElement("tr");

				let bookTitle = document.createElement("td");
				bookTitle.append(result[i].title);
				
				let bookAuthor = document.createElement("td");
				bookAuthor.append(result[i].author);
				
				// 책 표지 (Cover) 이미지 생성
				let bookCoverTd = document.createElement("td"); // 이미지가 들어갈 칸
				let bookCoverImg = document.createElement("img"); // 이미지 태그 생성
				bookCoverImg.src = result[i].coverUrl; // 이미지 경로 설정
				bookCoverImg.alt = "Book Cover"; // 대체 텍스트 설정 (옵션)
				bookCoverImg.style.width = "100px"; // 이미지 크기 조정 (옵션)
				bookCoverImg.style.height = "auto"; // 비율 유지

				bookCoverTd.appendChild(bookCoverImg); // td 태그에 이미지 추가
				
				
				// td태그의 내용들을 tr에 추가
				tr.append(bookTitle, bookAuthor, bookCoverTd);

				// tr태그를 tbody인 memberList에 추가
				bookList.append(tr);
			}
			

		});

	});
	
} 

myPageBtn.addEventListener("click", ()=>{
  location.href= "/myPage-info"
});
