const searchBookListBtn = document.querySelector("#searchBtn"); // 도서 관리 검색창
const tbody = document.querySelector("#tbody");
const input = document.querySelector("#searchInput");

searchBookListBtn.addEventListener("click", e => {

  if(input.value.trim().length === 0) {
    alert("검색어를 입력해주세요.");
    e.preventDefault();
    input.focus();
  }

  fetch("/adminBoard/selectBoardList")
  .then(resp => resp.json())
  .then(boardList => {
    tbody.innerHTML = "";

    for(let board of boardList) {
      const tr = document.createElement("tr");

      const arr = ['boardNo', 'boardTitle', 'memberNickname', 'boardWriteDate', 'boardDelFl'];

      for(let key of arr) {
        const td = document.createElement("td");

        td.innerText = board[key];
        tr.append(td);
      }
      tbody.append(tr);

    }
  })
});