<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div id="aladin-bookinfo"></div>
	<input type="button" value="검색" onclick="search();">
	<p></p>	
</body> 
<script type="text/javascript">
	function search(){
		var main = $("div[id^=aladin-bookinfo]"); // aladin-bookinfo로 시작하는 id를 가진 div 엘리먼트를 찾습니다.
		// 그 엘리먼트가 존재한다면
		if(main.length>0) { 
		     var id = main.attr("id").substr(16, 10); // ISBN(10자리)부분을 잘라내서 얻습니다 (16칸부터 10개만큼의 문자 얻기)
		     var url = "http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx?ttbkey=ttbwlsgustjd882031001&cover=big&ItemId=" + id + "&output=js&callback=bookDisplay";
		    
		     // 콜백 함수입니다.
		     function bookDisplay(success, data) {
		 
		        /* 여기서 책 정보를 이용해 구조를 만듭니다! */
		     }
		 
		    // AJAX 요청을 보냅니다. getJSON() 함수는 알라딘 API 대용으로 사용불가.
	     $.ajax({
	        url: url,
	        jsonp: "bookDisplay",
	        dataType: "jsonp"
	     });
			
		}

</script>
</html>