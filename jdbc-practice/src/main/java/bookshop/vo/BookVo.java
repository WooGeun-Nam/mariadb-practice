package bookshop.vo;

public class BookVo {
	private Long no;
	private String title;
	private Long authorNo;
	private String rent;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getAuthorNo() {
		return authorNo;
	}
	public void setAuthorNo(Long authorNo) {
		this.authorNo = authorNo;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", authorNo=" + authorNo + ", rent=" + rent + "]";
	}
}
