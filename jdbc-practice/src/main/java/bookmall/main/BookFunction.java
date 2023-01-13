package bookmall.main;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.UserDao;
import bookmall.dao.OrderDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.UserVo;
import bookmall.vo.OrderVo;

public class BookFunction {
	static void UserInsert() {
		UserVo vo = null;
		UserDao dao = new UserDao();
		
		String[] data1 = {"둘리", "또치"};
		String[] data2 = {"01011112222","01033334444"};
		String[] data3 = {"dooly@gmail.com","ddochi@gmail.com"};
		String[] data4 = {"dooly","ddochi"};
		
		// no, name, phonenumber, email, password
		for(int i=0 ; i<data1.length; i++) {
			vo = new UserVo();
			vo.setName(data1[i]);
			vo.setPhoneNumber(data2[i]);
			vo.setEmail(data3[i]);
			vo.setPassword(data4[i]);
			
			dao.insert(vo);
		}
	}
	
	static void UserFindAll() {
		List<UserVo> list = new UserDao().findAll();
		for(UserVo vo : list) {
			System.out.println(vo);
		}
	}
	
	static void CategoryInsert() {
		CategoryVo vo = null;
		CategoryDao dao = new CategoryDao();
		
		String[] data = {"소설", "컴퓨터&IT", "예술"};
		
		for(int i=0 ; i<data.length; i++) {
			vo = new CategoryVo();
			vo.setName(data[i]);
			dao.insert(vo);
		}
	}

	static void CategoryFindAll() {
		List<CategoryVo> list = new CategoryDao().findAll();
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
	}
	
	static void BookInsert() {
		BookVo vo = null;
		BookDao dao = new BookDao();
		
		//String[] data = {"소설", "컴퓨터&IT", "예술"};
		String[] data1 = {"화가가 사랑한 나무들", "재벌집 막내아들", "자바스크립트로 배우는 SICP"};
		Long[] data2 = {18900L,15300L,40500L};
		Long[] data3 = {3L,1L,2L};
		
		for(int i=0 ; i<data1.length; i++) {
			vo = new BookVo();
			vo.setTitle(data1[i]);
			vo.setPrice(data2[i]);
			vo.setCategoryNo(data3[i]);
			
			dao.insert(vo);
		}
	}

	static void BookFindAll() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}
	
	static void CartInsert() {
		CartVo vo = null;
		CartDao dao = new CartDao();
		
		Long[] data1 = {2L,1L};
		Long[] data2 = {2L,3L};
		Long[] data3 = {4L,2L};
		
		for(int i=0 ; i<data1.length; i++) {
			vo = new CartVo();
			vo.setUserNo(data1[i]);
			vo.setBookNo(data2[i]);
			vo.setQuantity(data3[i]);
			
			dao.insert(vo);
		}
	}

	static void CartFindAll() {
		List<CartVo> list = new CartDao().findAll();
		for(CartVo vo : list) {
			System.out.println(vo);
		}
	}
	
	static void OrderInsert() {
		OrderVo vo = null;
		OrderDao dao = new OrderDao();
		
		String[] data1 = {"A001", "A002"};
		Long[] data2 = {61200L,81000L};
		String[] data3 = {"Busan","Seoul"};
		Long[] data4 = {2L, 1L};
		
		// no, name, phonenumber, email, password
		for(int i=0 ; i<data1.length; i++) {
			vo = new OrderVo();
			vo.setOrdernumber(data1[i]);
			vo.setPrice(data2[i]);
			vo.setAddress(data3[i]);
			vo.setUserNo(data4[i]);
			
			dao.insert(vo);
		}
	}

	static void OrderFindAll() {
		List<OrderVo> list = new OrderDao().findAll();
		for(OrderVo vo : list) {
			System.out.println(vo);
		}
	}
}
