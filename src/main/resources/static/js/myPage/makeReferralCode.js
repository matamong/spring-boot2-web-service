
$('#insert').click(function () {
    $.ajax({
        url: url + "api/referralCode",
        type: "POST",
        success: function () {
            alert('인증 코드를 생성하였습니다.');
            location.href = '/myPage/referralCode';
        },
        error: function () {
            alert('인증 코드 생성에 실패하였습니다.');
        }
    });
});