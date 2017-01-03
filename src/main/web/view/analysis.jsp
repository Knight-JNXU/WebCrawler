<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Knight_JXNU
  Date: 2017/1/3
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="Shortcut Icon" href="../images/icon.ico">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0 maximum-scale=1.0, user-scalable=no"/>
    <Title>analysis</Title>
    <link href="../css/zzsc-demo.css" rel="stylesheet" type="text/css"/>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
    <link href="../css/basictable.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="../js/jquery.basictable.js"></script>
</head>
<body>
<div class="zzsc-container">
    <div id="page">

        <table id="table">
            <thead>
            <tr>
                <th>搜索内容</th>
                <th>搜索次数</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${records}">
                <tr>
                    <td>${item.shTitle}</td>
                    <td>${item.num}</td>
                </tr>
            </c:forEach>
            <%--<tr>
                <td>Jill Smith</td>
                <td>25</td>
            </tr>
            <tr>
                <td>John Stone</td>
                <td>30</td>
            </tr>
            <tr>
                <td>Jane Strip</td>
                <td>29</td>
            </tr>
            <tr>
                <td>Gary Mountain</td>
                <td>21</td>
            </tr>
            <tr>
                <td>James Camera</td>
                <td>31</td>
            </tr>--%>
            </tbody>
        </table>
        <script>
            $('#table').basictable();
        </script>
    </div>
</div>
</body>
</html>
