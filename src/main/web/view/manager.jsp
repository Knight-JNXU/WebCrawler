<%--
  Created by IntelliJ IDEA.
  User: Knight_JXNU
  Date: 2016/12/23
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="Shortcut Icon" href="images/icon.jpg">
    <meta charset="UTF-8">
    <title>manager</title>
    <link href="../css/mystyle.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/myjs.js"></script>
    <script type="text/javascript" src="../js/jquery-2.1.4.js"></script>
</head>
<body style="text-align:center">
<h1>Films Seach Backstage</h1>
<div style="margin-top: 0%;">
    <input type="button" class="managersubmit" value="录入" onclick="buttonGet('/manag/input')"><br><br>
    <input type="button" class="managersubmit" value="检测" onclick="checkIsRun('/manag/isRunning')"><br><br>
    <input type="button" class="managersubmit" value="停止" onclick="inputStop('/manag/stop')"><br><br>
</div>
</body>
</html>
