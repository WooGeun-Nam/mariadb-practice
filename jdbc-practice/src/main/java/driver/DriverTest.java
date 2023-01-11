package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverTest {

	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			// 1. JDBC Driver Loading
			Class.forName("driver.MyDriver");

			// 2. 연결하기
			String url = "jdbc:mydb://127.0.0.1:2204/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			System.out.println("연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩실패" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(conn != null && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

}