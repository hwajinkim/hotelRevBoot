function insert(){
    event.preventDefault();

    const token = localStorage.getItem("token");

    var frm = document.insertForm;

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

    var formData = new FormData(document.forms['insertForm']);

    $.ajax({
        url: '/api/admin/roomInsert',
        type: 'POST',
        headers: {
            'Authorization': 'Bearer '+token
        },
        data: formData,
        contentType: false,
        processData: false,
        success: function(response) {
            alert('방 등록이 완료되었습니다.');
            window.location.href = '/admin/roomList';
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('API 요청에 실패했습니다.: ' + textStatus);
        }
    });
}