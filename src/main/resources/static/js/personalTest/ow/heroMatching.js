$('.label').on('click', function () {
    $(this).prev().prop("checked", true);
    var nextQuestion = $(this).parent().parent().parent().parent().parent().next().next().next().next(); //item
    var windowHeight = $(window).height();
    var nextQuestionOffset = nextQuestion.offset();

    $("html, body").animate({
        scrollTop: nextQuestionOffset.top - windowHeight / 2
    }, 500);
})

$('input').on('click', function () {
    $(this).prev().prop("checked", true);
    var nextQuestion = $(this).parent().parent().parent().parent().parent().next().next().next().next(); //item
    var windowHeight = $(window).height();
    var nextQuestionOffset = nextQuestion.offset();

    $("html, body").animate({
        scrollTop: nextQuestionOffset.top - windowHeight / 2
    }, 500);
})



$('#find').on('click', function () {
    var q1 = $("input[name='1']:checked").val();
    var q2 = $("input[name='2']:checked").val();
    var q3 = $("input[name='3']:checked").val();
    var q4 = $("input[name='4']:checked").val();
    var q5 = $("input[name='5']:checked").val();

    var q6 = $("input[name='6']:checked").val();
    var q7 = $("input[name='7']:checked").val();
    var q8 = $("input[name='8']:checked").val();
    var q9 = $("input[name='9']:checked").val();
    var q10 = $("input[name='10']:checked").val();

    var questionOnetToSix = Number(q1) + Number(q2) + Number(q3) + Number(q4) + Number(q5) + 10;
    var questionSevenToTwelve = Number(q6) + Number(q7) + Number(q8) + Number(q9) + Number(q10) + 10;

    var resultNumArr = [questionOnetToSix, questionSevenToTwelve];

    console.log('결과 : ' + resultNumArr);
    console.log('총 배점 : ');
    console.log('내향 및 외향 점수 : ' + q1 + '/' + q2 + '/' + q3 + '/' + q4 + '/' + q5);
    console.log('논리 및 공감 점수 : ' + q6 + '/' + q7 + '/' + q8 + '/' + q9 + '/' + q10);


    var matchedHero = findHero(resultNumArr);

    if (matchedHero === undefined) {
        findUncheckedInput();
    } else {
        var heroName = encodeURI(matchedHero[0]);
        var x = matchedHero[1];
        var y = matchedHero[2];
        location.href = "http://localhost:8080/personalTest/ow/heroMatching/result?heroName=" + heroName + "&x=" + x + "&y=" + y;
    }

});

function findUncheckedInput(inputValue, question) {
    for (i = 1; i <= 10; i++) {
        var inputValue = $('input[name="' + i + '"]:checked').val();
        var question = $('#question' + i);

        if (inputValue === undefined) {
            var warningMessage = $('<div>이런, 체크를 놓치셨네요! 다시 한 번 봐주실래요?</div>').css("color", "red");
            question.before(warningMessage);
            return moveToQuestion(question);
        }
    }
}

function moveToQuestion(question) {
    var windowHeight = $(window).height();
    var questionOffset = question.offset();

    return $("html, body").animate({
        scrollTop: questionOffset.top - windowHeight / 2
    }, 500);
}

function findHero(resultNumArr) {

    for (i in HEROS) {
        console.log("i: " + i + "; value: " + HEROS[i]);
    }

    if (resultNumArr[0] === 5) {//만약 첫째자리가 5이고
        if (resultNumArr[1] >= 10) { //둘째자리가 10과 같거나 크면 브리기테.
            return BRIGITTE;
        }
    }

    if (resultNumArr[0] >= 15) {//만약 첫째자리가 15 이상이고
        if (resultNumArr[1] >= 10) { //둘째자리가 10과 같거나 크면 젠야타.
            return ZENYATTA;
        }
    }


    for (i in HEROS) {
        if (resultNumArr[0] === HEROS[i][1]) { // 첫째자리가 맞을 떄
            if (resultNumArr[1] === HEROS[i][2]) { //둘째자리까지 맞으면 영웅을 return
                return HEROS[i];
            }

        } else {
            //둘째자리가 맞지 않다면 첫째자리가 같은 영웅들의 둘째자리 데이터 중 결과와 근사한 값으로 보내버리기
            return findNearHero(resultNumArr, HEROS);
        }
    }
}

function findNearHero(resultNumArr, HEROS) {
    var firstMatchedHeros = [];
    var firstMatchedHerosSecond = []; //비교할 두번째 값

    for (i in HEROS) {
        if (resultNumArr[0] === HEROS[i][1]) {
            firstMatchedHeros.push(HEROS[i]);
            firstMatchedHerosSecond.push(HEROS[i][2]);
        }
    }

    console.log('첫째자리가 같은 영웅 : ' + firstMatchedHeros);
    console.log('첫째자리가 같은 영웅의 둘째자리 : ' + firstMatchedHerosSecond);

    if (firstMatchedHeros.length === 1) {
        return firstMatchedHeros[0];
    }

    var near = 0; //제일 가까운 수가 담길 변수
    var target = resultNumArr[1];
    var abs = 0;
    var min = Math.max.apply(null, firstMatchedHerosSecond); //해당 범위에서 가장 큰 값
    console.log('최댓값 :' + min);
    console.log('근사값을 찾는 숫자: ' + target);

    for (var i = 0; i < firstMatchedHerosSecond.length; i++) {
        abs = ((firstMatchedHerosSecond[i] - target) < 0) ? - ((firstMatchedHerosSecond[i]) - target) : (firstMatchedHerosSecond[i] - target);
        if (abs < min) {
            min = abs;
            near = firstMatchedHerosSecond[i];
        }
    }

    console.log('제일 가까운 값 : ' + near);

    for (i in firstMatchedHeros) {
        if (firstMatchedHeros[i][2] === near) {
            return firstMatchedHeros[i];
        }
    }
}