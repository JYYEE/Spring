<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
	<c:if test="${not empty bvo }">
		<script type="text/javascript">
			alert("삽입 완료");
		</script>
	</c:if>
	<h2>book</h2>
	<p></p>	
	<button type="button" onclick="nextPage()">다음</button>
	<button type="button" onclick="beforePage()">이전</button>

	<input type="text" id="bookId" placeholder="책 검색 하기">
	<button id="bookBtn">검색하기</button>
	
	<button id="listGet">책 가져오기</button>

	<div id="selectOneBook"></div>
	<div id="bookList"></div>
	<form id="bookForm" action="/bookAll" method="post">
		<input type="hidden" id='name' name="book_title">
		<input type="hidden" id='summary' name="book_content">
		<input type="hidden" id='cover' name="book_cover">
		<input type="hidden" id='date' name="book_spmt_ymd">
		<input type="hidden" id='author' name="book_author">
		<input type="hidden" id='publisher' name="pub_nm">
		<input type="hidden" id='talt' name="book_talt" value="NO">
		<input type="hidden" name="ccg_b001" value='종이책'>
		<input type="hidden" name="ccg_b002" value='국내도서'>
		<input type="hidden" name="ccg_b003" value='컴퓨터/IT'>
		<input type="hidden" id='price' name="book_amt">
		<input type="hidden" id='isbn' name="book_isbn">
		<input type="button" id="btn" value="제출">
	</form>
	
	<table border="1">
		<thead>
			 <tr>
				<td>책 번호</td>
				<td>책 제목</td>
				<td>책 isbn</td>
				<td>책 발행일</td>
				<td>책 가격</td>
				<td>책 작가</td>
				<td>책 내용</td>
				<td>책 출판사</td>
				<td>책 사진</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bvo }" var="bvo">
				<tr>
					<td>${bvo.book_num }</td>
					<td>${bvo.book_name }</td>
					<td>${bvo.book_isbn }</td>
					<td>${bvo.book_date }</td>
					<td>${bvo.book_price }</td>
					<td>${bvo.book_author }</td>
					<td>${bvo.book_summary }</td>
					<td>${bvo.book_publisher }</td>
					<td><img src="${bvo.book_cover }" ></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
<script type="text/javascript">
$(function(){
	var btn = $("#btn");
	var bookBtn = $("#bookBtn");
	var listGet = $("#listGet");
	var selectOneBook = document.querySelector("#selectOneBook");
	num = 1;
	



	listGet.on("click", function(){
		$.ajax({
		      url: "/bookOnGet",
		      method: "POST",
		      success: function(response) {
		        var p1 = document.createElement("p");
				p1.textContent = response.book_title;

				var img = document.createElement("img");
				img.src = response.book_cover;
				img.width = 200;
				img.height = 330;
				selectOneBook.appendChild(p1);
				selectOneBook.appendChild(img);



		      },
		      error: function(xhr, status, error) {
		        // 요청을 보내는 도중에 오류가 발생한 경우의 처리
		        console.error("오류가 발생했습니다:", error);
		      }
		    });
	})


	bookBtn.on("click", function(){
		var bookList = document.querySelector("#bookList");
		var bookId = $("#bookId").val();
		bookList.innerHTML = "";
		$.ajax({
			type : "get",
			url : "https://dapi.kakao.com/v3/search/book?target=publisher&size=50&page="+num,
			data : {
				query : bookId
			},
			headers : {
				Authorization : "KakaoAK de3f93057e79cda997a80fe332c0e815"
			}
		})
		
	.done(function(msg) {
		  for (var i = 0; i < msg.documents.length; i++) {
		    var timeMsg = msg.documents[i].datetime.split('T')[0];
		    var isbn13 = msg.documents[i].isbn;
		    if (msg.documents[i].isbn.length > 14) {
		      isbn13 = msg.documents[i].isbn.split(" ")[1];
		    }
		    var content = msg.documents[i].contents.split('.');
		    var inputContent = '';
		    console.log(msg.meta)
		    for(var j = 0; j < content.length-1; j++){
		    	inputContent += content[j];
		    }
		    	
		    var div = document.createElement("div");
		
		    var bookName = document.createElement("strong");
		    bookName.classList.add("book_name");
		    bookName.innerHTML = msg.documents[i].title.split('(')[0];
		    div.appendChild(bookName);
			div.appendChild(document.createElement("br"));
	
		    var bookCover = document.createElement("img");
		    bookCover.classList.add("book_cover");
		    bookCover.src = msg.documents[i].thumbnail;
		    div.appendChild(bookCover);
			div.appendChild(document.createElement("br"));
	
		    var bookAuthor = document.createElement("strong");
		    bookAuthor.classList.add("book_author");
		    bookAuthor.innerHTML = msg.documents[i].authors[0];
		    div.appendChild(bookAuthor);
			div.appendChild(document.createElement("br"));
	
		    var bookUrl = document.createElement("strong");
		    bookUrl.classList.add("book_url");
		    bookUrl.innerHTML = msg.documents[i].url;
		    div.appendChild(bookUrl);
			div.appendChild(document.createElement("br"));
	
		    var bookSummary = document.createElement("strong");
		    bookSummary.classList.add("book_summary");
		    bookSummary.innerHTML = inputContent;
		    div.appendChild(bookSummary);
			div.appendChild(document.createElement("br"));
	
		    var bookDate = document.createElement("strong");
		    bookDate.classList.add("book_date");
		    bookDate.innerHTML = timeMsg;
		    div.appendChild(bookDate);
			div.appendChild(document.createElement("br"));
	
		    var bookISBN = document.createElement("strong");
		    bookISBN.classList.add("book_isbn");
		    bookISBN.innerHTML = isbn13;
		    div.appendChild(bookISBN);
			div.appendChild(document.createElement("br"));
	
		    var bookPrice = document.createElement("strong");
		    bookPrice.classList.add("book_price");
		    bookPrice.innerHTML = msg.documents[i].price;
		    div.appendChild(bookPrice);
			div.appendChild(document.createElement("br"));
	
		    var bookPublisher = document.createElement("strong");
		    bookPublisher.classList.add("book_publisher");
		    bookPublisher.innerHTML = msg.documents[i].publisher;
		    div.appendChild(bookPublisher);
			div.appendChild(document.createElement("br"));
	
		    var bookTranslators = document.createElement("strong");
		    bookTranslators.classList.add("book_translators");
		    if (msg.documents[i].translators[0] != null) {
		      bookTranslators.innerHTML = msg.documents[i].translators[0];
		    } else {
		      bookTranslators.innerHTML = "NO";
		    }
		    div.appendChild(bookTranslators);
			div.appendChild(document.createElement("br"));
	
		    var deleteButton = document.createElement("button");
		    deleteButton.innerHTML = "삭제";
		    deleteButton.onclick = function() {
		      deleteDiv(this);
		    };
		    div.appendChild(deleteButton);
			div.appendChild(document.createElement("br"));
	
		    div.appendChild(document.createElement("br"));
		
		    bookList.appendChild(div);
		  }
		})

	})
	btn.on('click', function(){
		  const book_name = document.querySelectorAll(".book_name");
		  const book_cover = document.querySelectorAll(".book_cover");
		  const book_author = document.querySelectorAll(".book_author");
		  const book_summary = document.querySelectorAll(".book_summary");
		  const book_date = document.querySelectorAll(".book_date");
		  const book_isbn = document.querySelectorAll(".book_isbn");
		  const book_price = document.querySelectorAll(".book_price");
		  const book_publisher = document.querySelectorAll(".book_publisher");
		  const book_translators = document.querySelectorAll(".book_translators");

		  for (let i = 0; i < book_name.length; i++) {

		    $.ajax({
		      url: "/bookAll",
		      method: "POST",
		      data: {
		    	  book_title: book_name[i].textContent,
		    	  book_cover: book_cover[i].getAttribute("src"),
		    	  book_isbn: book_isbn[i].textContent,
		    	  book_spmt_ymd: book_date[i].textContent,
		    	  book_amt: book_price[i].textContent,
		    	  book_author: book_author[i].textContent,
			      book_content: book_summary[i].textContent,
			      pub_nm: 'Taschen',
			      book_talt: book_translators[i].textContent,
			      ccg_b001: '종이책',
			      ccg_b002: '해외도서',
			      ccg_b003: '예술/건축'
		      },
		      success: function(response) {
		        // 성공적으로 요청을 보냈을 때의 처리
		        console.log("요청이 성공적으로 전송되었습니다.");
		      },
		      error: function(xhr, status, error) {
		        // 요청을 보내는 도중에 오류가 발생한 경우의 처리
		        console.error("오류가 발생했습니다:", error);
		      }
		    });
		  }
		});
});
function deleteDiv(di){
	var parentDiv = di.parentNode;
	parentDiv.remove();
}


function nextPage(){
	num += 1;
	bookBtn.click();
}

function beforePage(){
	num -= 1;
	bookBtn.click();
}
</script>

</html>