package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.AlarmVO;

public interface IAlarmService {
	public void insertAlarm(AlarmVO alarmVO);
	public List<AlarmVO> alarmCheck(int memNo);
	public int statChange(int alarmNo);
	public int allReadUpdate(int memNo);
}
