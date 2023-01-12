package bookshop.main;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.AuthorDao;
import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookShop {

	public static void main(String[] args) {
		displayBookInfo();

		Scanner scanner = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		Long no = scanner.nextLong();
		scanner.close();

		BookVo vo = new BookVo();
		vo.setNo(no);
		vo.setRent("Y");
		new BookDao().update(vo);

		displayBookInfo();
	}

	private static void displayBookInfo() {
		List<BookVo> list = new BookDao().findAll();
		System.out.println("******도서 정보 출력하기******");
		for (BookVo vo : list) {
			String rent = null;
			if (vo.getRent().equals("Y")) {
				rent = "대여중";
			} else {
				rent = "재고있음";
			}
			System.out.println("책 번호:" + vo.getNo() + ", 책 제목:" + vo.getTitle() + ", 작가:" + new AuthorDao().findAuthorName(vo.getAuthorNo()) + ", 대여 유무:" + rent);
		}
	}

}
