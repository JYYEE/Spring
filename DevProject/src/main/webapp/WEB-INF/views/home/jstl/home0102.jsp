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
   <p>1) escapeXml 속성의 기본값은 true이고, 특수 문자를 변환한다.</p>
   <table border="1">
      <tr>
         <td>member.userId</td>
         <td>${member.userId }</td>
         <td>결과 : hongkd<>&%0101 </td>
      </tr>
      <tr>
         <td>member.userId</td>
         <td><c:out value="${member.userId }"/></td>
         <td>결과 : &lt;p>hongkd<>&%0101&lt;/p></td>
      </tr>
      <!-- 
      	태그가 태그의 역할을 하도록 출력된다. (escapeXml 속성의 값이 false 일 때
       -->
      <tr>
         <td>member.userId</td>
         <td><c:out value="${member.userId }" escapeXml="false"/></td>
         <td>결과 : hongkd<>&%0102 </td>
      </tr>
   </table>
</body>
</html>