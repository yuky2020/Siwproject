<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import=" siwproject.siwproject.pg.*,siwproject.siwproject.model.*,org.springframework.beans.factory.annotation.Autowired,
 java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="fotografo" items="${fotografi}">
		<a href="http://localhost:8080/user/paginaFotograf/${fotografo.id}"> ${fotografo.nome} </a>
		<br>
	</c:forEach>
</body>
</html>