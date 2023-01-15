package bookmall.main;

import bookmall.dao.test.*;

public class BookMall {

	public static void main(String[] args) {
		
		// 사용자 2
		UserDaoTest.testInsert();
		System.out.println("####### 회원리스트 #######");
		UserDaoTest.testFindAll();
		
		// 컴퓨터 인문학 교양 3
		CategoryDaoTest.testInsert();
		System.out.println("####### 카테고리 #######");
		CategoryDaoTest.testFindAll();
		
		// 카테고리 + 상품 3개
		BookDaoTest.testInsert();
		System.out.println("####### 상품리스트 #######");
		BookDaoTest.testFindAll();
		
		// 카트 리스트 2개
		CartDaoTest.testInsert();
		System.out.println("####### 카트 #######");
		CartDaoTest.testFindAll();
		
		// 주문 리스트 1개
		OrderDaoTest.testInsert();
		System.out.println("####### 주문 #######");
		OrderDaoTest.testFindAll();
		
		// 주문 도서 리스트 2개
		OrderDaoTest.testInsertToOrderBook();
		System.out.println("####### 주문 도서 #######");
		OrderDaoTest.testFindByOrderBook();
		
	}
}
