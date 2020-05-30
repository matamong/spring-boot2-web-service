
$('#delete').on('click', function () {
    $.ajax({
        url: url + "api/user",
        type: "DELETE",
        success: function () {
            location.href="/";
        },
        error: function () {
            alert("탈퇴에 실패하였습니다. 개발자 메일로 연락주세요.")
        }
    });
});
