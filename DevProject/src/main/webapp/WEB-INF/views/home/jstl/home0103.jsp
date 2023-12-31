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
   <p>JSTL 태그들의 Example ::: c:out</p>
   <p>2) default 속성</p>
   <table border="1">
      <tr>
         <td>member.userId</td>
         <td>${member.userId }</td>
      </tr>
      <tr>
         <td>member.userId</td>
         <td><c:out value="${member.userId }"/></td>
      </tr>
      <!-- 
      	default값을 설정하면 값이 존재하지 않을 때 값으로 설정됨.
       -->
      <tr>
         <td>member.userId</td>
         <td><c:out value="${member.userId }" default="hong123"/></td>
      </tr>
   </table>
</body>
</html>