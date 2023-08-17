package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface IMemberDAO {
	public int insertMember(MemberVO memberVO);
	public MemberVO loginCheck(MemberVO memberVO);
	public List<MemberVO> selectMember();
}
