$(function(){
    const token = localStorage.getItem('token');

        if(token){
            $('.not-login').css('display', 'none');

            const payload = JSON.parse(atob(token.split('.')[1]));
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