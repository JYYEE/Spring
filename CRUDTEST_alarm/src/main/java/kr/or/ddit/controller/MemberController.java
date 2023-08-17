package kr.or.ddit.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
	
	@Inject
	private IMemberService memberService;
	
	@RequestMapping(value ={"", "/signin.do"}, method = RequestMethod.GET)
	public String signinForm() {
		return "signin";
	}
	
	@RequestMapping(value = "/signup.do", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}
	
	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public String signup(MemberVO memberVO, Model model, RedirectAttributes redirectAttribute) {
		log.info("memberVO.getMemId : " + memberVO.getMemId());
		log.info("memberVO.getMemPw : " + memberVO.getMemPw());
		log.info("memberVO.getMemName : " + memberVO.getMemName());
		log.info("memberVO.getMemGender : " + memberVO.getMemGender());
		log.info("memberVO.getMemPhone : " + memberVO.getMemPhone());
		log.info("memberVO.getMemEmail : " + memberVO.getMemEmail());
		log.info("memberVO.getMemAgree : " + memberVO.getMemAgree());
		
		String goPage = "";
		if(StringUtils.isAnyBlank(memberVO.getMemId(), memberVO.getMemPw(), memberVO.getMemName(), memberVO.getMemGender(), memberVO.getMemPhone(), memberVO.getMemEmail(), memberVO.getMemAgree())) {
			redirectAttribute.addFlashAttribute("msg", "누락된 입력 정보가 존재합니다.");
			goPage = "redirect:/member/signup.do";
		} else {
			ServiceResult result = memberService.insertMember(memberVO);
			if(result.equals(ServiceResult.OK)) {
				redirectAttribute.addFlashAttribute("msg", "가입이 완료되었습니다!");
				goPage = "redirect:/member/signin.do";
			}else {
				redirectAttribute.addFlashAttribute("msg", "가입이 실패했습니다. 다시 시도해주세요.");
				goPage = "redirect:/member/signup.do";
			}
		}
		return goPage;
	}
	
	@RequestMapping(value ="/signin.do", method = RequestMethod.POST)
	public String signin(MemberVO memberVO, Model model, HttpSession session, RedirectAttributes redirectAttribute) {
		log.info("memberVO.getMemId : " + memberVO.getMemId());
		log.info("memberVO.getMemPw : " + memberVO.getMemPw());
		
		String goPage = "";
		if(StringUtils.isAnyBlank(memberVO.getMemId(), memberVO.getMemPw())) {
			redirectAttribute.addFlashAttribute("msg", "누락된 입력 정보가 존재합니다.");
			goPage = "redirect:/member/signin.do";
		}else {
			MemberVO result = memberService.loginCheck(memberVO);
			if(result == null) {
				redirectAttribute.addFlashAttribute("msg", "존재하지 않는 회원입니다.");
				goPage = "redirect:/member/signin.do";
			}else {
				session.setAttribute("member", result);
				session.setAttribute("id", result.getMemNo() + "");
				goPage = "redirect:/board/list.do";
			}
		}
		return goPage;
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "signin";
	}
	
	@RequestMapping(value = "/alarm", method = RequestMethod.GET)
	public String alarm() {
		return "alarm";
	}
}
