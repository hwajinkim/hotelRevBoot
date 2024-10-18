$(function(){
    $("#btn_idCheck").click(function(){
        fn_idCheck();
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
            }else{
                $('#result_idCheck').text('이미 가입된 사용자입니다.');
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
    if(frm.country.value == ""){
        alert("국가를 입력해주세요.");
        frm.country.focus();
        return false;
    }
    if(frm.eName.value == ""){
        alert("영문 이름을 입력해주세요.");
        frm.eName.focus();
        return false;
    }
    if(frm.kName.value == ""){
        alert("국문 이름을 입력해주세요.");
        frm.kName.focus();
        return false;
    }
    if(frm.birth.value == ""){
        alert("생년월일을 입력해주세요.");
        frm.birth.focus();
        return false;
    }
    if(frm.phone01.value == ""){
        alert("전화번호 첫째 자리를 입력해주세요.");
        frm.phone01.focus();
        return false;
    }
    if(frm.phone02.value == ""){
        alert("전화번호 둘째 자리를 입력해주세요.");
        frm.phone02.focus();
        return false;
    }
    if(frm.phone03.value == ""){
        alert("전화번호 셋째 자리를 입력해주세요.");
        frm.phone03.focus();
        return false;
    }
    if(frm.email01.value == ""){
        alert("이메일 첫째 자리를 입력해주세요.");
        frm.email01.focus();
        return false;
    }
    if(frm.email02.value == ""){
        alert("이메일 둘째 자리를 입력해주세요.");
        frm.email02.focus();
        return false;
    }

    frm.phone.value = frm.phone01.value + '-' + frm.phone02.value + '-' + frm.phone03.value;
    frm.email.value = frm.email01.value + '@' + frm.email02.value;

    var formData = new FormData(document.forms['joinForm']);
    console.log(formData);

    $.ajax({
        url: '/common/join',
        type: 'POST',
        data: formData,
        contentType: false,
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