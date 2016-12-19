/**
 * Created by Knight_JXNU on 2016/12/19.
 */
var url_head="";

function buttonGet(url) {
    var request = new XMLHttpRequest();
    request.open("GET", url_head+url,true);
    request.send();
}
