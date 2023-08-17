package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;

@Data
public class PaginationInfoVO<T> {
	private int totalRecord; 	// 총게시글수
	private int totalPage; 		// 총페이지수
	private int currentPage; 	// 현재 페이지
	private int screenSize = 10;// 한 페이지당 글 수
	private int blockSize = 5;	// 페이지 블록 수
	private int startPage;		// 시작 페이지	
	private int endPage;		// 끝 페이지
	private int startRow;		// 한 페이지 내 시작 글
	private int endRow;			// 한 페이지 내 끝 글
	private String searchType;	// 검색 타입
	private String searchWord;	// 검색어
	private List<T> dataList;	// 검색 결과 list

	public PaginationInfoVO() {};
	
	public PaginationInfoVO(int screenSize, int blockSize) {
		this.blockSize = blockSize;
		this.screenSize = screenSize;
	}
	
	// 총 페이지수 구하기
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (int) (Math.ceil(totalRecord / (double)screenSize));
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize - 1);
		endPage = (currentPage + (blockSize - 1))/blockSize * blockSize;
		startPage = endPage - (blockSize - 1);
	}
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		html.append("<ul class='pagination m-0 justify-content-center'>");
		if(startPage >1) {
			html.append("<li class='page-item'><a href='' class='page-link' data-page='"+(startPage - blockSize) + "'>"
					+ "<span class='material-icons'>keyboard_arrow_left</span></a></li>");
		}
		for(int i = startPage; i<= (endPage < totalPage ? endPage : totalPage); i++) {
			if(i == currentPage) {
				html.append("<li class='page-item active'><span class='page-link'>"+i+"</span></li>");
			} else {
				html.append("<li class='page-item'><a href='' class='page-link' data-page='"+i+"'>"+i+"</a></li>");
			}
		}
		if(endPage < totalPage) {
			html.append("<li class='page-item'><a href='' class='page-link' data-page='"+(endPage+1)+"'>"
					+ "<span class='material-icons'>keyboard_arrow_right</span></a></li>");
		}
		html.append("</ul>");
		return html.toString();
	}
}

	
