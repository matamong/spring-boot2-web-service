function checkIsNotEmpty(content) {
    var blank_pattern = /^\s+|\s+$/g;

    if (content === null || content === "") {
        return false;
    } else if (content.search(/\s/) != -1) {
        return false;
    } else if(content.replace( blank_pattern, '' ) == ""){
        return false;
    } else{
        return true;
    }
}

function checkIsNotEmptyWithoutSpace(content){
    if (content === null || content === "") {
        return false;
    }else{
        return true;
    }
}

function checkLength(content, lengthUwant) {
    var contentLength = 0;
    var engCheck = /[a-z]/;
    var numCheck = /[0-9]/;

    for (var i = 0; i < content.length; i++) {
        //한글은 2, 영문은 1로 치환
        contentChar = content.charAt(i);
        if (escape(contentChar).length > 4) {
            contentLength += 2;
        } else { contentLength += 1; }
    }

    if (contentLength < 2 || contentLength > lengthUwant) {
        return false;
    } else{
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