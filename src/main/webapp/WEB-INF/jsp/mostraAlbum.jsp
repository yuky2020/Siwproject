<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="siwproject.siwproject.model.Album, java.Utils.List">
<% List<Album> albums = Database.getAlbum(); %>
<% request.setAttribute("albums", albums); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="album" items="${albums}">
		<a href="paginaAlbum.jsp?id=${album.id}"> ${album.nome} </a>
	</c:forEach>
</body>
</html>