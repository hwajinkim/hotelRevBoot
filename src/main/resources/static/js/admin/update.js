function update(){
	var frm = document.updateForm;

	if(frm.roomName.value == ""){
		alert("객실명을 입력하세요.");
		return false;
	}

	if(frm.roomPrice.value == ""){
        alert("객실 요금을 입력하세요.");
        return false;
    }

    if(frm.roomAmount.value == ""){
        alert("객실 수를 입력하세요.");
        return false;
    }

    if(frm.roomSize.value == ""){
        alert("객실 크기를 입력하세요.");
        return false;
    }

    if(frm.peopleNum.value == ""){
        alert("최대 인원 수를 입력하세요.");
        return false;
    }

    if(frm.bedType.value == ""){
        alert("침대 타입을 입력하세요.");
        return false;
    }

	frm.submit();
}