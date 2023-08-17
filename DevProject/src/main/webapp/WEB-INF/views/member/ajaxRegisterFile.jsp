<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax Register File</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h1>10. 파일 업로드 Ajax 방식 요청 처리</h1>
	<hr/>
	
	<p>Ajax 방식으로 전달한 파일 요소값을 스프링MVC가 지원하는 MultipartFile 매개변수로 처리한다.</p>
	<div>
		<input type="file" id="inputFile"/><br/>
		<hr/>
		<img id="preview"/>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$('#inputFile').on("change", function(event){
		console.log("change event...!");
		//console.log(event)
		/*
			event.target.files 결과값
			files: FileList
			0 : File {name: '11.png', lastModified: 1684374121734, lastModifiedDate: Thu May 18 2023 10:42:01 GMT+0900 (한국 표준시), webkitRelativePath: '', size: 127652, …}
			length: 1
			[[Prototype]] : FileList
		*/
		var files = event.target.files;
		var file = files[0];
		
		//console.log(file);
		if(isImageFile(file)){ // 이미지 파일 일 때만 썸네일 생성하고자 이미지임을 확인
			// 비동기 처리시, 파일 데이터를 전송할 때에는 무조건!!! FormData()를 이용하여 데이터를 전송한다
			var formData = new FormData();
			formData.append("file", file);
			
			// formData 는 key/value 형식으로 데이터가 저장된다.
			// 일반데이터를 보낼 때는 formData에 append 로 해서 키, value로 저장하기.
			// dataType : 응답(response) 데이터를 내보낼 때 보내줄 데이터 타입
			// processData : 데이터 파라미터를 data라는 속성으로 넣는데, jquery 내부적으로 쿼리 스트링을 구성합니다. 
			// 				파일 전송의 경우 쿼리 스트링을 사용하지 않으므로 해당 설정을 false로 비활성화한다.
			// contentType : Content-Type설정시 , 사용하는데 해당 설정의 기본값은
			// 					'application/x-www-form-urlencoded; charset=utf-8'입니다.
			// 				하여, 기본값으로 나가지않고 'multipart/form-data'로 나갈 수 있도록 설정을 false합니다.
			// 				request요청에서 Content-Type을 확인해보면 'multipart/form-data; boundar=====WebkitFormBoundary[HashKey]'
			// 				와 같은 값으로 전송된 것을 확인할 수 있습니다.
			// 파일 데이터 보낼 때, processData, contentType false로 무조건!!! 설정해주기
			
			$.ajax({
				type : "post",
				url : "/ajax/uploadAjax",
				data : formData,
				dataType : "text",
				processData : false,
				contentType : false,
				success : function(data){
					alert(data);
					if(data === "UPLOAD SUCCESS"){
						var file = event.target.files[0]; 
						// ajax는 비동기라 시점이 일정하지 않아서 앞에서 변수에 선언한 파일을 가져오면 제대로 읽어오지 못할 수 있음. 
						//처음에 발생한 event에서 파일을 꺼내옴
						var reader = new FileReader();
						reader.onload = function(e){
							console.log(e)
							/*
								e.target.result 결과값
								data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAtQAAAFDCAYAAAAappmWAAA
								이미지를 base64로 코드화 시킨 내용
							*/
							$("#preview").attr("src", e.target.result);
						}
						reader.readAsDataURL(file);
						 
					}
				}
				
			})
		} else { 				// 이미지 파일이 아닐 때
			alert("이미지를 넣으라구요!!");
		}
	});
});

function isImageFile(file){
	var ext = file.name.split(".").pop().toLowerCase();	// 파일명에서 확장자명을 가져온다. pop은 마지막요소를 꺼냄
	// 확장자 중 이미지에 해당하는 확장자가 아닌 경우 포함되어 있는 문자가 없으니까 -1(false)가 리턴
	// 확장자 중 이미지 확장자가 포함되어 있다면 0보다 큰 수 일테니 true가 리턴
	return ($.inArray(ext, ["jpg", "jpeg", "gif", "png"]) === -1) ? false : true; // inArray(비교할 대상, 배열) 
}
</script>
</html>