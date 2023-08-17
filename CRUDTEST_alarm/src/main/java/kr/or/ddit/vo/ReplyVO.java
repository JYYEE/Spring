package kr.or.ddit.vo;

import lombok.Data;

@Data
public class ReplyVO {
	private int replyNo;			// 댓글 번호
	private String replyContent;	// 댓글 내용
	private String replyDate;		// 댓글 작성일
	private int memNo;				// 댓글 작성자
	private int boNo;				// 댓글이 달린 게시글 번호
}
