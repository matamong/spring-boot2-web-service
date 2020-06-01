
$(document).ready(function () {
    $('.negative').hide();
});
$(".close.icon").click(function () {
    $(this).parent().hide();
});



$('#certificate').click(function () {
    var nickname = $('#nickname').val();

    if (checkNicknameValidation(nickname) === true) {
        checkNicknameDuplication(nickname);
    }else{
        $("#nicknameValidationError").fadeIn(2000);
        $('#nickname').focus();
    }
});

function checkNicknameDuplication(nickname) {
    var encodedNickname = encodeURI(nickname);
    var inputValue = $('input[name="policyCheck"]:checked').val();
    if(findUncheckedInput(inputValue) === false){
        $('#policy').attr('class','ui red message');
    }else{
        $.ajax({
            type: 'GET',
            url: url + "api/user/nicknames/" + encodedNickname,
            error: function (err) {
                $("#nicknameDuplicationError").fadeIn(2000);
                $('#nickname').focus();
            },
            success: function () {
                var encodedNickname = encodeURI(nickname);

                var jsonData = JSON.stringify({
                    nickname: $('#nickname').val()
                });
                $.ajax({
                    url: url + "api/referralCode/" + $('#referralCode').val(),
                    type: "PUT",
                    data: jsonData,
                    contentType: "application/json",
                    dataType: "json",
                    success: function () {
                        alert('축하합니다. 인증에 성공하였습니다!');
                        location.href = '/myPage';
                    },
                    error: function () {
                        alert('인증에 실패하였습니다.');
                    }
                });
            }
        });
    }
}
function checkNicknameValidation(nickname) {
    var nickLength = 0;
    var engCheck = /[a-z]/;
    var numCheck = /[0-9]/;
    var specialCheck = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

    for (var i = 0; i < nickname.length; i++) {
        //한글은 2, 영문은 1로 치환
        nick = nickname.charAt(i);
        if (escape(nick).length > 4) {
            nickLength += 2;
        } else { nickLength += 1; }
    }

    if (nickname === null || nickname === "") {
        return false;
    } else if (nickname.search(/\s/) != -1) {
        //닉네임 빈칸 포함 안됨
        return false;
    } else if (nickLength < 2 || nickLength > 20) {
        return false;
    } else if (specialCheck.test(nickname)) {
        //닉네임 특수문자 포함 안됨
        return false;
    }else{
        return true;
    }
}

function findUncheckedInput(inputValue) {
    if (inputValue === undefined) {
        return false;
    }else{
        return true;
    }
}
