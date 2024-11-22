const searchBookListBtn = document.querySelector("#searchBtn"); // 도서 관리 검색창
const tbody = document.querySelector("#tbody");
const input = document.querySelector("#searchInput");

searchBookListBtn.addEventListener("click", e => {

  if(input.value.trim().length === 0) {
    alert("검색어를 입력해주세요.");
    e.preventDefault();
    input.focus();
  }

  fetch("/adminBoard/selectBookList")
  .then(resp => resp.json())
  .then(bookList => {
    tbody.innerHTML = "";

    for(let book of bookList) {
      const tr = document.createElement("tr");

      const arr = ['bookId', 'title', 'author', 'createdAt', 'isDeleted'];

      for(let key of arr) {
        const td = document.createElement("td");

        td.innerText = book[key];
        tr.append(td);
      }
      tbody.append(tr);

    }
  })
});