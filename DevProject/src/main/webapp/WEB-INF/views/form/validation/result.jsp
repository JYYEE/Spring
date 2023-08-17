<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Result</h3>
	<table>
		<tr>
			<td>유저ID</td>
			<td>${member.userId}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${member.userName}</td>
		</tr>
		<tr>
			<td>E-Mail</td>
			<td>${member.email}</td>
		</tr>
	</table>
</body>
</html>