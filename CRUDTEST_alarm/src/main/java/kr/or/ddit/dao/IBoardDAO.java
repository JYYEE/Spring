package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IBoardDAO {
	public int insertBoard(BoardVO boardVO);
	public void incrementHit(int boNo);
	public BoardVO selectBoard(int boNo);
	public int updateBoard(BoardVO boardVO);
	public int deleteBoard(int boNo);
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO);
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO);
}
