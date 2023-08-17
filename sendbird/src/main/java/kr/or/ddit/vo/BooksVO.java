package kr.or.ddit.vo;


public class BooksVO {
	private String book_no;
	private String book_title;
	private String book_content;
	private String book_cover;
	private String book_spmt_ymd;
	private String book_author;
	private String pub_nm;
	private String book_talt;
	private int book_margin;
	private int book_mileage;
	private int book_page_cnt;
	private int book_amt;
	private int book_discount;
	private int book_ntsl_amt;
	private String book_isbn;
	private String ccg_b001;
	private String ccg_b002;
	private String ccg_b003;
	
	@Override
	public String toString() {
		return "BooksVO [book_no=" + book_no + ", book_title=" + book_title + ", book_content=" + book_content
				+ ", book_cover=" + book_cover + ", book_spmt_ymd=" + book_spmt_ymd + ", book_author=" + book_author
				+ ", pub_nm=" + pub_nm + ", book_talt=" + book_talt + ", book_margin=" + book_margin + ", book_mileage="
				+ book_mileage + ", ccg_b001=" + ccg_b001 + ", ccg_b002=" + ccg_b002 + ", ccg_b003=" + ccg_b003
				+ ", book_page_cnt=" + book_page_cnt + ", book_amt=" + book_amt + ", book_discount=" + book_discount
				+ ", book_ntsl_amt=" + book_ntsl_amt + ", book_isbn=" + book_isbn + "]";
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
	public String getBook_no() {
		return book_no;
	}
	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public String getBook_content() {
		return book_content;
	}
	public void setBook_content(String book_content) {
		this.book_content = book_content;
	}
	public String getBook_cover() {
		return book_cover;
	}
	public void setBook_cover(String book_cover) {
		this.book_cover = book_cover;
	}
	public String getBook_spmt_ymd() {
		return book_spmt_ymd;
	}
	public void setBook_spmt_ymd(String book_spmt_ymd) {
		this.book_spmt_ymd = book_spmt_ymd;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public String getPub_nm() {
		return pub_nm;
	}
	public void setPub_nm(String pub_nm) {
		this.pub_nm = pub_nm;
	}
	public String getBook_talt() {
		return book_talt;
	}
	public void setBook_talt(String book_talt) {
		this.book_talt = book_talt;
	}
	public int getBook_margin() {
		return book_margin;
	}
	public void setBook_margin(int book_margin) {
		this.book_margin = book_margin;
	}
	public int getBook_mileage() {
		return book_mileage;
	}
	public void setBook_mileage(int book_mileage) {
		this.book_mileage = book_mileage;
	}
	public String getCcg_b001() {
		return ccg_b001;
	}
	public void setCcg_b001(String ccg_b001) {
		this.ccg_b001 = ccg_b001;
	}
	public String getCcg_b002() {
		return ccg_b002;
	}
	public void setCcg_b002(String ccg_b002) {
		this.ccg_b002 = ccg_b002;
	}
	public String getCcg_b003() {
		return ccg_b003;
	}
	public void setCcg_b003(String ccg_b003) {
		this.ccg_b003 = ccg_b003;
	}
}
