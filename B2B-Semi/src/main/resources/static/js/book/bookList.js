function showTooltip(event, element) {
	// 가상 요소(::after)는 직접 조작할 수 없으므로, 
	// 실제 툴팁 요소를 동적으로 생성하고 관리합니다
	let tooltip = document.getElementById('dynamic-tooltip');

	if (!tooltip) {
		tooltip = document.createElement('div');
		tooltip.id = 'dynamic-tooltip';
		tooltip.style.position = 'fixed';
		tooltip.style.backgroundColor = 'white';
		tooltip.style.padding = '5px';
		tooltip.style.border = '1px solid #ccc';
		tooltip.style.borderRadius = '4px';
		tooltip.style.boxShadow = '0 2px 5px rgba(0,0,0,0.2)';
		tooltip.style.zIndex = '1000';
		tooltip.style.display = 'none';
		document.body.appendChild(tooltip);
	}

	// 툴팁 내용 업데이트
	tooltip.textContent = element.getAttribute('data-full-text');
	tooltip.style.display = 'block';

	// 마우스 커서 위치에 따라 툴팁 위치 조정
	const offset = 5; // 커서로부터의 거리
	tooltip.style.left = (event.pageX + offset) + 'px';
	tooltip.style.top = (event.clientY - offset * 14) + 'px';

	// 요소에서 마우스가 벗어났을 때 툴팁 숨기기
	element.onmouseleave = function() {
		tooltip.style.display = 'none';
	};
}

// Submenu toggle
const submenuLinks = document.querySelectorAll('.has-submenu');

submenuLinks.forEach(link => {
	link.addEventListener('click', (e) => {
		e.preventDefault();
		const submenu = link.nextElementSibling;
		submenu.classList.toggle('active');
		const icon = link.querySelector('.fa-chevron-down');
		icon.style.transform = submenu.classList.contains('active') ? 'rotate(180deg)' : 'rotate(0)';
	});
});

// Ranking tabs
const tabBtns = document.querySelectorAll('.tab-btn');

tabBtns.forEach(btn => {
	btn.addEventListener('click', () => {
		tabBtns.forEach(b => b.classList.remove('active'));
		btn.classList.add('active');
	});
});

const bookCards = document.querySelectorAll('.book-card');
bookCards.forEach(card => {
	card.addEventListener('click', () => {
		const modal = new bootstrap.Modal(document.getElementById('bookDetailModal'));
		modal.show();
	});
});

// 별점 선택 기능
const stars = document.querySelectorAll('.star-rating');
const selectedRating = document.querySelector('.selected-rating');
let currentRating = 0;

stars.forEach(star => {
	star.addEventListener('mouseover', function() {
		const rating = this.dataset.rating;
		updateStars(rating);
	});

	star.addEventListener('mouseout', function() {
		updateStars(currentRating);
	});

	star.addEventListener('click', function() {
		currentRating = this.dataset.rating;
		selectedRating.textContent = `${currentRating}.0`;
		updateStars(currentRating);
	});
});

function updateStars(rating) {
	stars.forEach(star => {
		const starRating = star.dataset.rating;
		if (starRating <= rating) {
			star.classList.add('active');
		} else {
			star.classList.remove('active');
		}
	});
}

// 리뷰 제출 버튼 클릭 이벤트
document.getElementById('submitReview').addEventListener('click', function() {
	const reviewContent = document.getElementById('reviewContent').value;
	const spoilerChecked = document.getElementById('spoilerCheck').checked;

	if (currentRating === 0) {
		alert('별점을 선택해주세요.');
		return;
	}

	if (!reviewContent.trim()) {
		alert('리뷰 내용을 입력해주세요.');
		return;
	}

	// 여기에 리뷰 제출 로직 추가
	console.log({
		rating: currentRating,
		content: reviewContent,
		hasSpoiler: spoilerChecked
	});

	// 폼 초기화
	currentRating = 0;
	updateStars(0);
	selectedRating.textContent = '0.0';
	document.getElementById('reviewContent').value = '';
	document.getElementById('spoilerCheck').checked = false;
});