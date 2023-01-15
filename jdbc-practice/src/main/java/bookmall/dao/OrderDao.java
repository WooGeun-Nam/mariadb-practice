package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.OrderVo;
import bookmall.vo.UserVo;

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
			String sql = "select o.ordernumber, u.name, u.email, o.price, o.address "
					+ "from orders o join user u on o.user_no = u.no order by o.no";
			pstmt = conn.prepareStatement(sql);

			// 4. SQL 실행 쿼리끝에 세미클론 X
			rs = pstmt.executeQuery();

			// 5. 결과 처리
			while (rs.next()) {
				OrderVo vo = new OrderVo();
				
				UserVo userVo = new UserVo();
				userVo.setName(rs.getString(2));
				userVo.setEmail(rs.getString(3));
				
				vo.setUserVo(userVo);
				vo.setOrdernumber(rs.getString(1));
				vo.setPrice(rs.getLong(4));
				vo.setAddress(rs.getString(5));
				
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
	
	public List<OrderVo> findAllByOrderBook() {
		List<OrderVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC Driver Loading
			conn = getConnection();

			// 3. Statement 준비
			String sql = "select b.no, b.title, ob.quantity from order_book ob "
					+ "join book b on ob.book_no = b.no order by ob.no asc";
			pstmt = conn.prepareStatement(sql);

			// 4. SQL 실행 쿼리끝에 세미클론 X
			rs = pstmt.executeQuery();

			// 5. 결과 처리
			while (rs.next()) {
				OrderVo vo = new OrderVo();
				
				BookVo bookVo = new BookVo();
				bookVo.setNo(rs.getLong(1));
				bookVo.setTitle(rs.getString(2));
				
				vo.setBookVo(bookVo);
				vo.setQuantity(rs.getLong(3));
				
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
			String sql = "insert into order_book values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			for(CartVo cartVo : list) {
				pstmt.setLong(1, cartVo.getQuantity());
				pstmt.setLong(2, cartVo.getBookNo());
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
