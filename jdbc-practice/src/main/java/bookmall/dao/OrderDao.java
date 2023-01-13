package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;
import bookmall.vo.OrderVo;

public class OrderDao {
	public List<OrderVo> findAll() {
		List<OrderVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC Driver Loading
			conn = getConnection();

			// 3. Statement 준비
			String sql = "select no, ordernumber, price, address, user_no from orders order by no asc";
			pstmt = conn.prepareStatement(sql);

			// 4. SQL 실행 쿼리끝에 세미클론 X
			rs = pstmt.executeQuery();

			// 5. 결과 처리
			while (rs.next()) {
				OrderVo vo = new OrderVo();
				vo.setNo(rs.getLong(1));
				vo.setOrdernumber(rs.getString(2));
				vo.setPrice(rs.getLong(3));
				vo.setAddress(rs.getString(4));
				vo.setUserNo(rs.getLong(5));
				
				result.add(vo);
			}

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
	
	public void findAllByOrderBook() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC Driver Loading
			conn = getConnection();

			// 3. Statement 준비
			String sql = "select no, quantity, book_no, user_no from order_book order by no asc";
			pstmt = conn.prepareStatement(sql);

			// 4. SQL 실행 쿼리끝에 세미클론 X
			rs = pstmt.executeQuery();

			// 5. 결과 처리
			while (rs.next()) {
				OrderVo vo = new OrderVo();
				vo.setNo(rs.getLong(1));
				vo.setOrdernumber(rs.getString(2));
				vo.setPrice(rs.getLong(3));
				vo.setAddress(rs.getString(4));
				vo.setUserNo(rs.getLong(5));
				
				System.out.println(rs.getLong(1)+":"+rs.getLong(2)+":"+rs.getLong(3)+":"+rs.getLong(4));
			}

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
	}

	public void insert(OrderVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql = "insert into orders values(null, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, vo.getOrdernumber());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setString(3, vo.getAddress());
			pstmt.setLong(4, vo.getUserNo());
			
			//5. SQL 실행
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insertToOrderBook(OrderVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			List<CartVo> list = new CartDao().findUser(vo.getUserNo());
//			for(CartVo cVo : list) {
//				System.out.println(list);
//			}
			String sql = "insert into order_book values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			for(CartVo cVo : list) {
				pstmt.setLong(1, cVo.getQuantity());
				pstmt.setLong(2, cVo.getBookNo());
				pstmt.setLong(3, vo.getNo());
				
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://"+StaticIP.IP+":"+StaticIP.PORT+"/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
