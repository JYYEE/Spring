<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>READ04</title>
</head>
<body>
	<h3>READ04 Result</h3>
	<p>hobbyArray</p>
	<c:forEach items="${hobbyArray }" var="hobby">
		<c:out value="${hobby }"/>
	</c:forEach><hr/>

	<p>hobbyList</p>
	<c:forEach items="${hobbyList }" var="hobby">
		<c:out value="${hobby }"/>
	</c:forEach><hr/>
	
	<p>carArray</p>
	<c:forEach items="${carArray }" var="car">
		<c:out value="${car }"/>
	</c:forEach><hr/>
	
	<p>carList</p>
	<c:forEach items="${carList }" var="car">
		<c:out value="${car }"/>
	</c:forEach><hr/>
</body>
</html>