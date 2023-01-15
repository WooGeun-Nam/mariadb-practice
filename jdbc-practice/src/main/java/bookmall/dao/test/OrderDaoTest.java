package bookmall.dao.test;

import java.util.List;

import bookmall.dao.OrderDao;
import bookmall.vo.BookVo;
import bookmall.vo.OrderVo;
import bookmall.vo.UserVo;

public class OrderDaoTest {
	public static void main(String[] args) {
		// testInsert();
		testFindAll();
		// testInsertToOrderBook();
		testFindByOrderBook();
	}

	public static void testInsert() {
		OrderVo vo = null;
		OrderDao dao = new OrderDao();

		String[] data1 = { "A001" };
		Long[] data2 = { 61200L };
		String[] data3 = { "Busan" };
		Long[] data4 = { 2L, 1L };

		// no, name, phonenumber, email, password
		for (int i = 0; i < data1.length; i++) {
			vo = new OrderVo();
			vo.setOrdernumber(data1[i]);
			vo.setPrice(data2[i]);
			vo.setAddress(data3[i]);
			vo.setUserNo(data4[i]);

			dao.insert(vo);
		}
	}

	public static void testFindAll() {
		List<OrderVo> list = new OrderDao().findAll();
		for (OrderVo vo : list) {
			UserVo userVo = vo.getUserVo();
			System.out.println("| 주문번호 : "+vo.getOrdernumber()+" | 이름 : "+userVo.getName()+" | "
					+ "이메일 : "+userVo.getEmail()+" | 결제금액 : "+vo.getPrice()+" | 주소 : "+vo.getAddress()+" |");
		}
	}

	public static void testInsertToOrderBook() {
		OrderVo vo = null;
		OrderDao dao = new OrderDao();

		vo = new OrderVo();
		vo.setNo(1L);
		vo.setOrdernumber("A001");
		vo.setPrice(142000L);
		vo.setAddress("Busan");
		vo.setUserNo(2L);

		dao.insertToOrderBook(vo);
	}

	public static void testFindByOrderBook() {
		List<OrderVo> list = new OrderDao().findAllByOrderBook();
		for (OrderVo vo : list) {
			BookVo bookVo = vo.getBookVo();

			System.out.println(
					"| 도서번호 : " + bookVo.getNo() + " | 도서제목 : " + bookVo.getTitle() + " | 수량 : " + vo.getQuantity()+" |");
		}
	}
}
