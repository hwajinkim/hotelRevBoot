function login(){
	var frm = document.loginForm;

	if(frm.mId.value == ""){
		alert("아이디를 입력하세요.");
		return false;
	}

	if(frm.mPw.value == ""){
		alert("비밀번호를 입력하세요.");
		return false;
	}
	frm.submit();
}