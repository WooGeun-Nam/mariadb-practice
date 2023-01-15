package bookmall.vo;

public class CartVo {
	private Long no;
	private Long quantity;
	private Long userNo;
	private Long bookNo;
	private BookVo bookVo;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long memberNo) {
		this.userNo = memberNo;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public BookVo getBookVo() {
		return bookVo;
	}
	public void setBookVo(BookVo bookVo) {
		this.bookVo = bookVo;
	}
	@Override
	public String toString() {
		return "CartVo [no=" + no + ", quantity=" + quantity + ", userNo=" + userNo + ", bookNo=" + bookNo + ", bookVo="
				+ bookVo + "]";
	}
}
