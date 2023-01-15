package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;

public class CartDaoTest {
	public static void main(String[] args) {
		//testInsert();
		testFindAll();
		//testFindUser();
	}

	public static void testFindUser() {
		List<CartVo> list = new CartDao().findUser(1L);
		for(CartVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void testInsert() {
		CartVo vo = null;
		CartDao dao = new CartDao();
		
		Long[] data1 = {2L,2L};
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

	public static void testFindAll() {
		List<CartVo> list = new CartDao().findAll();
		for(CartVo vo : list) {
			BookVo bookVo = vo.getBookVo();
			System.out.println("| 도서제목 : "+bookVo.getTitle()+" | 수량 : "+vo.getQuantity()+" | 가격 : "+bookVo.getPrice()+" |");
		}
	}
}
