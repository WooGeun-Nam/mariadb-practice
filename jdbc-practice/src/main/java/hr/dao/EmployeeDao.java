package hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.vo.EmployeeVo;

public class EmployeeDao {
	public List<EmployeeVo> findBySalary(int minSalary, int maxSalary) {
		List<EmployeeVo> result = new ArrayList<EmployeeVo>();
		
		return result;
	}
	
	public List<EmployeeVo> findByName(String keyword) {
		List<EmployeeVo> result = new ArrayList<EmployeeVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC Driver Loading
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.64.3:3306/employees?charset=utf8";
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 3. Statement 준비
			String sql = 
					"select emp_no, first_name, last_name, date_format(hire_date, '%Y-%m-%d')" +
					" from employees" +
					" where first_name like ? or last_name like ?" +
					" order by hire_date asc";
			pstmt = conn.prepareStatement(sql);

			// 4. binding
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");

			// 4. SQL 실행 쿼리끝에 세미클론 X
			rs = pstmt.executeQuery();

			// 5. 결과 처리
			while (rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String hireDate = rs.getString(4);

				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setHireDate(hireDate);
				
				result.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩실패" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		return result;
	}
}
