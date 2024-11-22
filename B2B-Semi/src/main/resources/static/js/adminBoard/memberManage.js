const searchBookListBtn = document.querySelector("#searchBtn"); // 도서 관리 검색창
const tbody = document.querySelector("#tbody");
const input = document.querySelector("#searchInput");

searchBookListBtn.addEventListener("click", e => {

  if(input.value.trim().length === 0) {
    alert("검색어를 입력해주세요.");
    e.preventDefault();
    input.focus();
  }

  fetch("/adminBoard/selectMemberList")
  .then(resp => resp.json())
  .then(memberList => {
    tbody.innerHTML = "";

    for(let member of memberList) {
      const tr = document.createElement("tr");

      const arr = ['memberNo', 'memberNickname', 'memberName', 'enrollDate', 'memberDelFl'];

      for(let key of arr) {
        const td = document.createElement("td");

        td.innerText = member[key];
        tr.append(td);
      }
      tbody.append(tr);

    }
  })
});