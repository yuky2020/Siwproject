<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="silph.model.*,silph.pg.*" %>
<% Long id = Long.parseLong(request.getParameter("id")); %>
<% Album a = Database.getAlbumPerId(id); %>
<% request.setAttribute("album", a); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> ${album.nome}</h1><br>
	${album.fotografo.nome}
</body>
</html>