<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>7장. JSP</h3>
	<p>JSTL 태그들의 Example ::: fmt:parseDate</p>
	<p>10) dateStyle 속성을 지정하지 않으면 기본값은 default이다.</p>
	<p>dateValue : ${dateValue }</p>
	<fmt:parseDate value="${dateValue }" type="date" var="date"/>
	<p>date : ${date }</p>
</body>
</html>