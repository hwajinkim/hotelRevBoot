/*
function login(){
	var frm = document.loginForm;

	if(frm.username.value == ""){
		alert("아이디를 입력하세요.");
		return false;
	}

	if(frm.password.value == ""){
		alert("비밀번호를 입력하세요.");
		return false;
	}
	frm.submit();
}*/
function login(){
    let token = '';

    const username = $('#username').val();
    const password = $('#password').val();

    $.ajax({
        url: '/api/auth/login',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            username: username,
            password: password
        }),
        success: function(response){
            token = response.accessToken;
            alert('로그인에 성공하였습니다.');
            //서버로부터 JWT 토큰을 받으면 로컬 스토리지에 저장
            localStorage.setItem('token', token);
            console.log('JWT 토큰이 로컬 스토리지에 저장되었습니다.');
            location.href="/main";
            // 로그인 성공 후 즉시 보호된 리소스 요청
           //requestTest(token);
        },
        error: function(){
            alert('로그인에 실패하였습니다.');
        }
    });
}

function requestTest(token){
    $.ajax({
        url: '/api/auth/authorization',
        type: 'GET',
        headers: {
            'Authorization': 'Bearer '+token
        },
        success: function(response){
            if(response == 'success'){
                response = '인가에 성공하였습니다.';
            }
            alert(response);
            location.href="/main";
        },
        error: function(){
            alert("인가에 실패하였습니다.");
            localStorage.removeItem('token');
        }
    });
}

function logout(){
    localStorage.removeItem('token');
    console.log('JWT 토큰이 삭제되었습니다.');
    alert('로그아웃 되었습니다.');
    location.href="/main";
}
