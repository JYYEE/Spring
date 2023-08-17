package kr.or.ddit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ajax")
@Slf4j
public class AjaxMemberFileController {
	
	@GetMapping(value = "/registerFileForm") // method방식이get이면서 경로까지 포함시켜서 나타냄.
	//@PostMapping(value = "/registerFileForm")// method방식이 post이면서 경로 포함
	//@RequestMapping(value = "/registerFileForm", method = RequestMethod.GET) // 이 방법 대신 GetMapping 이용 가능. 스타일 차이
	public String ajaxRegisterFileForm() {
		return "member/ajaxRegisterFile";
	}
	
	//produces : dataType을 설정. 응답에 대한 미디어 타입
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file){
		String originalFileName = file.getOriginalFilename();
		log.info("uploadAjax() 실행...!");
		log.info("originalFileName : " + originalFileName);
		ResponseEntity<String> entity = new ResponseEntity<String>("UPLOAD SUCCESS", HttpStatus.OK);
		return entity;
	}
}
