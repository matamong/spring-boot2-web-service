//서버에서 데이터만(json)만 받아서 직접 js로 만지고싶은데....
// 구조가 서버사이드 렌더링이라 강제로 model에 저장되어있는 데이터를 끄집어내겠다,,,
console.log("서버에서 데이터만(json)만 받아서 직접 js로 만지고싶은데....\n" +
    "     애초에 시작한 구조가 서버사이드 렌더링이라..\n" +
    "백엔드와 프론트엔드를 떼어낼 그 날을 위하여...ㅠㅠ");

var articleDateText = document.getElementById("article_date").innerText;

window.onload = function () {
    articleDateSpliter(articleDateText);
}
articleDateSpliter(articleDateText);

function articleDateSpliter(str) {
    var strArr = str.split('');
    var slicedStrArr = strArr.slice(0, 10);
    var text = slicedStrArr.join('');

    document.getElementById("article_date").innerHTML = text;
}

function pageBack() {
    history.back();
}

$('.edit').on('click', function () {
    var commentId = $(this).parent().parent().find('.metadata').find('input:hidden').val();
    var commentActionDiv = $(this).parent();
    var commentContentDiv = $(this).parent().prev();
    var commentContent = commentContentDiv.text();

    var form = createCommentEditForm(commentContent);
    $(commentContentDiv).after(form);
    commentContentDiv.hide();
    commentActionDiv.hide();
});

function createCommentEditForm(content) {
    var form = document.createElement('form');
    var fieldDiv = document.createElement('div');
    var textarea = document.createElement('textarea');
    var submitDiv = document.createElement('div');
    var cancleDiv = document.createElement('div');

    form.className = 'ui reply form';
    fieldDiv.className = 'field';
    textarea.value = content;
    submitDiv.className = 'ui green button commentUpdate';
    submitDiv.innerHTML = '수정'
    cancleDiv.className = 'ui button commentEditCancle';
    cancleDiv.innerHTML = '취소';

    cancleDiv.onclick = function (event) {
        var editForm = $(this).parent().parent();
        var commentForm = $(this).parent().parent().parent().find('.text');
        var commentActions = $(this).parent().parent().parent().find('.actions');
        commentForm.show();
        commentActions.show();
        editForm.remove();
    }

    submitDiv.onclick = function (event) {

        var textareaContent = $(this).parent().find('textarea').val();
        var commentId = $(this).parent().parent().prev().prev().find('.comment_id').val();

        var jsonData = JSON.stringify({
            commentId: commentId,
            content: textareaContent,
            boardIdx: $('#board_idx').val()
        });
        $.ajax({
            url: url + "api/comments/" + commentId,
            type: "PUT",
            data: jsonData,
            contentType: "application/json",
            dataType: "json",
            success: function (data, status) {
                location.reload();
            },
            error: function (status) {
                alert("댓글 작성에 실패하였습니다.")
            }
        });
    }

    fieldDiv.appendChild(textarea);
    fieldDiv.appendChild(submitDiv);
    fieldDiv.appendChild(cancleDiv);
    form.appendChild(fieldDiv);

    textarea.focus();
    return form;
}

$('.delete').on('click', function () {
    var commentId = $(this).parent().parent().find('.metadata').find('input:hidden').val();

    $('.mini.modal')
        .modal({
            allowMultiple: false,
            blurring: true,
            onApprove: function () {
                $.ajax({
                    url: url + "api/comments/" + commentId,
                    type: "DELETE",
                    success: function (data, status) {
                        location.reload();
                    },
                    error: function (status) {
                        alert("댓글 삭제에 실패하였습니다.")
                    }
                });
            }
        })
        .modal('show');
});

$('#like').click(function () {
    var jsonData = JSON.stringify({
        boardIdx: $('#board_idx').text()
    });
    $.ajax({
        url: url + "api/likes/board",
        type: "POST",
        data: jsonData,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert('추천 성공!');
            location.reload();
        },
        error: function () {
            location.reload();
        }
    });
});

$('#comment_insert').click(function () {
    var jsonData = JSON.stringify({
        content: $('#comment_content').val(),
        boardIdx: $('#board_idx').text()
    });
    $.ajax({
        url: url + "api/comments",
        type: "POST",
        data: jsonData,
        contentType: "application/json",
        dataType: "json",
        success: function (data, status) {
            location.reload();
        },
        error: function (status) {
            alert("댓글 저장에 실패하였습니다.")
        }
    });
});
