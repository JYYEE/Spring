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
	<p>11) dateStyle 속성으로 short로 지정하여 문자열을 Date 객체로 변환한다.</p>
	<p>dateValueS : ${dateValueS }</p>
	<p>dateValueM : ${dateValueM }</p>
	<p>dateValueL : ${dateValueL }</p>
	<p>dateValueF : ${dateValueF }</p>
	<fmt:parseDate value="${dateValueS }" type="date" dateStyle="short" var="dateS"/>
	<fmt:parseDate value="${dateValueM }" type="date" dateStyle="medium" var="dateM"/>
	<fmt:parseDate value="${dateValueL }" type="date" dateStyle="long" var="dateL"/>
	<fmt:parseDate value="${dateValueF }" type="date" dateStyle="full" var="dateF"/>
	<p>dateS : ${dateS }</p>
	<p>dateM : ${dateM }</p>
	<p>dateL : ${dateL }</p>
	<p>dateF : ${dateF }</p>
</body>
</html>