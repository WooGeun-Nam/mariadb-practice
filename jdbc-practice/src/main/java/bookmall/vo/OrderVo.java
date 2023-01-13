package bookmall.vo;

public class OrderVo {
	// no, ordernumber, price, address, member_no
	private Long no;
	private String ordernumber;
	private Long price;
	private String address;
	private Long userNo;
	
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
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long memberNo) {
		this.userNo = memberNo;
	}
	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", ordernumber=" + ordernumber + ", price=" + price + ", address=" + address
				+ ", userNo=" + userNo + "]";
	}
}
