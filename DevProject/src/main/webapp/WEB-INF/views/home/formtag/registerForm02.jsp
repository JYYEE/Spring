<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Spring Form</h4>
	<p>1) form:form 태그를 이용한 form을 생성해보자</p>
	<!-- 
		form으로 제출된 내용은 modelAttribute로 설정된 자바빈즈 객체의 속성 
		modelAttribute : form태그에서 가용하고 있는 자바빈즈 객체의 별칭으로 생각
		조건 : 서버에서도 modelAttribute로 설정한 자바빈즈 객체를 내보내줘야 순환체계가 이어짐.
	-->
	
	<form:form modelAttribute="user" method="post" action="/formtag/register">
		<table border="1">
			<tr>
				<td>유저 ID</td>
				<td>
					<form:input path="userId"/>
					<font color="red">
						<form:errors path="userId"/>
					</font>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<form:input path="userName"/>
					<font color="red">
						<form:errors path="userName"/>
					</font>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
	
</body>
</html>