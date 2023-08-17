package kr.or.ddit.controller.intercept;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessLoggingInterceptor extends HandlerInterceptorAdapter { 
	//spring 5.3 version 이상에서는 HandlerInterceptorAdapter 를 사용하는 대신 HandlerInterceptor를 implements 해서 사용하는 방식으로 바뀌었다. 
	
	PrintWriter writer;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandler...!");
		File file = new File("C:/logs/ddit_logging.log");
		writer = new PrintWriter(new FileWriter(file, true), true);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String requestURI = request.getRequestURI();
		log.info("requestURI : " + requestURI);
		
		HandlerMethod handlermethod = (HandlerMethod) handler;
		Method method = handlermethod.getMethod();
		
		Class clazz = method.getDeclaringClass();	// class kr.or.ddit.controller.BoardController와 같은 녀석
		
		String className = clazz.getName();			// kr.or.ddit.controller.BoardController와 같은 녀석
		String classSimpleName = clazz.getSimpleName();	// BoardController와 같은 녀석
		String methodName = method.getName();		// boardList 와 같은 메서드
		
		writer.printf("현재일시 : %s %n", getCurrentTime());
		writer.printf("Access Controller : %s %n", className + "." + methodName);
	}

	public String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(cal.getTime());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

}
