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
	<p>7) type 속성을 time로 지정하여 시간 포멧팅을 한다.</p>
	<p>now : ${now }</p>
	<p>time default : <fmt:formatDate value="${now }" type="time" timeStyle="default"/></p>
	<p>time short : <fmt:formatDate value="${now }" type="time" timeStyle="short"/></p>
	<p>time medium : <fmt:formatDate value="${now }" type="time" timeStyle="medium"/></p>
	<p>time long : <fmt:formatDate value="${now }" type="time" timeStyle="long"/></p>
	<p>time full : <fmt:formatDate value="${now }" type="time" timeStyle="full"/></p>
</body>
</html>