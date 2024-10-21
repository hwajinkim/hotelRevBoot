$(function(){
    const token = localStorage.getItem('token');

    $.ajax({
        url: '/api/memberInfo',
        type: 'GET',
        headers: {
            'Authorization': 'Bearer '+token
        },
        success: function(response) {
            showMemberInfo(response);
        },
        error: function(xhr, status, error) {
            console.error('회원정보를 불러올 수 없습니다.:', error);
        }
    });
});

function showMemberInfo(res){
    console.log('Member Info:', res.username);

    $('#username').text(res.username);
    $('#email').text(res.email);
    $('#phone').text(res.phone);
}