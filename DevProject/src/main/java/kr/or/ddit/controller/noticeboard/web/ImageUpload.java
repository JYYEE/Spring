package kr.or.ddit.controller.noticeboard.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class ImageUpload {
	@RequestMapping(value = "/imageUpload.do")
	public String imageUpload(
			HttpServletRequest req, // 서버 경로 설정
			HttpServletResponse resp,  // 응답으로 내보낼 때 mime 타입 설정. 
			MultipartHttpServletRequest multiFile // 파일 이미지를 받기 위한 파라미터
			) throws Exception {
		JsonObject json = new JsonObject();
		PrintWriter printWriter = null;
		OutputStream out = null;
		long limitSize = 1024 * 1024 * 2;	// 2MB로 제한
		MultipartFile file = multiFile.getFile("upload"); // ckeditor로 업로드 하면 upload라는 이름으로 넘어옴.
		
		if(file != null && file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) { // 파일 존재
			if(file.getContentType().toLowerCase().startsWith("image/")) { // image 파일 일 때(미디어 타입이 image)
				if(file.getSize() > limitSize) { // limitsize보다 클 때 -> 업로드 불가
					JsonObject jsonMsg = new JsonObject();
					JsonArray jsonArr = new JsonArray();
					// ckeditor가 인식하기 위해선 json의 객체로 담아서 전달해야함 (규칙) 
					jsonMsg.addProperty("message", "2MB미만의 이미지만 업로드 가능합니다!"); // 원하는 메세지를 출력할 때 key를 message로 설정(규칙)
					jsonArr.add(jsonMsg);
					json.addProperty("uploaded", 0); // uploaded 실패시 flag값으로 0으로 보내야함. (규칙)
					json.add("error", jsonArr.get(0)); // json 객체를 value로 넣을 때 사용.
					
					resp.setCharacterEncoding("UTF-8");
					printWriter = resp.getWriter();
					printWriter.println(json);
				} else {
					try {
						String fileName = file.getName();
						byte[] bytes = file.getBytes();
						String uploadPath = req.getServletContext().getRealPath("/resources/img");// 업로드 경로 (서버)
						
						File uploadFile = new File(uploadPath);
						if(!uploadFile.exists()) {
							uploadFile.mkdirs();	// 존재하지 않는 경우 폴더 구조를 만들어 준다.
						}
						fileName = UUID.randomUUID().toString();	// UUID를 활용하여 랜덤으로 발생되어 만들어진 문자열값을 저장한다. 
						uploadPath = uploadPath + "/" + fileName + ".jpg";	//  /resources/img/UUID.jpg
						
						out = new FileOutputStream(new File(uploadPath));
						out.write(bytes); 
						
						// 응답으로 내보내기 위한 준비
						printWriter = resp.getWriter();
						resp.setContentType("text/html");
						String fileUrl = req.getContextPath() + "/resources/img/" + fileName + ".jpg";
						
						// ckeditor가 이미지 업로드 성공할 때 3가지 속성은 반드시 보내줘야함. 
						json.addProperty("uploaded", 1); // 업로드 성공시 flag값
						json.addProperty("fileName", fileName);
						json.addProperty("url", fileUrl);
						
						printWriter.println(json);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if(out!= null) {out.close();}
						if(printWriter!= null) {printWriter.close();}
					}
					
				}
			}
		}
		return null;
	}
}
