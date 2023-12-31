<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>List</h2>
	<a href="/crud/member/register">등록</a>
	<%-- <form action="/crud/board/search" method="post">
		<input type="text" name="title" value="${board.title }"/>
		<input type="submit" value="검색"/>
	</form> --%>
	<table border="1">
		<tr>
			<td align="center" width="60">번호</td>
			<td align="center" width="80">아이디</td>
			<td align="center" width="70">비밀번호</td>
			<td align="center" width="70">사용자명</td>
			<td align="center" width="180">작성일</td>
		</tr>
		<c:choose>
			<c:when test="${empty memberList }">
				<tr>
					<td colspan="4">조회하신 회원정보가 존재하지 않습니다. </td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${memberList }" var="member">
					<tr>
						<td align="center">${member.userNo}</td>
						<td align="center">
							<a href="/crud/member/read?userNo=${member.userNo}">
								${member.userId}
							</a>
						</td>
						<td align="center">${member.userPw}</td>
						<td align="center">${member.userName}</td>
						<td align="center"><fmt:formatDate value="${member.regDate}" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>