$(document).ready(function(){
$('#delete').click(function () {
    $.ajax({
        async: false,
        url: url + "api/user/delete",
        type: "DELETE",
        success: function (result) {
            alert("탈퇴하였습니다. 로그아웃해주세요.");
            document.getElementById('logout').submit();
        },
        error: function (data) {
            alert("탈퇴에 실패하였습니다. 개발자 메일로 연락주세요.")
        }
    });

});
});