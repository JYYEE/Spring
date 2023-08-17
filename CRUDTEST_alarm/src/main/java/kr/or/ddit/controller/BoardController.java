package kr.or.ddit.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.IAlarmService;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.vo.AlarmVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfoVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	
	@Inject
	private IBoardService boardService;
	
	@Inject
	private IAlarmService alarmService;
	
	@Inject
	private IMemberService memberService;
	
	@RequestMapping(value ="/list.do")
	public String list(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		
		PaginationInfoVO<BoardVO> pagingVO = new PaginationInfoVO<BoardVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = boardService.selectBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> dataList = boardService.selectBoardList(pagingVO);
		pagingVO.setDataList(dataList);
		model.addAttribute("pagingVO", pagingVO);
		
		return "list";
	}
	
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String detail(int boNo, Model model) {
		BoardVO boardVO = boardService.selectBoard(boNo);
		model.addAttribute("board", boardVO);
		return "detail";
	}
	
	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public String form() {
		return "form";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	   public String boardInsert(BoardVO boardVO, HttpSession session , Model model, RedirectAttributes redirectAttribute) {
	      String goPage ="";
	      MemberVO memberVO = (MemberVO)session.getAttribute("member");
	      boardVO.setBoWriter(memberVO.getMemId());
	      log.info("session id : " + memberVO.getMemId() );
	      log.info("title : " + boardVO.getBoTitle());
	      log.info("content : " + boardVO.getBoContent());
	      log.info("writer : " + boardVO.getBoWriter());
	      if(StringUtils.isAnyBlank(boardVO.getBoTitle(),boardVO.getBoTitle())){
	         redirectAttribute.addFlashAttribute("msg", "누락된 입력 정보가 존재합니다.");
	         redirectAttribute.addFlashAttribute("boardVO", boardVO);
	         goPage = "redirect:/form.do";
	      }
	      
	      ServiceResult result = boardService.insertBoard(boardVO);
	      if(result.equals(ServiceResult.OK)) {
	         AlarmVO alarmVO = new AlarmVO();
	         alarmVO.setMemNo(memberVO.getMemNo());
	         List<MemberVO> memberList = memberService.selectMember();
	         for (MemberVO vo : memberList) { // 전체 멤버
	            if(vo.getMemNo() != memberVO.getMemNo()) { // memberVO는 작성자
	               alarmVO.setAlarmReceiver(vo.getMemNo());	// 작성자를 제외한 모든 회원들이 발신
	               alarmVO.setAlarmType("게시글");
	               alarmVO.setAlarmUrl("http://localhost/board/detail.do?boNo=" + boardVO.getBoNo());
	               alarmService.insertAlarm(alarmVO);
	            }
	         }
	         goPage ="redirect:/board/detail.do?boNo=" + boardVO.getBoNo();
	      } else {
	         redirectAttribute.addFlashAttribute("msg", "서버 오류! 등록에 실패하였습니다.");
	         redirectAttribute.addFlashAttribute("boardVO", boardVO);
	         goPage ="redirect:/form.do";
	      }
	      return goPage;
	   }
	
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String boardUpdateForm(int boNo, Model model) {
		BoardVO boardVO = boardService.selectBoard(boNo);
		model.addAttribute("board", boardVO);
		model.addAttribute("status", "u");
		return "form";
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String boardUpdate(BoardVO boardVO, Model model) {
		String goPage = "";
		ServiceResult result = boardService.updateBoard(boardVO);
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/board/detail.do?boNo=" + boardVO.getBoNo();
		}else {
			model.addAttribute("board", boardVO);
			model.addAttribute("status", "u");
			goPage = "form";
		}
		return goPage;
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String boardDelete(int boNo, Model model) {
		String goPage = "";
		ServiceResult result = boardService.deleteBoard(boNo);
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/board/list.do";
		}else {
			goPage = "redirect:/board/detail.do?boNo="+boNo;
		}
		return goPage;
	}
	
	// 알람 갯수 확인
	@ResponseBody
	@RequestMapping(value = "/alarmCheck.do", method = RequestMethod.POST)
	public ResponseEntity<List<AlarmVO>> alarmCheck(HttpServletRequest request, Model model) {
		
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
		List<AlarmVO> alarmList = null;
		if(memberVO != null) {
			alarmList = alarmService.alarmCheck(memberVO.getMemNo());
		}
		
		return new ResponseEntity<List<AlarmVO>>(alarmList, HttpStatus.OK);
	}
	
	// 알람 상태 업데이트
	@RequestMapping(value = "/statChange.do", method = RequestMethod.POST)
	public int statChange(int alarmNo) {
		int cnt = alarmService.statChange(alarmNo);
		return cnt;
	}
	
	@ResponseBody
	@RequestMapping(value = "/allReadUpdate.do", method = RequestMethod.POST)
	public int allReadUpdate(HttpServletRequest request, Model model) {
		
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
		alarmService.allReadUpdate(memberVO.getMemNo());
		return 0;
	}
}
