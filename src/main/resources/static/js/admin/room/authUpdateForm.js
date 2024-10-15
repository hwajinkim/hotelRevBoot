$(document).ready(function(){
    const token = localStorage.getItem("token");

    const roomId = $('#roomId').text();

    $.ajax({
        url: '/api/admin/roomUpdatePage/'+roomId,
        type: 'GET',
        headers: {
            'Authorization': 'Bearer '+token
        },
        dataType: "json",
        contentType: "application/json",
        success: function(response){

            var room = response.data;
            console.log(room);
            showRoomUpdatePage(room);
        },
        error: function(error){
            console.error("API 요청에 실패했습니다." + error);
        }
    });
});

function showRoomUpdatePage(room){
    $('#roomName').val(room.roomName);       // 객실명
    $('#roomPrice').val(room.roomPrice); // 객실 요금
    $('#roomAmount').val(room.roomAmount); // 객실수
    $('#roomSize').val(room.roomSize);   // 객실 크기
    $('#peopleNum').val(room.peopleNum); // 최대 인원수
    $('#bedType').val(room.bedType);         // 침대 타입
    $('#roomSpec').val(room.roomSpec);       // 특이사항
    $('#roomThumbnailPath').val(room.roomThumbnailPath); // 썸네일 이미지
}