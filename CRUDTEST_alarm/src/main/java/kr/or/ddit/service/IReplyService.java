package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.ReplyVO;

public interface IReplyService {
	public List<ReplyVO> selectReply(int boNo);
	public ServiceResult insertReply(ReplyVO replyVO);
}
