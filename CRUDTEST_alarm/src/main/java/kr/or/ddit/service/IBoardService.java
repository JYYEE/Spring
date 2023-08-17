package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IBoardService {
	public ServiceResult insertBoard(BoardVO boardVO);
	public BoardVO selectBoard(int boNo);
	public ServiceResult updateBoard(BoardVO boardVO);
	public ServiceResult deleteBoard(int boNo);
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO);
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO);
}
