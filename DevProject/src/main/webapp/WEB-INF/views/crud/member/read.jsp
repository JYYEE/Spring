<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h2>Read</h2>
	<table>
		<tr>
			<td>userId</td>
			<td>${member.userId }</td>
		</tr>
		<tr>
			<td>userName</td>
			<td>${member.userName }</td>
		</tr>
		<tr>
			<td>auth - 1</td>
			<td>${member.authList[0].auth }</td>
		</tr>
		<tr>
			<td>auth - 2</td>
			<td>${member.authList[1].auth }</td>
		</tr>
		<tr>
			<td>auth - 3</td>
			<td>${member.authList[2].auth }</td>
		</tr>
	</table>
	<button type="button" id="btnModify">수정</button>
	<button type="button" id="btnRemove">삭제</button>
	<button type="button" id="btnList">목록</button>
	<form method="post" action="/crud/member/remove" id="delForm">
		<input type="hidden" name="userNo" value="${member.userNo }">
	</form>
</body>
<script type="text/javascript">
$(function(){
	var btnModify = $('#btnModify');
	var btnDelete = $('#btnDelete');
	var btnList = $('#btnList');
	var delForm = $('#delForm');
	var btnRemove = $('#btnRemove');
	
	// 수정 버튼 클릭 시 이벤트
	btnModify.on("click", function(){
		delForm.attr("action", "/crud/member/modify");
		delForm.attr("method", "get");
		delForm.submit();
	}); 
	// 삭제 버튼 클릭 시 이벤트
	btnRemove.on("click", function(){
		if(confirm("정말로 삭제하시겠습니까?")){
			delForm.submit();
		}
	});
	// 목록 버튼 클릭 시 이벤트
	btnList.on("click", function(){
		location.href="/crud/member/list";
	});
})

</script>
</html>