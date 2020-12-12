<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Tweet, java.util.List" %>
<%
// セッションスコープのログインユーザ情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<%
// アプリケーションスコープに保存されたつぶやきリストを取得
List<Tweet> tweetList = (List<Tweet>) application.getAttribute("tweetList");
// リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
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
<p><a href="/kokaTweet/Main">更新</a></p>
<form action="/kokaTweet/Main" method="post">
<input type="text" name="text"><br>
<input type="submit" value="つぶやく" style="margin-top: 8px"><br>
</form>
<% if(errorMsg != null) { %>
	<p style="background-color: #ff6347; color: #ffffff; padding: 3px;"><%= errorMsg %></p>
<% } %>
<% for(Tweet tweet : tweetList) { %>
	<p><%= tweet.getUserName() %> : <%= tweet.getText() %></p>
<% } %>
</body>
</html>