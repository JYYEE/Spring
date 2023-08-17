<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" data-scroll="true">
      <div class="container-fluid py-1 px-3">
        <div id="alarmZone" class="md-col-6"></div>
        <div class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4" id="navbar">
          <div class="ms-md-auto pe-md-3 d-flex align-items-center">
          </div>
          <ul class="navbar-nav  justify-content-end">
          <c:if test="${member == null }">
            <li class="nav-item d-flex align-items-center">
              <a href="/member/signin.do" class="nav-link text-body font-weight-bold px-0">
                <i class="fa fa-user me-sm-1"></i>
                <span class="d-sm-inline d-none">로그인</span>
              </a>
            </li>
            </c:if>
         <li class="nav-item d-flex align-items-center">　</li>
         <li class="nav-item">
           <div class="d-flex align-items-center justify-content-between">
            <div class="avatar-group mt-2 avatar avatar-xs rounded-circle">
              <img alt="Image placeholder" src="../resources/img/team-1.jpg" style="width:40px;">
            </div>
           </div>
         </li>
         <li class="nav-item d-flex align-items-center">　</li>
         <li class="nav-item d-flex align-items-center">
            <div class="d-flex flex-column justify-content-center">
              <h6 class="mb-0 text-sm">${member.memId }</h6>
              <p class="text-xs text-secondary mb-0">${member.memEmail }</p>
            </div>
         </li>
         <li class="nav-item d-flex align-items-center">　</li>
         <c:if test="${member != null }">
         <li class="nav-item d-flex align-items-center">
                <a  class="nav-link text-body font-weight-bold px-0">
                    <img id="bell" src="<c:url value='/resources/img/bell-normal.png'/>" style="width:60px; height:30px;" />
               </a>
               <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right show" id="alarm" style="left: inherit; right: 0px; top: 80%; display: none;">
               <span class="dropdown-item dropdown-header" id="noti"></span>
               <div class="dropdown-divider"></div>
               <div class="dropdown-divider"></div>
               <a href="#" id="allRead" class="dropdown-item dropdown-footer">모두 읽기</a>
            </div>
            </li>
         <li class="nav-item d-flex align-items-center">
              <a href="/member/logout.do" class="nav-link text-body font-weight-bold px-0">
                <i class="fa fa-user me-sm-1"></i>
                <span class="d-sm-inline d-none">로그아웃</span>
              </a>
            </li>
            </c:if>
          </ul>
        </div>
      </div>
    </nav>
<script>
$(function(){
	var bell = $("#bell");
	var alarm = $("#alarm");
	var allRead = $("#allRead");
	let memId = "${sessionScope.member.memId}";
	let memNo = "${sessionScope.member.memNo}";   
	let ws = new WebSocket("ws://localhost/echo");
	let str = "";
	let alarmNo = 0;
	
   ws.onopen = function(){
      console.log("웹소켓 연결!!!!!!!!!!!!!!");
      ws.send("${sessionScope.member.memName}님이 접속했습니다. ");
      console.log(memNo);
      alarmCheck(memNo);
   };
   
   ws.onmessage = function(event){
	      console.log(event);
	      if(event.data.length >0 ){
	         if(event.data.indexOf("접속") == -1){
	            $("#noti").empty();
	            $("#noti").append(event.data);
	         } else {
	            $("#alarmZone").append(event.data);
	            
	            setTimeout(function(){
		            $("#alarmZone").empty();	            	
	            }, 3000);	
	         }
	      }
	   };
   
   ws.onclose = function(){
      console.log("웹소켓 끊김!!!!!!!!!!!!");
   };
   ws.onerror = function(err){
      console.log("error : ", err)
   }
   
   function alarmCheck(memNo){
      $.ajax({
         type : "post",
         url : "/board/alarmCheck.do",
         data : { memNo : memNo},
         success : function(res){
            console.log(res);
            ac = " ";
            $.each(res, function(i, v){
               
               type = v.alarmType; 
               url = v.alarmUrl;
               bono = v.alarmUrl.split("=")[1];
               alarmNo = v.alarmNo; 
               
               ac += "<a href='"+url+"' id='alarmCheck' class='dropdown-item' data-alarmNo='"+alarmNo+"'>"
               ac += "<span class='float-right text-muted text-sm'><i class='fas fa-file mr-2'>&nbsp;&nbsp;</i>"+bono +"번 " + type +"이 등록되었습니다.&nbsp;&nbsp;&nbsp;바로가기</span></a>"
               
               console.log(ac);
            })
            ws.send(ac);
         }
      })
   }
	
	$('.dropdown-item').on('click',function(){
		statChange(alarmNo);
	});
	
	function statChange(alarmNo){
		console.log(alarmNo);
		$.ajax({
			type : "post",
			url : "/board/statChange.do",
			data : {alarmNo : alarmNo},
			success : function(res){
				console.log("statChange"+res);

			}
		})
	}
	
	
	setInterval(function () {
		alarmCheck(memNo);
		var count = $(".dropdown-item span").length;
		if(count == 0){
			$("#bell").attr("src","<c:url value='/resources/img/bell-normal.png' />");
		}else{
			$("#bell").attr("src","<c:url value='/resources/img/bell-alarm.png' />");
		}
	}, 1000);
	
	bell.on("click", function() {
		if($("#alarm").css("display") == "none"){
			$("#alarm").css("display","");
		}else{
			$("#alarm").css("display","none");
		}
	});
	
	allRead.on("click", function() {
		$.ajax({
			type : "post",
			url : "/board/allReadUpdate.do",
			data : {memNo : memNo},
			success : function(res){
				bell.click();
			}
		})
	});
})
</script>