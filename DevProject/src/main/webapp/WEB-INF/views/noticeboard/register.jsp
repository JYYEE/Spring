<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="register-box">
	<div class="card card-outline card-primary">
		<div class="card-header text-center">
			<a href="" class="h1"><b>DDIT</b>BOARD</a>
		</div>
		<div class="card-body">
			<p class="login-box-msg">회원가입</p>
			<!-- 시큐리티에서 <sec:csrfInput/> 보내야 하는데 파일데이터 보낼 때는 토큰값을 다음과 같이 파라미터로 넘기기 -->
			<form action="/notice/signup.do?${_csrf.parameterName }=${_csrf.token}" method="post" id="signupForm" enctype="multipart/form-data">
				<div class="input-group mb-3 text-center">
					<img class="profile-user-img img-fluid img-circle" id="profileImg"
						src="/resources/dist/img/AdminLTELogo.png" alt="User profile picture"
						style="width: 150px;">
				</div>
				<div class="input-group mb-3">
					<label for="inputDescription">프로필 이미지</label> 
				</div>
				<div class="input-group mb-3">
					<div class="custom-file">
						<input type="file" class="custom-file-input" name="imgFile" id="imgFile"> 
						<label class="custom-file-label" for="imgFile">프로필 이미지를 선택해주세요</label>
					</div>
				</div>
				<div class="input-group mb-3">
					<label for="inputDescription">프로필 정보</label> 
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memId" name="memId" value="${member.memId }" placeholder="아이디를 입력해주세요"> 
					<span class="input-group-append">
						<button type="button" class="btn btn-secondary btn-flat" id="idCheckBtn">중복확인</button>
					</span>
					<span class="error invalid-feedback" style="display:block;">${errors.memId }</span>
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memPw" name="memPw" placeholder="비밀번호를 입력해주세요">
					<span class="error invalid-feedback" style="display:block;">${errors.memPw }</span>
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memName" name="memName" value="${member.memName }" placeholder="이름을 입력해주세요">
					<span class="error invalid-feedback" style="display:block;">${errors.memName }</span>
				</div>
				<div class="input-group mb-3">
					<div class="form-group clearfix">
						<div class="icheck-primary d-inline">
							<input type="radio" id="memGenderM" name="memGender" value="M" checked="checked">
							<label for="memGenderM">남자</label>
						</div>
						<div class="icheck-primary d-inline">
							<input type="radio" id="memGenderF" name="memGender" value="F">
							<label for="memGenderF">여자</label>
						</div>
					</div>
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memEmail" name="memEmail" value="${member.memEmail }" placeholder="이메일을 입력해주세요">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memPhone" name="memPhone" value="${member.memPhone }" placeholder="전화번호를 입력해주세요">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memPostCode" name="memPostCode"> 
					<span class="input-group-append">
						<button type="button" onclick="DaumPostcode1()" class="btn btn-secondary btn-flat">우편번호 찾기</button>
					</span>
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memAddress1" name="memAddress1" placeholder="주소를 입력해주세요">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memAddress2" name="memAddress2" placeholder="상세주소를 입력해주세요">
				</div>
				<div class="row">
					<div class="col-8">
						<div class="icheck-primary">
							<input type="checkbox" id="memAgree" name="memAgree" value="Y">
							<label for="memAgree"> 개인정보 사용을 동의해주세요 <a href="#">개인정보방침</a></label>
						</div>
					</div>
					<div class="col-4">
						<button type="button" class="btn btn-primary btn-block" id="signupBtn">가입하기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<br/>
	<div class="card card-outline card-secondary">
		<div class="card-header text-center">
			<h4>MAIN MENU</h4>
			<button type="button" class="btn btn-secondary btn-block" onclick="javascript:location.href='/notice/login.do'">뒤로가기</button>
		</div>
	</div>
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function(){
	var imgFile = $("#imgFile");		// 프로필 이미지 선택 Elememt(이미지 선택 파일)
	var idCheckBtn = $("#idCheckBtn");	// 아이디 중복확인 버튼
	var idCheckFlag = false;			// 아이디 중복 확인 Flag 값(default : false, 중복확인 진행하지 않음.)
	var signupBtn = $("#signupBtn");	// 가입하기 버튼
	var signupForm = $('#signupForm');	// 가입 Form
	
	// 프로필 사진 선택
	imgFile.on("change", function(event){
		console.log(event);
		var file = event.target.files[0];
		
		if(isImageFile(file)){	// 이미지 파일 선택
			var reader = new FileReader();
			reader.onload = function(e){
				$("#profileImg").attr("src", e.target.result);
			}
			reader.readAsDataURL(file);
		} else {	// 이미지 파일을 선택하지 않음.
			alert("이미지 파일을 선택해주세요!");
		}
	});
	
	// 아이디 중복 체크
	// 비동기처리에서도  스프링 시큐리티 설정 필요. token을 보내야하므로 모든 ajax에 모두 적용 
	idCheckBtn.on("click", function(){
		var id = $('#memId').val();
		if(id == null || id == ""){
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		$.ajax({
			type : "post",
			url : "/notice/idCheck.do",
			beforeSend : function(xhr){
				xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
			},
			data : {memId : id},
			success : function(res){
				console.log("아이디 중복확인 이벤트 결과 : " + res);
				// 결과는 EXIST, NOTEXIST로 출력
				if(res == "NOTEXIST"){	// 아이디 사용 가능
					alert("사용 가능한 아이디입니다!");
					$("#id").html("사용가능한 아이디 입니다!").css("color", "green");
					idCheckFlag = true;	// 중복 확인했다는 flag 설정
				} else { // 아이디 중복
					alert("이미 사용중인 아이디입니다!");
					$("#id").html("이미 사용중인 아이디입니다!");
				}
				
				
			}
		})
	});
	
	signupBtn.on("click", function(){
		var memId = $('#memId').val();		// 아이디
		var memPw = $('#memPw').val();		// 비밀번호
		var memName = $('#memName').val();	// 이름
		var agreeFlag = false;				// 개인정보 동의 flag
		
		if(memId == null || memId == ""){
			alert("아이디를 입력해주세요.");
			return false;
		}
		if(memPw == null || memPw == ""){
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		if(memName == null || memName == ""){
			alert("이름을 입력해주세요.");
			return false;
		}
		
		var memAgree = $('#memAgree:checked').val();
		if(memAgree == "Y"){
			agreeFlag = true;
		}
		
		// 개인정보 처리방침을 동의했습니까?
		// 아이디 중복 체크를 하고 오셨습니까?
		// 오셨다구요? 그럼 가입하기를 진행할게요!
		// 예? 안했다구요? 그럼 가입하기를 할 수 없어요.
		if(agreeFlag){	// 개인정보 처리방침 동의
			if(idCheckFlag){	// 중복확인 OK
				signupForm.submit();
			} else {
				alert("아이디 중복 체크를 진행해주세요!");
			}
		} else {
			alert("개인 정보 동의를 체크해주세요!");
		}
	});
	
	
});

// 이미지 파일인지 체크(확장자를 이용)
function isImageFile(file){
	var ext = file.name.split(".").pop().toLowerCase();		// 파일명에서 확장자를 가져온다.
	return ($.inArray(ext, ["jpg", "jpeg", "gif", "png"]) === -1) ? false : true;
}

// 카카오 도로명 주소 함수1(지번 선택)
function DaumPostcode1() {
	new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("memPostCode").value = data.zonecode;
            document.getElementById("memAddress1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("memAddress2").focus();
            }
		}
    }).open();
};

// 카카오 도로명 주소 함수2(도로명)
function DaumPostcode2() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다. (내가 설정한 요소의 id로 지정)
            document.getElementById('memPostCode').value = data.zonecode;
            document.getElementById("memAddress1").value = roadAddr;
            document.getElementById("memAddress2").focus();
        }  
    }).open();
}

</script>
