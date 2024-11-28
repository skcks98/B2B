/* 목록으로 돌아가는 버튼 */
const goToListBtn = document.querySelector("#communityList");

goToListBtn.addEventListener("click", () => {

	let path = location.pathname.split("/");
	path[2] = "community";

	// 새로운 경로 조합
	const newUrl = path.join("/") + location.search;

	console.log(newUrl);
	location.href = newUrl;
});

const deleteBtn = document.querySelector("#deleteBtn");

if (deleteBtn != null) {
	deleteBtn.addEventListener("click", () => {

		if (!confirm("삭제 하시겠습니까?")) {
			alert("취소됨")
			return;
		}

		const url = location.pathname.replace("board", "editBoard") + "/delete";

		// form태그 생성
		const form = document.createElement("form");
		form.action = url;
		form.method = "POST";

		// cp값을 저장할 input 생성
		const cpInput = document.createElement("input");
		cpInput.type = "hidden";
		cpInput.name = "cp";
		
		const srchtype = document.createElement("input");
		srchtype.type = "hidden";
		srchtype.name = "searchType";
		
		const srchInput = document.createElement("input");
		srchInput.type = "hidden";
		srchInput.name = "searchInput";

		// 쿼리스트링에서 원하는 파라미터 얻어오기
		const params = new URLSearchParams(location.search)
		const type = params.get("searchType");
		srchtype.value = type;
		
		const inp = params.get("searchInput");
		const encodedSearchInput = encodeURIComponent(inp);
		srchInput.value = encodedSearchInput;
		
		const cp = params.get("cp");
		cpInput.value = cp;

		form.append(srchtype);
		form.append(srchInput);
		form.append(cpInput);

		// 화면에 form태그를 추가한 후 제출하기
		document.querySelector("body").append(form);
		form.submit();

	});
}