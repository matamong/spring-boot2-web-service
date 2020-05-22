//공유 js
$(document).ready(function () {
    //input에 데이터넣고
    // 다른 버튼에 인풋 데이터를 data text value 넣은거 따옴
    var btn = document.getElementById('copyLink');
    var clipboard = new Clipboard(btn);
    clipboard.on('success', function (e) {
        alert('주소가 복사됐습니다!');
    });
    clipboard.on('error', function (e) {
        console.log('에러');
    });
});




function sendSns(sns, txt) {
    var o;
    var _url = encodeURIComponent($(location).attr('href'));
    var _txt = encodeURIComponent(txt);
    var _br = encodeURIComponent('\r\n');

    switch (sns) {
        case 'facebook':
            o = {
                method: 'popup',
                url: 'http://www.facebook.com/sharer/sharer.php?u=' + _url
            };
            break;

        case 'twitter':
            o = {
                method: 'popup',
                url: 'http://twitter.com/intent/tweet?text=' + _txt + '&url=' + _url
            };
            break;

        case 'me2day':
            o = {
                method: 'popup',
                url: 'http://me2day.net/posts/new?new_post[body]=' + _txt + _br + _url + '&new_post[tags]=epiloum'
            };
            break;

        case 'kakaotalk':
            alert('준비 중입니다. 주소복사를 이용해주세요 :)')
            kakaotalk();
            break;

        case 'kakaostory':
            o = {
                method: 'web2app',
                param: 'posting?post=' + _txt + _br + _url + '&apiver=1.0&appver=2.0&appid=dev.epiloum.net&appname=' + encodeURIComponent('Epiloum 개발노트'),
                a_store: 'itms-apps://itunes.apple.com/app/id486244601?mt=8',
                g_store: 'market://details?id=com.kakao.story',
                a_proto: 'storylink://',
                g_proto: 'scheme=kakaolink;package=com.kakao.story'
            };
            break;

        case 'band':
            o = {
                method: 'web2app',
                param: 'create/post?text=' + _txt + _br + _url,
                a_store: 'itms-apps://itunes.apple.com/app/id542613198?mt=8',
                g_store: 'market://details?id=com.nhn.android.band',
                a_proto: 'bandapp://',
                g_proto: 'scheme=bandapp;package=com.nhn.android.band'
            };
            break;

        default:
            alert('지원하지 않는 SNS입니다.');
            return false;
    }

    switch (o.method) {
        case 'popup':
            window.open(o.url);
            break;

        case 'web2app':
            if (navigator.userAgent.match(/android/i)) {
                // Android
                setTimeout(function () { location.href = 'intent://' + o.param + '#Intent;' + o.g_proto + ';end' }, 100);
            }
            else if (navigator.userAgent.match(/(iphone)|(ipod)|(ipad)/i)) {
                // Apple
                setTimeout(function () { location.href = o.a_store; }, 200);
                setTimeout(function () { location.href = o.a_proto + o.param }, 100);
            }
            else {
                alert('이 기능은 모바일에서만 사용할 수 있습니다.');
            }
            break;
    }
}