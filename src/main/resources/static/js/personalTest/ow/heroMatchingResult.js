//이미지주소만 어떻게 하자


// 화면에 쓰는 js
var heroEngName = $('#hero').val();
var x = $('x').val();
var y = $('y').val();

var heroArr = findHeroArray(heroEngName)

changeOgMeta(heroArr);
writeArticle(heroArr);

function findHeroArray(heroEngName) {
    for (i in HEROS) {
        if (HEROS[i][0] === heroEngName) {
            return HEROS[i];
        }
    }
}

function changeOgMeta(heroArr) {
    var heroEngName = heroArr[0];
    var heroKorName = heroArr[3];

    var title = '나는 ' + heroKorName + '와(과) 잘 어울려요.'
    var imgSrc = '/images/personalTest/ow/heros/' + heroEngName + '.png';
    var url = $(location).attr('href');

    // // 페이스북 관련 메타태그
    // $("#ogTitle").attr("content", title);
    // $("#ogImg").attr("content", imgSrc);
    // $("#ogUrl").attr("content", url);

    //hidden input에 주소 쓰기
    $("#link").val(url);
    //본문 이미지 바꾸기
    $("#heroImg").attr("src", imgSrc);
}

function writeArticle(heroArr) {
    var heroKorName = heroArr[3];
    var heroQuote = heroArr[4];
    var heroDescription = heroArr[5];

    $('#heroName').text(heroKorName);
    $('.heroName').text(heroKorName);
    $('#blockquote').text(heroQuote);
    $('#heroDescription').text(heroDescription);
}
