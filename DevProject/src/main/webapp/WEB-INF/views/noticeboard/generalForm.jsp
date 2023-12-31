<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="/resources/ckeditor/ckeditor.js"></script>
<section class="content">
	<div class="container-fluid" >
		<div class="row" style="justify-content: center;">
			<div class="col-md-6">	
				<div class="card card-primary">
					<div class="card-header">
						<h3 class="card-title">공지사항 등록</h3>
					</div>
					<!-- 
						파일 업로드
						1) method는 꼭 post!
						2) enctype = "multipart/form-data"
						3) <input type="file" name="boFile">
					 -->
					<!-- 
						요청 URI : /notice/generalFormPost
						요청 파라미터 : post
						요청 파라미터 : {boTitle=제목입니다, boContent=내용이지요, boWriter=개똥이, boFile=파일객체}
					 -->
					<form id="frm" name="frm" action="/notice/generalFormPost" method="post" enctype="multipart/form-data">
						<div class="card-body">
							<div class="form-group">
								<label for="boTitle">제목</label> 
								<input type="text" class="form-control" id="boTitle" name="boTitle" placeholder="제목을 입력해주세요." required="required">
							</div>
							<div class="form-group">
								<label for="boContent">내용</label> 
								<textarea rows="10" cols="20" class="form-control" id="boContent" name="boContent" placeholder="내용을 입력해주세요."></textarea>
							</div>
							<div class="form-group">
								<label for="boWriter">작성자</label> 
								<input type="text" class="form-control" id="boWriter" name="boWriter" placeholder="작성자를 입력해주세요." required="required">
							</div>
							<div class="form-group">
								<label for="boFile">File input</label>
								<div class="input-group">
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="boFile" name="boFile" multiple="multiple"> 
										<label class="custom-file-label" for="boFile">Choose file</label>
									</div>
								</div>
							</div>
						</div>
				
						<div class="card-footer">
							<button type="submit" class="btn btn-primary">등록</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<script>
$(function(){
	CKEDITOR.replace("boContent");
})	
</script>