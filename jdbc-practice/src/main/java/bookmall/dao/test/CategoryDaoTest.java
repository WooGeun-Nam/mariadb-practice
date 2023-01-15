package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {
	public static void main(String[] args) {
		//testInsert();
		testFindAll();
	}

	public static void testInsert() {
		CategoryVo vo = null;
		CategoryDao dao = new CategoryDao();
		
		String[] data = {"소설", "컴퓨터&IT", "예술"};
		
		for(int i=0 ; i<data.length; i++) {
			vo = new CategoryVo();
			vo.setName(data[i]);
			dao.insert(vo);
		}
	}

	public static void testFindAll() {
		List<CategoryVo> list = new CategoryDao().findAll();
		for(CategoryVo vo : list) {
			System.out.println("| 카테고리번호 : "+vo.getNo()+" | 카테고리이름 : "+vo.getName()+" |");
		}
	}
}
