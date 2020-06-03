
$('.ui.dropdown')
    .dropdown();

ClassicEditor
    .create( document.querySelector( '#editor' ) )
    .then( editor => {
        console.log( 'Editor was initialized', editor );
        myEditor = editor;
    } )
    .catch( error => {
        console.error( error );
    } );


$(document).ready(function () {
    $('.negative').hide();
});
$(".close.icon").click(function () {
    $(this).parent().hide();
});


$('#insert').click(function () {
    var title = $('#board_title').val();
    var inputValue = $('input[name="policy"]:checked').val();

    if (checkIsNotEmptyWithoutSpace(title) === false) {
        $("#titleNegative").fadeIn(2000);
        document.getElementById("focusTop").scrollIntoView();
        $('#title').focus();
    } else if(checkLength(title, 50) === false){
        $("#titleNegative").fadeIn(2000);
        document.getElementById("focusTop").scrollIntoView();
        $('#title').focus();
    } else if(findUncheckedInput(inputValue) === false){
        $('#policy').attr('class','ui red message');
        document.getElementById("focusTop").scrollIntoView();
    } else{
        var jsonData = JSON.stringify({
            title: $('#board_title').val(),
            content: myEditor.getData(),
            boardType: $('#board_type option:selected').val()
        });
        $.ajax({
            url: url + "api/boards",
            type: "POST",
            data: jsonData,
            contentType: "application/json",
            dataType: "json",
            success: function () {
                location.href = '/board/list';
            },
            error: function () {
                alert('무엇인가 잘못되었네요!');
            }
        });

    }
});
