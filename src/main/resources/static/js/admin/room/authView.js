$(document).ready(function(){
    const token = localStorage.getItem("token");
    console.log(token);

    var roomId = $('#roomId').text();
    console.log(roomId);

    $.ajax({
        url: '/api/admin/roomView/'+roomId,
        type: 'GET',
        headers: {
            'Authorization': 'Bearer '+token
        },
        dataType: "json",
        contentType: "application/json",
        success: function(response){

            var room = response;
            showRoomView(room);
        },
        error: function(error){
            console.error("API 요청에 실패했습니다." + error);
        }
    });
});

function showRoomView(room){
    $('#roomName').text(room.roomName);       // 객실명
    $('#roomPrice').text(room.roomPrice + " 원"); // 객실 요금
    $('#roomAmount').text(room.roomAmount); // 객실수
    $('#roomSize').text(room.roomSize + " ㎡");   // 객실 크기
    $('#peopleNum').text(room.peopleNum + " 명"); // 최대 인원수
    $('#bedType').text(room.bedType);         // 침대 타입
    $('#roomSpec').text(room.roomSpec);
}

