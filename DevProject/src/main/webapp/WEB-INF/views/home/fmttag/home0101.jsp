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
	<p>JSTL 태그들의 Example ::: fmt:formatNumber</p>
	<p>1) type 속성을 지정하거나 pattern 속성을 지정하여 숫자를 형식화한다.</p>
	<p>number : ${coin }</p>
	<p>currency : <fmt:formatNumber value="${coin }" type="currency"/></p><!-- 통화기호 (원단위) 붙음-->
	<p>percent : <fmt:formatNumber value="${coin }" type="percent"/></p><!-- *100 -->
	<p>pattern : <fmt:formatNumber value="${coin }" pattern="000000.00"/></p><!-- 000000.00의 형식으로 출력 -->
</body>
</html>