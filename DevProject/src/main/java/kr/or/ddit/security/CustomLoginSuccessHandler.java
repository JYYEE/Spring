package kr.or.ddit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private static final Logger log = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);
	
	// 로그인 성공했을 때 받아줄 메소드
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		log.info("[로그인 성공!] onAuthenticationSuccess() 실행...!");
		User customUser = (User) authentication.getPrincipal();
		
		log.info("username : " + customUser.getUsername());
		log.info("password : " + customUser.getPassword());
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
