/**
 * Created by Knight_JXNU on 2016/12/19.
 */
var url_head="/CSDNSearch/";
function getHead() {
    return url_head;
}
function checkSearch() {
    var target = $('#searchInput').val();
    if(target==""){
        alert("请输入关键字!");
        return false;
    }
    return true;
}
function buttonGet(url) {
    var request = new XMLHttpRequest();
    request.open("GET", url_head+url,true);
    request.send();
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
