$(function(){
    const token = localStorage.getItem('token');

        if(token){
            $('.not-login').css('display', 'none');

            const payload = JSON.parse(atob(token.split('.')[1]));
            console.log(payload);
            const exp = payload.exp * 1000;

            if(Date.now() >= exp){
                alert("세션이 만료되었습니다. 다시 로그인 해주세요.");
                localStorage.removeItem('token');
                window.location.href="/common/loginForm";
            }

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