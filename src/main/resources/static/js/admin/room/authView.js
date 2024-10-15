$(document).ready(function(){
    const token = localStorage.getItem("token");

    const roomId = $('#roomId').text();

    $.ajax({
        url: '/api/admin/roomView/'+roomId,
        type: 'GET',
        headers: {
            'Authorization': 'Bearer '+token
        },
        dataType: "json",
        contentType: "application/json",
        success: function(response){

            var room = response.data;
            showRoomView(room);
            showRoomViewBtn(room);
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

function showRoomViewBtn(room){
    const roomBtn =
        "<div style='margin: 10px;'>" +
            "<button type='button' class='btn btn-secondary' onclick='location.href=\"/admin/roomList\";'>목록</button>" +
            "<button type='button' class='btn btn-warning' onclick='location.href=\"/admin/roomUpdatePage?Id=" + room.id + "\";'>수정</button>" +
            "<button type='button' class='btn btn-danger' onclick='deleteRoom(" + room.id + ");'>삭제</button>" +
        "</div>";
    $('#roomViewBtn').append(roomBtn);
}

