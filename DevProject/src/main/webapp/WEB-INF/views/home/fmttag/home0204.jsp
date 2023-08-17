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
	<p>JSTL 태그들의 Example ::: fmt:parseNumber</p>
	<p>3) type 속성이 pattern인 경우</p>
	<p>coin : ${coin }</p>
	<fmt:parseNumber value="${coin }" pattern="0,000.00" var="coinNum"/>
	<p>coinNum : ${coinNum }</p><!-- number로 파싱됨. 콤마 사라짐. -->
</body>
</html>