function update(){
    event.preventDefault();

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

    const roomId = $('#roomId').text();
    const token = localStorage.getItem("token");

    var formData = new FormData(document.forms['updateForm']);

    $.ajax({
        url: '/api/admin/roomUpdate/'+roomId,
        type: 'POST',
        headers: {
            'Authorization': 'Bearer '+token
        },
        data: formData,
        contentType: false,
        processData: false,
        success: function(response) {
            console.log(response);
            alert('방 수정이 완료되었습니다.');
            window.location.href = '/admin/roomList';
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('API 요청에 실패했습니다.: ' + textStatus);
        }
    });
}