package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/redirectattribute")
@Slf4j
public class RedirectAttributeMemberController {

	/*
	 * 4. RedirectAttribute 타입
	 * - RedirectAttribute는 일회성으로 데이터를 전달하는 용도로 사용한다.
	 */
	@RequestMapping(value = "/registerForm", method = RequestMethod.GET)
	public String registerForm() {
		log.info("registerForm() 실행...!");
		return "member/registerRedirectAttributeForm";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Member member, RedirectAttributes redirectattribute) {
		log.info("register() 실행...!");
		redirectattribute.addFlashAttribute("msg", "success");
		// 값이 맨 처음에는 출력되었지만 리로드 하면 사라짐.
		// redirect로 요청이 새롭게 만들어지면서 기존 요청내용은 사라짐.
		// 일회성 메세지를 표시할 때/최초 알림
		return "redirect:/redirectattribute/result";
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result() {
		log.info("result() 실행...!");
		return "result";
	}
}
