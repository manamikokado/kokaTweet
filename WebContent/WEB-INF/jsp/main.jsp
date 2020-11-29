<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
// セッションスコープのログインユーザ情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>こかついーと</title>
</head>
<body>
<h1>こかついーとメイン</h1>
<p>
<%= loginUser.getName() %>さん、ログイン中
</p>
<a href="/kokaTweet/Logout">ログアウト</a>
</body>
</html>