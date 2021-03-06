

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(function() {
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

const protocol = location.protocol;
const host = location.host;

const url = protocol + "//" + host+'/';


function changeMeta() {

    var thisUrl = $(location).attr('href');
    var title = $(document).find("title").text();

    $("#ogTitle").attr("content", title);
    $("#ogUrl").attr("content", thisUrl);
}

changeMeta();

<!-- Global site tag (gtag.js) - Google Analytics -->
window.dataLayer = window.dataLayer || [];
function gtag(){dataLayer.push(arguments);}
gtag('js', new Date());

gtag('config', 'UA-168455544-1');
