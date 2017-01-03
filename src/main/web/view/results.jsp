<%--
  Created by IntelliJ IDEA.
  User: Knight_JXNU
  Date: 2016/12/23
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <link rel="Shortcut Icon" href="../images/icon.ico">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <Title>results</Title>
    <link href="../css/mystyle.css" rel="stylesheet" type="text/css"/>
    <link href="../css/resultstyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="resultdiv">
    <div class="numstyle">结果数量：${size}</div>
    <c:forEach var="item" items="${list}">
        <article class="excerpt excerpt-5" style=""><a class="focus" href="#" title="用DTcms做一个独立博客网站（响应式模板）" target="_blank" ></a>
            <header><a class="cat" style= "cursor:default" href="javascript:return false;" >${item.author}<i></i></a>
                <h2><a href="${item.url}" target="_blank" >${item.title}</a>
                </h2>
            </header>
        </article>
    </c:forEach>
</div>
</body>
</html>
