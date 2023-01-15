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

public class CartDao {
	public List<CartVo> findAll() {
		List<CartVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC Driver Loading
			conn = getConnection();

			// 3. Statement 준비
			String sql = "select b.title, c.quantity, b.price from cart c join book b on c.book_no = b.no order by c.no";
			pstmt = conn.prepareStatement(sql);

			// 4. SQL 실행 쿼리끝에 세미클론 X
			rs = pstmt.executeQuery();

			// 5. 결과 처리
			while (rs.next()) {
				CartVo vo = new CartVo();
				
				BookVo bookVo = new BookVo();
				bookVo.setTitle(rs.getString(1));
				bookVo.setPrice(rs.getLong(3));
				
				vo.setBookVo(bookVo);
				vo.setQuantity(rs.getLong(2));
				
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

	public List<CartVo> findUser(Long userNo) {
		List<CartVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC Driver Loading
			conn = getConnection();

			// 3. Statement 준비
			String sql = "select no, quantity, user_no, book_no from cart where user_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, userNo);
			// 4. SQL 실행 쿼리끝에 세미클론 X
			rs = pstmt.executeQuery();

			// 5. 결과 처리
			while (rs.next()) {
				CartVo vo = new CartVo();
				vo.setNo(rs.getLong(1));
				vo.setQuantity(rs.getLong(2));
				vo.setUserNo(rs.getLong(3));
				vo.setBookNo(rs.getLong(4));
				
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
	
	public void insert(CartVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql = "insert into cart values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setLong(1, vo.getQuantity());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setLong(3, vo.getUserNo());
			
			
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
