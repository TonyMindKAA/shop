<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>KAA-Pro</h1>
	<img alt="" src="http://localhost:8080/Task_8/captcha.png">
	<c:out value="${kaa}" />
	sdsd
	<br>
	ook
	<c:out value="${capchasId}" />
	<br>
	<c:out value="${capchasValue}" />
	<br>
	<c:out value="${val}" />
	
	<img alt="" src="data:image/png;base64,<c:out value="${img}" />">
</body>
</html>