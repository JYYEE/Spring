package kr.or.ddit.vo;

public class Books_manager {
	private String book_no;
	private int bm_cnt;
	private int bm_bsc_cnt;
	private int bm_cnt_indct1;
	private int bm_cnt_indct2;	
	
	public String getBook_no() {
		return book_no;
	}


	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}


	public int getBm_cnt() {
		return bm_cnt;
	}


	public void setBm_cnt(int bm_cnt) {
		this.bm_cnt = bm_cnt;
	}


	public int getBm_bsc_cnt() {
		return bm_bsc_cnt;
	}


	public void setBm_bsc_cnt(int bm_bsc_cnt) {
		this.bm_bsc_cnt = bm_bsc_cnt;
	}


	public int getBm_cnt_indct1() {
		return bm_cnt_indct1;
	}


	public void setBm_cnt_indct1(int bm_cnt_indct1) {
		this.bm_cnt_indct1 = bm_cnt_indct1;
	}


	public int getBm_cnt_indct2() {
		return bm_cnt_indct2;
	}


	public void setBm_cnt_indct2(int bm_cnt_indct2) {
		this.bm_cnt_indct2 = bm_cnt_indct2;
	}


	@Override
	public String toString() {
		return "Books_manager [book_no=" + book_no + ", bm_cnt=" + bm_cnt + ", bm_bsc_cnt=" + bm_bsc_cnt
				+ ", bm_cnt_indct1=" + bm_cnt_indct1 + ", bm_cnt_indct2=" + bm_cnt_indct2 + "]";
	}
		
}
