package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {
	public static void main(String[] args) {
		//testInsert();
		testFindAll();
	}

	private static void testInsert() {
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

	private static void testFindAll() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}
}
