function update(){
	var frm = document.updateForm;

	if(frm.roomName.value == ""){
		alert("객실명을 입력하세요.");
		return false;
	}

	if(frm.roomPrice.value == ""){
        alert("객실요금을 입력하세요.");
        return false;
    }

    if(frm.roomAmount.value == ""){
        alert("객실수를 입력하세요.");
        return false;
    }

    if(frm.roomSize.value == ""){
        alert("객실크기를 입력하세요.");
        return false;
    }

    if(frm.peopleNum.value == ""){
        alert("최대인원수를 입력하세요.");
        return false;
    }

    if(frm.bedType.value == ""){
        alert("침대타입을 입력하세요.");
        return false;
    }

	frm.submit();
}