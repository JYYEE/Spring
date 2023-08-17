<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="apple-touch-icon" sizes="76x76" href="../resources/img/apple-icon.png">
  <link rel="icon" type="image/png" href="../resources/img/favicon.png">
  <title>
    대덕인재개발원 CRUD 연습
  </title>
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
  <!-- Nucleo Icons -->
  <link href="../resources/css/nucleo-icons.css" rel="stylesheet" />
  <link href="../resources/css/nucleo-svg.css" rel="stylesheet" />
  <!-- Font Awesome Icons -->
  <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
  <!-- Material Icons -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
  <!-- CSS Files -->
  <link id="pagestyle" href="../resources/css/material-dashboard.css?v=3.0.4" rel="stylesheet" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>

<body class="bg-gray-200">
  <main class="main-content  mt-0">
    <div class="page-header align-items-start min-vh-100" style="background-image: url('https://images.unsplash.com/photo-1497294815431-9365093b7331?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80');">
      <span class="mask bg-gradient-dark opacity-6"></span>
      <div class="container my-auto">
        <div class="row">
          <div class="col-lg-4 col-md-8 col-12 mx-auto">
            <div class="card z-index-0 fadeIn3 fadeInBottom">
              <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                <div class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1">
                  <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">로그인</h4>
                </div>
              </div>
              <div class="card-body">
                <form role="form" method="post" action="/member/signin.do" id="signinForm" class="text-start">
                  <div class="input-group input-group-outline my-3">
                    <input type="email" id="id" name="memId" class="form-control" placeholder="아이디">
                  </div>
                  <div class="input-group input-group-outline mb-3">
                    <input type="password" id="pw" name="memPw" class="form-control" placeholder="비밀번호">
                  </div>
                  <div class="text-center">
                    <button type="button" id="formBtn" class="btn bg-gradient-primary w-100 my-4 mb-2">로그인</button>
                  </div>
                  <p class="mt-4 text-sm text-center">
                    아직 회원이 아니세요?
                    <a href="/member/signup.do" class="text-primary text-gradient font-weight-bold">회원가입</a>
                  </p>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
  <!--   Core JS Files   -->
  <script src="../resources/js/core/bootstrap.min.js"></script>
</body>
<script type="text/javascript">
$(function(){
	var msg = '${msg}';
	if(msg != ""){
		alert(msg);
	}
	
	var formBtn = $("#formBtn");
	
	formBtn.on("click", function() {
		var id = $("#id").val().trim();
		var pw = $("#pw").val().trim();
		
		if(id == ""){
			alert("아이디를 입력해주세요!");
			$("#id").focus();
			return false;
		}
		
		if(pw == ""){
			alert("비밀번호를 입력해주세요!");
			$("#pw").focus();
			return false;
		}
		
		$("#signinForm").submit();
	});
});
</script>
</html>