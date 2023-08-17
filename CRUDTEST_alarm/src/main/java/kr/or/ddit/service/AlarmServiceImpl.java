package kr.or.ddit.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.AlarmMapper;
import kr.or.ddit.vo.AlarmVO;

@Service
public class AlarmServiceImpl implements IAlarmService {
	
	@Inject
	private AlarmMapper mapper;

	@Override
	public void insertAlarm(AlarmVO alarmVO) {
		mapper.insertAlarm(alarmVO);
	}

	@Override
	public List<AlarmVO> alarmCheck(int memNo) {
		return mapper.alarmCheck(memNo);
	}
	
	@Override
	public int statChange(int alarmNo) {
		return mapper.statChange(alarmNo);
	}
	
	@Override
	public int allReadUpdate(int memNo) {
		return mapper.allReadUpdate(memNo);
	}
}
