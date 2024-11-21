// 이메일 인증 폼 서브밋 후 회원가입 폼으로 교체
document.getElementById('email-form').addEventListener('submit', function(e) {
    e.preventDefault();
    document.getElementById('email-form').style.display = 'none';
    document.getElementById('signUpForm').style.display = 'block';
});

// 취소버튼 눌렀을때 로그인 페이지로 돌아가기
document.querySelectorAll('#cancelButton').forEach(button => {
        button.addEventListener('click', function() {
        if(confirm("회원가입을 취소하고 로그인 페이지로 이동 하시겠습니까?")) {
            window.location.href = '/member/login';
    } 
  });
});





