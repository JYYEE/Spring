package kr.or.ddit.controller.form.validation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/formtag/validation")
@Slf4j
public class JSPFormValidationController {
	/*
	 * 8장. 스프링 폼 태그
	 * 
	 * 15. 입력값 검증 에러
	 * - 입력값 검증 에러를 출력하려면 <form:errors> 요소를 사용한다. 
	 * 
	 * ## 폼태그를 사용하는 이유 ##
	 * - 에러처리 편하게
	 * - 데이터 바인딩 편하게
	 * - 하지만, form:errors 에러 처리 안됨 -> 에러를 던져줄 수 있는 라이브러리가 필요
	 * - 자바빈즈 객체 안에다 앞으로 정해놓을 규칙을 설정 => validationController
	 */
	
	@RequestMapping(value = "/registerFormValidation01", method = RequestMethod.GET)
	public String registerFormValidation01(Model model) {
		log.info("registerFormValidation01() 실행...!");
		model.addAttribute("member", new Member());
		return "form/validation/registerFormValidation01";
	}

	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String registerFormValidationResult(Member member) {
		log.info("registerFormValidationResult() 실행...!");
		log.info("userId : " + member.getUserId());
		log.info("userName : " + member.getUserName());
		log.info("email : " + member.getEmail());
		return "form/validation/result";
	}
	
}
