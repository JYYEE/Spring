package kr.or.ddit.vo;

import lombok.Data;

@Data
public class AlarmVO {
	private int alarmNo;		// 알람 번호
	private int memNo;			// 글 작성자의 memNo
	private String alarmType;	// 알람 타입 (게시글, 댓글)
	private int alarmStatus;	// 알람 상태(미확인 : 0, 확인 : 1, 기본값 : 0)
	private String alarmDate;	// 알람 작성일
	private String alarmUrl;	// 알람 경로
	private int alarmReceiver;	// 알람 발신자의 memNo
}
