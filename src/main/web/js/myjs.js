/**
 * Created by Knight_JXNU on 2016/12/19.
 */
var url_head="";
function buttonGet(url) {
    var request = new XMLHttpRequest();
    request.open("GET", url_head+url,true);
    request.send();
}
function search(url, name) {
    var request = new XMLHttpRequest();
    request.open("post", url_head+url,true);
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send("name="+name);
}
function checkIsRun(url) {
    $.ajax({
        type:'GET',
        url:url_head+url,
        dataType:'json',
        complete:function (coordinates) {
            alert(coordinates.responseText);
        }
    });
}
function inputStop(url) {
    $.ajax({
        type:'GET',
        url:url_head+url,
        dataType:'json',
        complete:function (coordinates) {
            alert(coordinates.responseText);
        }
    });
}