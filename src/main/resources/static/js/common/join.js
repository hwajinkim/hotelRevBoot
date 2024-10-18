
var duplicateCheck = false;

$(function(){
    $("#btn_idCheck").click(function(){
        fn_idCheck();
    });

    $("#email02").change(function() {
        var selectVal = $(this).val();
        console.log(selectVal);
        if (selectVal === "") {
             $("#customEmail").show();
        } else {
             $("#customEmail").hide();
        }
    });


});

function fn_idCheck(){

    var frm = document.joinForm;

    if(frm.username.value==""){
        alert("사용자명을 입력하세요.");
        return;
    }

    var params = {'username' : frm.username.value };

    $.ajax({
        type : 'post',
        url : '/common/memberExists',
        data: JSON.stringify(params),
        dataType : 'JSON',
        contentType: "application/json",
        success: function(res){
        console.log(res.data);
            if(res.data == 'true'){
                $('#result_idCheck').text('사용 가능한 사용자명입니다.');
                duplicateCheck = true;
            }else{
                $('#result_idCheck').text('이미 가입된 사용자입니다.');
                duplicateCheck = false;
            }
        },
        error: function (request, status, error) {
            console.log("code: " + request.status);
            console.log("message: " + request.responseText);
            console.log("error: " + error);
        }
    });
}

function join(){
    event.preventDefault();

    var frm = document.joinForm;

    if(frm.username.value == ""){
        alert("사용자명을 입력하세요.");
        frm.username.focus();
        return false;
    }
    if(frm.password.value == ""){
        alert("비밀번호를 입력하세요.");
        frm.password.focus();
        return false;
    }
    if(frm.password_confirm.value == ""){
        alert("비밀번호 확인을 입력해주세요.");
        frm.password_confirm.focus();
        return false;
    }else{
        if(frm.password.value!=frm.password_confirm.value){
            alert("비밀번호를 다시 확인해주세요.");
            return false;
        }
    }

    if(duplicateCheck == false){
        alert("중복확인을 해주세요");
        frm.username.focus();
        return false;
    }

    frm.phone.value = frm.phone01.value + '-' + frm.phone02.value + '-' + frm.phone03.value;
    frm.email.value = frm.email01.value + '@' + frm.email02.value;

    const username = $('#username').val();
    const password = $('#password').val();
    const roles = $('#roles').val();
    const eName = $('#eName').val();
    const birth = $('#birth').val();
    const phone = $('#phone').val();
    const email = $('#email').val();

    const data = {
        "username" : username,
        "password" : password,
        "roles" : [roles],
        "eName" : eName,
        "birth" : birth,
        "phone" : phone,
        "email" : email
    }

    $.ajax({
        url: '/common/join',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json',
        processData: false,
        success: function(response) {
            alert('회원가입이 완료되었습니다.');
            window.location.href = '/main';
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('API 요청에 실패했습니다.: ' + textStatus);
        }
    });



}