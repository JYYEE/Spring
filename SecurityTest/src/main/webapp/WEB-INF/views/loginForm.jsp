<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginForm</title>
</head>
<body>
	<h1>LoginForm</h1>
	<h2><c:out value="${error }"/> </h2> <!-- 에러발생 시, 출력할 메세지 -->
	<h2><c:out value="${logout }"/> </h2> <!-- 로그아웃 시, 출력할 메세지 -->
	
	<!-- 스프링 시큐리티에서 인증하기 위해 반드시 지켜야할 규칙
		- controller로 넘어가는 폼태그가 아닌 스프링 시큐리티가 자동으로 처리해주는 폼태그
		- form태그 속성 action="/login", method="post"
		- name="username", name="password"로 지정
		- <sec:csrfInput/>을 반드시 포함(없으면 400 에러 발생)
	 -->
	<form action="/login" method="post">
		username : <input type="text" name="username" value="admin"/><br/>
		password : <input type="text" name="password" value="admin"/><br/>
		<!-- 로그인 상태 유지 체크박스 -->
		<input type="checkbox" name="remember-me"/>Remember-me
		<input type="submit" value="로그인"/>
		<sec:csrfInput/>
	</form>
</body>
</html>