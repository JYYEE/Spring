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
	<p>JSTL 태그들의 Example ::: fmt:formatDate</p>
	<p>6) type 속성을 date로 지정하여 날짜 포멧팅을 한다.</p>
	<p>now : ${now }</p>
	<p>date default : <fmt:formatDate value="${now }" type="date" dateStyle="default"/></p>
	<p>date short : <fmt:formatDate value="${now }" type="date" dateStyle="short"/></p>
	<p>date medium : <fmt:formatDate value="${now }" type="date" dateStyle="medium"/></p>
	<p>date long : <fmt:formatDate value="${now }" type="date" dateStyle="long"/></p>
	<p>date full : <fmt:formatDate value="${now }" type="date" dateStyle="full"/></p>
</body>
</html>