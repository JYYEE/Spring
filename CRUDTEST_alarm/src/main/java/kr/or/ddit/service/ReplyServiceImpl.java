package kr.or.ddit.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.mapper.ReplyMapper;
import kr.or.ddit.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements IReplyService {
	
	@Inject
	private ReplyMapper mapper;
	
	@Override
	public List<ReplyVO> selectReply(int boNo) {
		return mapper.selectReply(boNo);
	}

	@Override
	public ServiceResult insertReply(ReplyVO replyVO) {
		ServiceResult result = null;
		int status = mapper.insertReply(replyVO);
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
}
