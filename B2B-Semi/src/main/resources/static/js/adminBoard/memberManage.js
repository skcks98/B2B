const searchMemberListBtn = document.querySelector("#searchBtn"); // 도서 관리 검색창
const tbody = document.querySelector("#tbody");
const input = document.querySelector("#searchInput");
const checkAll = document.querySelector("#theadCheckAll");

searchMemberListBtn.addEventListener("click", () => {

	const key = document.querySelector("#searchKey").value;
	const query = input.value.trim();

	window.location.href = `/adminBoard/searchMember?key=${key}&search=${query}`;
  
});


// 체크박스 전체 선택.
document.querySelector("#theadCheckAll").addEventListener("change", e => {
	const checkboxes = tbody.querySelectorAll(".checkbox");
	checkboxes.forEach(checkbox => {
		checkbox.checked = e.target.checked;
	});
});

// 체크박스 전체 선택 동기화.
tbody.addEventListener("change", e => {
	if(e.target.classList.contains("checkbox")) {
		const checkboxes = tbody.querySelectorAll(".checkbox");
		const allChecked = Array.from(checkboxes).every(cb => cb.checked);
		document.querySelector("#theadCheckAll").checked = allChecked;
	}
})

const updateDelFlYBtn = document.querySelector("#updateDelFlY").addEventListener("click", () => updateMemberStatus('탈퇴'));
const updateDelFlNBtn = document.querySelector("#updateDelFlN").addEventListener("click", () => updateMemberStatus('복구'));

function updateMemberStatus(action) {
	
	const checkboxes = tbody.querySelectorAll(".checkbox:checked");
	if (checkboxes.length === 0) {
		alert("선택된 회원이 없습니다.");
		return;
	}
	
	const memberNos = Array.from(checkboxes).map(box => box.value);
	
	console.log(memberNos);
	
	fetch("/adminBoard/updateStatus", {
		method : "POST",
		headers : {"Content-type" : "application/json"},
		body : JSON.stringify({
			memberNos: memberNos,
			action: action
		})
		
	})
	.then(response => response.json())
	.then(data => {
		if(data.success) {
			alert(`회원 상태가 ${action}으로 변경됐습니다.`);
			location.reload();
		}
		else {
			alert("상태 변경 실패:" + data.message);
		}
	})
	.catch(error => console.error("Error:",error));
}

const updateMemberBtn = document.querySelectorAll("button[name='updateMemberBtn']").forEach(button => {
	button.addEventListener("click", () => {
		const memberNo = button.getAttribute('data-member-no');
		
		window.location.href = `/adminBoard/updateMember?memberNo=${memberNo}`;
	})
});

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
