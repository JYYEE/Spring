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
	<h3>7장. JSP</h3>
	<p>JSTL 태그들의 Example ::: c:import 사용</p>
	<p>특정 URL의 결과를 읽어와서 현재 위치에 삽입한다.</p>
	<p>절대 URL</p>
	<!-- import 경로에 있는 결과값을 가져와서 한 페이지에 출력 -->
	<c:import url="http://localhost/board/list"/>
	<c:import url="http://localhost/board/search">
		<c:param name="keyword" value="orange"/>
	</c:import>
	<br/>
	<p>상대 URL - 절대 경로</p>
	<c:import url="http://localhost/board/list"/>

	<p>상대 URL - 상대 경로</p>
	<c:import url="../../board/list.jsp"/>
	
</body>
</html>