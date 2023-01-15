package bookmall.vo;

public class OrderVo {
	// no, ordernumber, price, address, member_no
	private Long no;
	private String ordernumber;
	private Long price;
	private String address;
	private Long quantity;
	private Long userNo;
	private BookVo bookVo;
	private UserVo userVo;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public BookVo getBookVo() {
		return bookVo;
	}
	public void setBookVo(BookVo bookVo) {
		this.bookVo = bookVo;
	}
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", ordernumber=" + ordernumber + ", price=" + price + ", address=" + address
				+ ", quantity=" + quantity + ", userNo=" + userNo + ", bookVo=" + bookVo + ", userVo=" + userVo + "]";
	}
}
