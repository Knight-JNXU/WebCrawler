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
</head>
<body>
<div class="resultdiv">
    <c:forEach var="item" items="${list}">
        <a class="link" href="${item.url}">${item.shTitle}</a><br>
        ${item.author}<br><br>
    </c:forEach>
</div>
</body>
</html>
