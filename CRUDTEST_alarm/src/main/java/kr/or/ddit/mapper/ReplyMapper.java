package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.ReplyVO;

public interface ReplyMapper {
	public List<ReplyVO> selectReply(int boNo);
	public int insertReply(ReplyVO replyVO);
}
