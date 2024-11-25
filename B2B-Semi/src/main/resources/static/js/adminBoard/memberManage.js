// 다음 주소 API
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
            document.getElementById("detailAddress").value = '';
        }
    }).open();
}

// 주소 검색 버튼 클릭 시 위의 함수 실행 (myPage-info.html 외에도 문제가 발생하지 않도록 예외처리)
const searchAddress = document.querySelector("#searchAddress");
if(searchAddress != null) { // 화면상에 id가 searchAddress인 요소가 존재하는 경우에만.
    
    searchAddress.addEventListener("click", execDaumPostcode);
}

const searchBookListBtn = document.querySelector("#searchBtn"); // 도서 관리 검색창
const tbody = document.querySelector("#tbody");
const input = document.querySelector("#searchInput");
const checkAll = document.querySelector("#theadCheckAll");

searchBookListBtn.addEventListener("click", e => {

  if(input.value.trim().length === 0) {
    alert("검색어를 입력해주세요.");
    e.preventDefault();
    input.focus();
//	return;
  }

  fetch("/adminBoard/selectMemberList")
  .then(resp => resp.json())
  .then(memberList => {
    tbody.innerHTML = "";

    for(let member of memberList) {
      const tr = document.createElement("tr");

      tr.innerHTML = `
        <td><a href="gotoUpdate" class=hidden>${member.memberId}</a></td>
        <td>${member.memberNickname}</td>
        <td>${member.enrollDate}</td>
        <td>${member.memberDelFl ? "활성" : "탈퇴"}</td>
        <td><button class="updateMemberBtn" onclick='updateMember(${member.memberId})'>회원 수정</button></td>
        <td><input class="checkbox" type="checkbox" value="${member.id}"></td>
        `;

      tbody.appendChild(tr);
    }
    
  }).catch(error => {
	console.error("회원 목록 불러오기 중 오류:", error);
  });
  
});
/*
function updateMember(memberId) {
	console.log(memberId);
	
	fetch("/adminBoard/selectMemberList")
	.then(resp => resp.json())
	.then(member => {
		document.getElementById("memberNickname").value = member.memberNickname;
		document.getElementById("memberTel").value = member.memberTel;
		document.getElementById("memberAddress").value = member.memberAddress;

	})
	
	location.href= `adminBoard/updateMember?memberId=${memberId}`;
}*/


tbody.addEventListener("click", e => {
	if(e.target.classList.contains("updateMemberBtn")) {
		const memberId = e.target.closest("tr").querySelector("a").textContent;
		location.href = `updateMember?memberId=${memberId}`;
	}
});


document.querySelector("#theadCheckAll").addEventListener("change", e => {
	const checkboxes = tbody.querySelectorAll(".checkbox");
	checkboxes.forEach(checkbox => {
		checkbox.checked = e.target.checked;
	});
});

tbody.addEventListener("change", e => {
	if(e.target.classList.contains("checkbox")) {
		const checkboxes = tbody.querySelectorAll(".checkbox");
		const allChecked = Array.from(checkboxes).every(cb => cb.checked);
		document.querySelector("#theadCheckAll").checked = allChecked;
	}
})