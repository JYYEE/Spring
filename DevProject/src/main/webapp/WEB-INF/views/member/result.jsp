<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
</head>
<body>
<h3>RESULT</h3>
<c:set value="${member }" var="member"/>
<c:set value="${member.nationality }" var="nationality"/>
<c:set value="${member.cars }" var="cars"/>
<c:set value="${member.hobby }" var="hobby"/>
	<table border="1">
		<%-- <c:if test="${member }"></c:if> --%>
		<tr>
			<td>유저ID</td>
			<td>${member.userId }</td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td>${member.password }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${member.userName }</td>
		</tr>
		<tr>
			<td>E-Mail</td>
			<td>${member.email }</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>${member.dateOfBirth }</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${member.gender}</td>
			<%-- <c:choose>
				<c:when test="${member.gender eq 'male' }">
					<td>남성</td>
				</c:when>
				<c:when test="${member.gender eq 'female' }">
					<td>여성</td>
				</c:when>
				<c:otherwise>
					<td>이외</td>
				</c:otherwise>
			</c:choose> --%>
		</tr>
		<tr>
			<td>개발자 여부</td>
			<td>${member.developer}</td>
			<%-- <c:choose>
				<c:when test="${member.developer eq 'Y' }">
					<td>개발자</td>
				</c:when>
				<c:otherwise>
					<td>비개발자</td>
				</c:otherwise>
			</c:choose> --%>
		</tr>
		<tr>
			<td>외국인 여부</td>
			<c:choose>
				<c:when test="${member.foreigner eq true }">
					<td>외국인</td>
				</c:when>
				<c:otherwise>
					<td>한국인</td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td>국적</td>
			<td>
				<c:forEach items="${nationality }" var="nat">
					<c:if test="${nat eq 'korea' }">
						대한민국
					</c:if>
					<c:if test="${nat eq 'germany' }">
						독일
					</c:if>
					<c:if test="${nat eq 'austrailia' }">
						호주
					</c:if>
					<c:if test="${nat eq 'canada' }">
						캐나다
					</c:if>
					<c:if test="${nat eq 'usa' }">
						미국
					</c:if>
					&nbsp;
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>소유차량</td>
			<td>
				<c:forEach items="${cars }" var="car">
					${car}&nbsp;
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>취미</td>
			<td>
				<c:forEach items="${hobby }" var="hob">
					<c:if test="${hob eq 'sports' }">
						운동
					</c:if>
					<c:if test="${hob eq 'music' }">
						음악듣기
					</c:if>
					<c:if test="${hob eq 'movie' }">
						영화보기
					</c:if>
					&nbsp;
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>우편번호</td>
			<td>${member.address.postCode }</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>${member.address.location }</td>
		</tr>
		<tr>
			<td>카드1 - 번호</td>
			<td>${member.cardList[0].no }</td>
		</tr>
		<tr>
			<td>카드1 - 유효년월</td>
			<td>${member.cardList[0].validMonth }</td>
		</tr>
		<tr>
			<td>카드2 - 번호</td>
			<td>${member.cardList[1].no }</td>
		</tr>
		<tr>
			<td>카드2 - 유효년월</td>
			<td>${member.cardList[1].validMonth }</td>
		</tr>
		<tr>
			<td>소개</td>
			<td>${member.introduction }</td>
		</tr>
	
	</table>
</body>
</html>