<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
// セッションスコープからログインユーザ情報を取得
User loginUser = (User)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>こかついーと</title>
</head>
<body>
<h1>こかついーとログイン</h1>
<% if(loginUser != null) { %>
	<p>ログインに成功しました</p>
	<p>ようこそ<%= loginUser.getName() %>さん</p>
<% } else { %>
	<p>ログインに失敗しました</p>
	<a href="/kokaTweet/">TOPへ</a>
<% } %>
</body>
</html>