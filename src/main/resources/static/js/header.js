$(function(){
    const token = localStorage.getItem('token');
    //accessToken 만료체크
    if(token){
        chkExpiredAccess(token);
    }
    //refreshToken 만료체크
    chkExpiredRefresh();

        if(token){
            $('.not-login').css('display', 'none');

            const payload = JSON.parse(atob(token.split('.')[1]));
            console.log(payload);

            const userAuth = payload.auth;

            if(userAuth == 'ROLE_ADMIN'){
                $('#user-menu').css('display', 'none');
            }else if(userAuth == 'ROLE_USER'){
                $('#admin-menu-1').css('display', 'none');
                $('#admin-menu-2').css('display', 'none');
            }
        }else{
            $('.do-login').css('display', 'none');
            $('#user-menu').css('display', 'none');
            $('#admin-menu-1').css('display', 'none');
            $('#admin-menu-2').css('display', 'none');
            console.log("토큰이 없습니다.");
        }
});

//accessToken 만료 체크
function chkExpiredAccess(token){
    return $.ajax({
        url : '/api/auth/accessChk',
        type: 'GET',
        headers: {
            'Authorization': 'Bearer '+token
        },
        success: function (response){
            if(response === 'AccessToken expired'){
                console.log(response);
                reissueAccessToken();
            }
        },
        error: function(xhr, status, error) {
            console.error("error : " + error);
        }
    });
}

//accessToken 만료 시 refreshToken을 이용해 재발급
function reissueAccessToken(){
    return $.ajax({
        url : '/api/auth/reissue',
        type: 'GET',
        success: function (response){
            accessToken = response;
            localStorage.removeItem('token');
            localStorage.setItem('token', accessToken);
        },
        error: function(xhr, status, error) {
            console.error("error : " + error);
        }
    });
}

function chkExpiredRefresh(){
    return $.ajax({
        url : '/api/auth/refreshChk',
        type: 'GET',
        success: function (response){
            if(response === 'Refresh token is expired'){
                localStorage.removeItem('token');
                alert("인증이 만료되었습니다. 다시 로그인하세요.");
                location.href="/common/loginForm";
            }
        },
        error: function(xhr, status, error) {
            console.error("error : " + error);
        }
    });
}