package bookmall.dao.test;

import java.util.List;

import bookmall.dao.OrderDao;
import bookmall.vo.OrderVo;

public class OrderDaoTest {
	public static void main(String[] args) {
		//testInsert();
		//testFindAll();
		testInsertToOrderBook();
		testFindByOrderBook();
	}

	private static void testInsert() {
		OrderVo vo = null;
		OrderDao dao = new OrderDao();
		
		String[] data1 = {"A001"};
		Long[] data2 = {61200L};
		String[] data3 = {"Busan"};
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

	private static void testFindAll() {
		List<OrderVo> list = new OrderDao().findAll();
		for(OrderVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void testInsertToOrderBook() {
		OrderVo vo = null;
		OrderDao dao = new OrderDao();
		
		vo = new OrderVo();
		vo.setOrdernumber("A001");
		vo.setPrice(61200L);
		vo.setAddress("Busan");
		vo.setUserNo(2L);
		
		dao.insertToOrderBook(vo);
	}
	
	private static void testFindByOrderBook() {
		OrderDao dao = new OrderDao();
		dao.findAllByOrderBook();
	}
}
