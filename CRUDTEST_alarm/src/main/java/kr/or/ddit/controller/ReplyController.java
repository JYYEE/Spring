package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.IAlarmService;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.service.IReplyService;
import kr.or.ddit.vo.AlarmVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfoVO;
import kr.or.ddit.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/reply")
@Slf4j
public class ReplyController {
	
	@Inject
	private IReplyService replyService;
	
	@Inject
	private IAlarmService alarmService;
	
	@ResponseBody
	@RequestMapping(value ="/replyList.do", method = RequestMethod.POST)
	public List<ReplyVO> list(int boNo, Model model) {
		List<ReplyVO> replyList = replyService.selectReply(boNo);
		return replyList;
	}
	
	@RequestMapping(value ="/replyInsert.do", method = RequestMethod.POST)
	public String replyInsert(ReplyVO replyVO, Model model, HttpSession session) {
		log.info("replyVO.getmemno : " + replyVO.getMemNo());
		log.info("replyVO.getbono : " + replyVO.getBoNo());
		log.info("replyVO.getReplyContent : " + replyVO.getReplyContent());
		
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
		if(StringUtils.isBlank(replyVO.getReplyContent())){
			errors.put("replyContent", "내용을 입력해주세요!");
		}
		
		if(errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("reply", replyVO);
			goPage = "form";
		}else {
			replyVO.setMemNo(memberVO.getMemNo());
			ServiceResult result = replyService.insertReply(replyVO);
			if(result.equals(ServiceResult.OK)) {
				 AlarmVO alarmVO = new AlarmVO();
		         alarmVO.setMemNo(memberVO.getMemNo());
		         alarmVO.setAlarmType("댓글");
		         alarmVO.setAlarmUrl("http://localhost/board/detail.do?boNo=" + replyVO.getBoNo());
		         alarmService.insertAlarm(alarmVO);
				goPage = "redirect:/board/detail.do?boNo=" + replyVO.getBoNo();
			} else {
				errors.put("msg", "서버에러! 다시 시도해주세요!");
				model.addAttribute("errors", errors);
				goPage = "form";
			}
		}
		return goPage;
	}

}
