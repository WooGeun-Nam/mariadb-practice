package bookmall.main;

public class BookMall {

	public static void main(String[] args) {
		
		// 사용자 2
		BookFunction.UserInsert();
		System.out.println("####### 회원리스트 #######");
		BookFunction.UserFindAll();
		
		// 컴퓨터 인문학 교양 3
		BookFunction.CategoryInsert();
		System.out.println("####### 카테고리 #######");
		BookFunction.CategoryFindAll();
		
		// 카테고리 + 상품 3개
		BookFunction.BookInsert();
		System.out.println("####### 상품리스트 #######");
		BookFunction.BookFindAll();
		
		// 카트 리스트 2개
		BookFunction.CartInsert();
		System.out.println("####### 카트 #######");
		BookFunction.CartFindAll();
		
		// 주문 리스트 1개
		BookFunction.OrderInsert();
		System.out.println("####### 주문 #######");
		BookFunction.OrderFindAll();
		
		// 주문 도서 리스트 2개
		System.out.println("####### 주문 도서 #######");
		
	}
}
