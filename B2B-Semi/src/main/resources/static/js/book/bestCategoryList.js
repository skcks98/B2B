// URL에서 category 파라미터 값 가져오기
const params = new URLSearchParams(window.location.search);
const selectedCategory = params.get("category"); // 선택된 카테고리 값

// 버튼의 해당 장르 도서목록 조회
const categoryButton = document.querySelectorAll(".categoryButton");
const bookList = document.querySelector("#bookList");

// 버튼 배경색 설정
categoryButton.forEach(button => {
    if (button.getAttribute("value") === selectedCategory) {
        button.style.backgroundColor = "#DDD6FE"; // 선택된 버튼 강조
    } else {
        button.style.backgroundColor = "#f3f4f6"; // 다른 버튼은 초기화
    }
});

// 선택된 요소들에 대해 반복 처리
categoryButton.forEach(e => {
	// 각 e에 클릭 이벤트 리스너 추가
	e.addEventListener("click", cate => {
		
		// 모든 버튼의 배경색 초기화
        categoryButton.forEach(btn => {
            btn.style.backgroundColor = "#f3f4f6"; // 기본 배경색으로 초기화
        });

        // 클릭된 버튼만 배경색 변경
        cate.target.style.backgroundColor = "#DDD6FE";
		
		// 해당 버튼의 value 값 가져오기
		const category = cate.target.value;
		
		// 비동기 요청
		// 댓글 목록 비동기 get 방식 조회
		fetch(`/book/selectCategortBestBook?category=${category}`, {
			method: "GET",
			headers: { "Content-Type": "application/json" }
		})
		.then(resp => resp.json())
		.then(result => {
			// 기존값 비워두기
			bookList.innerHTML = '';
			
			// 조회되는 도서가 있을때
			if (result.length > 0) {
				// 순번 변수
				let i = 1;
				
				// book 개수만큼 반복 처리
				result.forEach(book => {
					// div 생성
					const bookCard = document.createElement('div');
					bookCard.className = 'book-card';
					
					// HTML 생성
					bookCard.innerHTML = `
			            <div class="rank-badge">${i}</div>
			            <img src="${book.coverUrl}" alt="${book.title}" class="book-cover">
			            <div class="book-info"
							data-bookId="${book.bookId}" data-title="${book.fullTitle}"
							data-coverUrl="${book.coverUrl}" data-author="${book.author}"
							data-rating="${book.customerReviewRank}" data-genres="${book.genres}"
							data-description="${book.description}" data-reviewCount="${book.reviewCount}">
			                <h3 class="book-title">${book.title}
			                </h3>
			                <p class="book-author">${book.author}</p>
			                <div class="book-stats">
			                    <div class="stat-item">
			                        <span class="stat-value">${book.customerReviewRank}</span>
			                        <span class="stat-label">평점</span>
			                    </div>
			                    <div class="stat-item">
			                        <span class="stat-value">${book.reviewCount}</span>
			                        <span class="stat-label">리뷰</span>
			                    </div>
			                </div>
			            </div>
			        `;
					
					bookList.appendChild(bookCard);
					i++;
				});

			}

		});
	});
	
});


bookList.addEventListener("click", (event) => {
    // 클릭한 요소가 `book-info` 클래스를 가진 경우
    if (event.target.closest(".book-info")) {
        const row = event.target.closest(".book-info");

        const modal = new bootstrap.Modal(document.getElementById('bookDetailModal'));

        // data-bookId 같은 속성 데이터 읽어오기
        const bookId = row.getAttribute('data-bookId');
        const bookTitle = row.getAttribute('data-title');
        const bookCover = row.getAttribute('data-coverUrl');
        const bookAuthor = row.getAttribute('data-author');
        const bookRating = row.getAttribute('data-rating');
        const bookGenres = row.getAttribute('data-genres');
        const bookDescription = row.getAttribute('data-description');
        const reviewCount = row.getAttribute('data-reviewCount');

        // 모달 내용 업데이트
        document.querySelector('.book-detail-title').textContent = bookTitle;
        document.querySelector('.book-detail-cover').src = bookCover;
        document.querySelector('.book-detail-author').textContent = bookAuthor;
        document.querySelector('.book-detail-stats .stat-item:first-child span').textContent = bookRating;
        document.querySelector('.book-detail-stats .stat-item:nth-child(2) span').textContent = reviewCount;
        document.querySelector('.avgScore').textContent = "평균 " + bookRating + " : 10.0";
        document.querySelector('.reviewCount').textContent = "총 " + reviewCount + "개의 리뷰";

        document.querySelector('#selectBookId').value = bookId;

        // 장르 업데이트
        const genreContainer = document.querySelector('.book-detail-genres');
        genreContainer.innerHTML = '';
        bookGenres.split(',').forEach(genre => {
            const genreBadge = document.createElement('span');
            genreBadge.className = 'badge bg-primary me-2';
            genreBadge.textContent = genre;
            genreContainer.appendChild(genreBadge);
        });

        document.querySelector('.book-synopsis-text').textContent = bookDescription;

        // 모달 보여주기
        modal.show();

        // 댓글 조회
        selectReviewList(bookId);
    }
});

function selectReviewList(bookId) {

	// 기존 html 비우기
	document.querySelector(".review-list").innerHTML = '';

	// 댓글 목록 비동기 get 방식 조회
	fetch(`/book/selectReviewList?bookId=${bookId}`, {
		method: "GET",
		headers: { "Content-Type": "application/json" }
	})
	.then(resp => resp.json())
	.then(result => {

		// 조회되는 댓글 목록이 있을때
		if (result.length > 0) {
			result.forEach(({ MEMBER_NICKNAME, COMMENT, STAR_POINT, WRITE_DATE, PROFILE_IMG }) => {
				// 별점은 따로 불러오기
				const starHTML = getStarHTML(STAR_POINT);

				const reviewHTML = `
			<div class="review-item">
				<div class="review-header d-flex justify-content-between align-items-center mb-2">
					<div class="reviewer-info d-flex align-items-center">
						<img src="${PROFILE_IMG}" alt="User" class="reviewer-avatar">
						<div class="ms-2">
							<div class="reviewer-name">${MEMBER_NICKNAME}</div>
							<div class="review-date text-muted">${WRITE_DATE}</div>
						</div>
					</div>
					<div class="review-actions">
						<div class="review-rating mb-1">
							${starHTML}
						</div>
					</div>
				</div>
				<p class="review-text mb-2">${COMMENT}</p>
			</div>
            `;

				// html 안에 대입
				document.querySelector(".review-list").innerHTML += reviewHTML;
			});

		} else {

			// 댓글이 없을때
			const reviewHTML = `
		<div class="review-item">
			<p class="review-text mb-2">작성된 리뷰가 없습니다.</p>
		</div>
        `;

			document.querySelector(".review-list").innerHTML += reviewHTML;
		}

	});

}

// 별개수 생성 html
function getStarHTML(starPoint) {
	let starPointHTML = '';

	// 별1개 짜리 생성
	for (let i = 1; i <= starPoint / 2; i++) {
		starPointHTML += `<i class="fas fa-star text-warning"></i>`;
	}

	// 별 반개짜리 생성
	if (starPoint % 2 != 0) {
		starPointHTML += `<i class="fas fa-star-half-alt text-warning"></i>`;
	}

	// 총 점수
	starPointHTML += `&nbsp;<span>${starPoint}점</span>`;

	return starPointHTML;
}



// 별점 선택 기능
const ratingSelect = document.querySelector('.rating-select');
const stars = document.querySelectorAll('.star-rating');
const selectedRating = document.querySelector('.selected-rating');
let currentRating = 0;

// 별점 선택시
function updateStars(rating) {
	const starRating = rating / 2; // 10점 만점을 5점 만점으로 변환
	stars.forEach(star => {
		// 기존의 클래스 삭제
		star.classList.remove('full', 'half');
		const thisStarRating = parseFloat(star.dataset.rating);

		// 별점 선택한 부분의 크기값 비교
		if (thisStarRating <= Math.floor(starRating)) {
			star.classList.add('full');
		} else if (thisStarRating === Math.ceil(starRating) && starRating % 1 !== 0) {
			star.classList.add('half');
		}
	});
}


if (ratingSelect != null) {

	// 마우스 올리게되면 표시될 별점 디자인
	ratingSelect.addEventListener('mousemove', (e) => {
		const star = e.target;
		if (!star.classList.contains('star-rating')) return;

		// 별의 위치 정보를 가져옴 (좌측 상단의 좌표와 너비, 높이)
		const rect = star.getBoundingClientRect();

		// 마우스 위치가 별의 왼쪽 절반에 있는지 확인
		const isLeftHalf = e.clientX - rect.left < rect.width / 2;

		// 별의 데이터 속성에서 별점 값 가져오기
		const rating = parseFloat(star.dataset.rating);

		// 마우스 위치에 따른 별점 표시 값 계산
		// 마우스가 왼쪽 절반에 있으면 반 개 별로 계산
		const displayRating = isLeftHalf ? (rating * 2 - 1) : rating * 2;

		// 모든 별에 대해 상태를 업데이트 (전체, 반개, 비어있는 상태)
		stars.forEach(s => {
			const sRating = parseFloat(s.dataset.rating);
			s.classList.remove('full', 'half');

			if (sRating < rating) {
				s.classList.add('full');
			} else if (sRating === rating) {
				if (isLeftHalf) {
					s.classList.add('half');
				} else {
					s.classList.add('full');
				}
			}
		});

		// 선택된 별점 표시
		selectedRating.textContent = displayRating;
	});

	// 클릭시 별점 저장
	ratingSelect.addEventListener('click', (e) => {
		const star = e.target;
		if (!star.classList.contains('star-rating')) return;

		const rect = star.getBoundingClientRect();
		const isLeftHalf = e.clientX - rect.left < rect.width / 2;
		const rating = parseFloat(star.dataset.rating);

		currentRating = isLeftHalf ? (rating * 2 - 1) : rating * 2;
		selectedRating.textContent = currentRating;
		updateStars(currentRating);
	});

	// 마우스가 떠났을때
	ratingSelect.addEventListener('mouseleave', () => {
		updateStars(currentRating);
		selectedRating.textContent = currentRating;
	});

}


const submitReview = document.getElementById('submitReview');

// 리뷰 제출 버튼 클릭 이벤트
if (submitReview != null) {
	submitReview.addEventListener('click', function() {

		// 로그인한 멤버 번호 가져오기
		const memberNo = loginMember.memberNo;

		const reviewContent = document.getElementById('reviewContent').value;
		const bookId = document.querySelector('#selectBookId').value;

		if (currentRating === 0) {
			alert('별점을 선택해주세요.');
			return;
		}

		if (!reviewContent.trim()) {
			alert('리뷰 내용을 입력해주세요.');
			return;
		}


		// 보낼 객체 선언
		const obj = {
			"bookId": bookId,
			"starPoint": currentRating,
			"comment": reviewContent,
			"memberNo": memberNo
		}

		// 리뷰작성 진행
		fetch("/book/insertBookReview", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(obj) // obj JS 객체를 JSON 으로 변경
		})
		.then(resp => resp.text())
		.then(result => {

			if (result > 0) {
				alert("리뷰 댓글이 등록되었습니다.");

				// 모달 데이터 갱신 및 다시 표시
				selectReviewList(bookId); // 댓글 목록 다시 불러오기
				
				// Bootstrap Modal 재활성화
				const modal = bootstrap.Modal.getInstance(document.getElementById('bookDetailModal'));
				if (modal) {
					modal.show(); // 모달 다시 표시
				}

			} else {
				alert("이미 리뷰를 작성하셨습니다.");
			}

		});

		// 폼 초기화
		currentRating = 0;
		updateStars(0);
		selectedRating.textContent = '0.0';
		document.getElementById('reviewContent').value = '';

	});
}