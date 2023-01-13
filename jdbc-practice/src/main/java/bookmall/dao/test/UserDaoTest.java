package bookmall.dao.test;

import java.util.List;

import bookmall.dao.UserDao;
import bookmall.vo.UserVo;

public class UserDaoTest {
	public static void main(String[] args) {
		//testInsert();
		testFindAll();
	}

	private static void testInsert() {
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

	private static void testFindAll() {
		List<UserVo> list = new UserDao().findAll();
		for(UserVo vo : list) {
			System.out.println(vo);
		}
	}
}
