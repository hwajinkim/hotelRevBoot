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
            accessToken = response;
            alert('로그인에 성공하였습니다.');
            //서버로부터 JWT 토큰을 받으면 로컬 스토리지에 저장
            localStorage.setItem('token', accessToken);
            console.log('JWT 토큰이 로컬 스토리지에 저장되었습니다.');
            location.href="/main";
            // 로그인 성공 후 즉시 보호된 리소스 요청
           //requestTest(token);
        },
        error: function(xhr, status, error) {
            console.error('로그인에 실패하였습니다.:', error);
        }
    });
}

//로그아웃 시
//1. localStorage의 accessToken 제거
//2. cookie의 refreshToken 제거
//3. DB의 refreshToken 제거
function logout(){
    localStorage.removeItem('token');
    $.ajax({
        url: '/api/auth/logout',
        type: 'GET',
        success: function(response){
            if(response === 'Logout completed'){
                console.log('JWT 토큰이 삭제되었습니다.');
                alert('로그아웃 되었습니다.');
                location.href="/main";
            }
        },
        error: function(xhr, status, error) {
            console.error('로그아웃에 실패하였습니다.:', error);
        }
    });
}
