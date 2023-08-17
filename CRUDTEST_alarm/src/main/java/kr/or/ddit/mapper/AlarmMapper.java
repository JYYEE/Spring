package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.AlarmVO;

public interface AlarmMapper {
	public void insertAlarm(AlarmVO alarmVO);
	public List<AlarmVO> alarmCheck(int memNo);
	public int statChange(int alarmNo);
	public int allReadUpdate(int memNo);
}
