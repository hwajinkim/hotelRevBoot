$(document).ready(function(){
    const token = localStorage.getItem("token");
    if(token == null){
        alert("접근 권한이 없습니다.");
        window.location.href="/common/loginForm";
        return false;
    }

    $.ajax({
        url: '/api/admin/roomList',
        type: 'GET',
        headers: {
            'Authorization': 'Bearer '+token
        },
        dataType: "json",
        contentType: "application/json",
        success: function(response){

            var roomList = response.list.content;
            var pageInfo = response.list;

            showRoomList(roomList);
            showPageInfo(pageInfo);
        },
        error: function(error){
            console.error("API 요청에 실패했습니다." + error);
        }
    });
});

function showRoomList(roomList){
    var tbody = $("tbody");
    tbody.empty();

    roomList.forEach(function(room) {
        var row =
        "<tr>" +
            "<td>" + room.id + "</td>" +
            "<td>";
        if (room.roomThumbnailPath) {
            row += "<a href='/admin/roomView?Id=" + room.id + "'>" +
                   "<img style='width: 150px; height: 100px;' src='" + room.roomThumbnailPath + "'/>" +
                   "</a>";
        }
        row += "</td>" +
               "<td><a href='/admin/roomView?Id=" + room.id + "'>" + room.roomName + "</a></td>" +
               "<td>" + room.createdDate.substring(0, 10) + "</td>" +
       "</tr>";

        tbody.append(row);
    });
}

function showPageInfo(pageInfo){
    var pagination = $("#pagination");
    pagination.empty();

    // 이전 페이지 버튼
    if (pageInfo.first) {
        pagination.append('<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>');
    } else {
        pagination.append('<li class="page-item"><a class="page-link" href="#" onclick="loadPage(' + (pageInfo.number - 1) + ')">Previous</a></li>');
    }

    // 페이지 번호
    for (var i = 0; i < pageInfo.totalPages; i++) {
        if (i === pageInfo.number) {
            pagination.append('<li class="page-item active"><a class="page-link" href="#">' + (i + 1) + '</a></li>');
        } else {
            pagination.append('<li class="page-item"><a class="page-link" href="#" onclick="loadPage(' + i + ')">' + (i + 1) + '</a></li>');
        }
    }

    // 다음 페이지 버튼
    if (pageInfo.last) {
        pagination.append('<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>');
    } else {
        pagination.append('<li class="page-item"><a class="page-link" href="#" onclick="loadPage(' + (pageInfo.number + 1) + ')">Next</a></li>');
    }
}

function loadPage(pageNumber) {
    const token = localStorage.getItem("token");

    $.ajax({
        url: '/api/admin/roomList',
        type: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        },
        data: {
            page: pageNumber  // 페이지 번호를 서버에 전달
        },
        dataType: "json",
        contentType: "application/json",
        success: function(response) {
            var roomList = response.list.content;
            var pageInfo = response.list;

            console.log("roomList",roomList);
            console.log("pageInfo",pageInfo);

            // 새로운 방 목록 및 페이지네이션 렌더링
            showRoomList(roomList);
            showPageInfo(pageInfo);
        },
        error: function(error) {
            console.error("API 요청에 실패했습니다: " + error);
        }
    });
}
