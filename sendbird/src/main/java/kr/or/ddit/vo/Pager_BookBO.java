package kr.or.ddit.vo;

public class Pager_BookBO {
	private String book_no;
	private int book_page_cnt;
	private int book_amt;
	private int book_discount;
	private int book_ntsl_amt;
	private String book_isbn;
	
	public String getBook_no() {
		return book_no;
	}
	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}
	public int getBook_page_cnt() {
		return book_page_cnt;
	}
	public void setBook_page_cnt(int book_page_cnt) {
		this.book_page_cnt = book_page_cnt;
	}
	public int getBook_amt() {
		return book_amt;
	}
	public void setBook_amt(int book_amt) {
		this.book_amt = book_amt;
	}
	public int getBook_discount() {
		return book_discount;
	}
	public void setBook_discount(int book_discount) {
		this.book_discount = book_discount;
	}
	public int getBook_ntsl_amt() {
		return book_ntsl_amt;
	}
	public void setBook_ntsl_amt(int book_ntsl_amt) {
		this.book_ntsl_amt = book_ntsl_amt;
	}
	public String getBook_isbn() {
		return book_isbn;
	}
	public void setBook_isbn(String book_isbn) {
		this.book_isbn = book_isbn;
	}
	
	@Override
	public String toString() {
		return "Pager_BookBO [book_no=" + book_no + ", book_page_cnt=" + book_page_cnt + ", book_amt=" + book_amt
				+ ", book_discount=" + book_discount + ", book_ntsl_amt=" + book_ntsl_amt + ", book_isbn=" + book_isbn
				+ "]";
	}
	
}
