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
        data: params,
        dataType : 'JSON',
        success: function(res){

            console.log("res:"+res);
            console.log("res.result:"+res.result);
        },
        error: function (request, status, error) {
            console.log("code: " + request.status);
            console.log("message: " + request.responseText);
            console.log("error: " + error);
        }
    });
}