<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
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
  <style type="text/css">
  	#replyBtn {
		margin-bottom: 30px;
		margin-left: 5px;
	}
  </style>
</head>

<body class="g-sidenav-show  bg-gray-200">
  <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3   bg-gradient-dark" id="sidenav-main">
    <div class="sidenav-header">
      <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
      <a class="navbar-brand m-0" href="" target="_blank">
        <img src="../resources/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo">
        <span class="ms-1 font-weight-bold text-white">대덕인재개발원</span>
      </a>
    </div>
    <hr class="horizontal light mt-0 mb-2">
    <div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link text-white active bg-gradient-primary" href="../pages/tables.html">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">table_view</i>
            </div>
            <span class="nav-link-text ms-1">공지사항</span>
          </a>
        </li>
      </ul>
    </div>
  </aside>
  <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
    <!-- Navbar -->
    <%@include file = "/WEB-INF/views/header.jsp" %>
    <!-- End Navbar -->
    <div class="container-fluid px-2 px-md-4">
      <div class="page-header min-height-300 border-radius-xl mt-4" style="background-image: url('https://images.unsplash.com/photo-1531512073830-ba890ca4eba2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1920&q=80');">
        <span class="mask  bg-gradient-secondary opacity-6"></span>
      </div>
      <div class="card card-body mx-3 mx-md-4 mt-n6">
        <div class="row gx-4 mb-2">
		  <div class="col-md-8">
		    <div class="h-100">
              <h5 class="mb-1">${board.boTitle }</h5>
              <p class="mb-0 font-weight-normal text-sm">
                ${board.boWriter } / ${board.boDate } / ${board.boHit }
              </p>
            </div>
		  </div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="card card-plain h-100">
					<div class="card-header pb-0 p-3">
						<h6 class="mb-0">내용</h6>
					</div>
					<div class="card-body p-3">
						${board.boContent }
					</div>
					<hr/>
					<div class="card-footer p-3">
						<button type="button" class="btn btn-outline-primary" id="delBtn">삭제</button>
						<button type="button" class="btn btn-outline-secondary" id="updateBtn">수정</button>
						<button type="button" class="btn btn-outline-success" id="listBtn">목록</button>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-12">
			<form action="/reply/replyInsert.do" method="post" id="replyForm">
				<input type="hidden" name="boNo" value="${board.boNo }"/>
				<div class="col-md-10">
					<textarea rows="3" cols="80" id="replyContent" name="replyContent"></textarea>
					<button type="submit" class="btn btn-primary" id="replyBtn">댓글 등록</button>
				</div>
			</form>
			<hr/>
			<div id="replyZone"></div>
		</div>
		<c:forEach items="${reply }" var="reply">
			<li>
				<div>
					<p>${member.memNo } / ${reply.replyDate }</p>
					<p>${reply.replyContent }</p>
				</div>
			</li>
		</c:forEach>
		<form action="/board/update.do" method="get" id="nFrm">
			<input type="hidden" name="boNo" value="${board.boNo }">
		</form>
	  </div>
    </div>
  </main>
  <div class="fixed-plugin">
    <a class="fixed-plugin-button text-dark position-fixed px-3 py-2">
      <i class="material-icons py-2">settings</i>
    </a>
    <div class="card shadow-lg">
      <div class="card-header pb-0 pt-3">
        <div class="float-start">
          <h5 class="mt-3 mb-0">Material UI Configurator</h5>
          <p>See our dashboard options.</p>
        </div>
        <div class="float-end mt-4">
          <button class="btn btn-link text-dark p-0 fixed-plugin-close-button">
            <i class="material-icons">clear</i>
          </button>
        </div>
        <!-- End Toggle Button -->
      </div>
      <hr class="horizontal dark my-1">
      <div class="card-body pt-sm-3 pt-0">
        <!-- Sidebar Backgrounds -->
        <div>
          <h6 class="mb-0">Sidebar Colors</h6>
        </div>
        <a href="javascript:void(0)" class="switch-trigger background-color">
          <div class="badge-colors my-2 text-start">
            <span class="badge filter bg-gradient-primary active" data-color="primary" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-dark" data-color="dark" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-info" data-color="info" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-success" data-color="success" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-warning" data-color="warning" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-danger" data-color="danger" onclick="sidebarColor(this)"></span>
          </div>
        </a>
        <hr class="horizontal dark my-3">
        <div class="mt-2 d-flex">
          <h6 class="mb-0">Light / Dark</h6>
          <div class="form-check form-switch ps-0 ms-auto my-auto">
            <input class="form-check-input mt-1 ms-auto" type="checkbox" id="dark-version" onclick="darkMode(this)">
          </div>
        </div>
        <hr class="horizontal dark my-sm-4">
      </div>
    </div>
  </div>
  <!--   Core JS Files   -->
  <script src="../resources/js/core/popper.min.js"></script>
  <script src="../resources/js/core/bootstrap.min.js"></script>
  <script src="../resources/js/plugins/perfect-scrollbar.min.js"></script>
  <script src="../resources/js/plugins/smooth-scrollbar.min.js"></script>
  <script>
    var win = navigator.platform.indexOf('Win') > -1;
    if (win && document.querySelector('#sidenav-scrollbar')) {
      var options = {
        damping: '0.5'
      }
      Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
    }
  </script>
  <!-- Github buttons -->
  <script async defer src="https://buttons.github.io/buttons.js"></script>
  <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
</body>
<script type="text/javascript">
$(function() {
	var listBtn = $("#listBtn");
	var updateBtn = $("#updateBtn");
	var delBtn = $("#delBtn");
	var nFrm = $("#nFrm");
	var replyBtn = $("#replyBtn");
	var replyForm = $("#replyForm");
	var replyContent = $("#replyContent");
	var boNo = "${board.boNo}";
	replyList(boNo);
	listBtn.on("click", function() {
		location.href = "/board/list.do";
	});
	
	updateBtn.on("click", function() {
		nFrm.submit();
	});
	
	delBtn.on("click", function() {
		if(confirm("정말 삭제하시겠습니까?")){
			nFrm.attr("method", "post");
			nFrm.attr("action", "/board/delete.do");
			nFrm.submit();
		}else{
			nFrm.reset();
		}
	});
	
	replyBtn.on("click", function() {
		var boNo = "${board.boNo}";
		var replyContent = $("#replyContent").val().trim();
		
		if(replyContent == ""){
			alert("내용을 입력해주세요!");
			$("#replyContent").focus();
			return false;
		}
		replyForm.submit();
	});
	
	function replyList(boNo){
 		$.ajax({
 			type : "post",
 			url : "/reply/replyList.do",
 			data : { boNo : boNo},
 			success : function(res){
 				console.log(res);
 				$.each(res, function(i, v){
 					console.log(v);
 					reply = "";
 					memNo = v.memNo;
 					date = v.replyDate;
 					content = v.replyContent;
 					
 					reply += "<p>" + memNo + "/" + date + "<p>"
 					reply += "<p>" + content + "<p>"
 					$("#replyZone").append(reply);
 				})
 			}
 		})
 	}
});
</script>
</html>