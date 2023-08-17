package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.MemberVO;

public interface IMemberService {
	public ServiceResult insertMember(MemberVO memberVO);
	public MemberVO loginCheck(MemberVO memberVO);
	public List<MemberVO> selectMember();
}
