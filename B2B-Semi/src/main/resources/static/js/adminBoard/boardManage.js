const searchBookListBtn = document.querySelector("#searchBtn"); // 도서 관리 검색창
const tbody = document.querySelector("#tbody");
const input = document.querySelector("#searchInput");

document.addEventListener("DOMContentLoaded", () => {
	const searchParams = new URLSearchParams(window.location.search);
	if(!searchParams.has("key")|| !searchParams.has("search")) {
		fetchBoardList();
	}
});

function fetchBoardList() {
	
}