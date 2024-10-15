function deleteRoom(roomId){
    const token = localStorage.getItem("token");
    if (!confirm("정말로 삭제하시겠습니까?")) {
        return; // 확인 창에서 취소를 누르면 삭제 작업을 중단
    }

    $.ajax({
        url: '/api/admin/roomDelete/'+roomId,
        type: 'DELETE',  // 또는 'DELETE' 메서드도 가능
        headers: {
            'Authorization': 'Bearer '+token
        },
        success: function(response) {
            alert('객실이 성공적으로 삭제되었습니다.');
            window.location.href = '/admin/roomList';
        },
        error: function(error) {
            alert('객실 삭제에 실패했습니다. 다시 시도해주세요.');
            console.log(error);
        }
    });
}