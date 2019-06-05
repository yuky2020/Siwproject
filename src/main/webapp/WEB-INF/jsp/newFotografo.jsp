<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="controllerFotografo" method="get">
		Nome: <input type="text" name="nome"><br>
		<input type="submit" value="conferma">
		<br> ${voidName} ${duplicatedName} ${longNameError}
	</form>
</body>
</html>