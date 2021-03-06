package com.pcwk.ehr;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao {

	final Logger LOG = LogManager.getLogger(this.getClass());
	
	private DataSource dataSource;
	
	public UserDao() {}
	
	
	// setter를 통한 의존성 주입
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int getCount(UserVO inVO) throws SQLException{
		int count = 0;
		// 1. db연결
		// 2. sql Statement(X), PreparedStatement
		// 3. PreparedStatement 수행
		// 4. ResultSet으로 정보를 받아와 처리
		// 5. 자원반납
		
		// 1.
		Connection connection = null;
		connection = dataSource.getConnection();
		
		// 2.
		StringBuilder sb = new StringBuilder(100);
		sb.append("SELECT COUNT(*) AS cnt 	\n");
		sb.append("FROM hr_member         	\n");
		sb.append("WHERE u_id LIKE ? || '%'	\n");
		
		LOG.debug("==============================");
		LOG.debug("=param: " + inVO.toString());
		LOG.debug("=sql: \n" + sb.toString());
		LOG.debug("==============================");
		
		// 3.
		PreparedStatement pstmt = connection.prepareStatement(sb.toString());
		pstmt.setString(1, inVO.getuId());
		
		// 4.
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			count = rs.getInt("cnt");
		}
		LOG.debug("==============================");
		LOG.debug("=count: " + count);
		LOG.debug("==============================");

		// 5.
		rs.close();
		pstmt.close();
		connection.close();
		
		return count;
	}
	
	//  변하지 않는 부분(db연결, 자원반납)
	public int jdbcContextWithStatementStratege(StatementStrategy stmt) throws SQLException{
		int flag = 0;
		// 1.
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dataSource.getConnection();
			pstmt = stmt.makeStatement(connection);
			flag = pstmt.executeUpdate();
			
			
		} catch(SQLException e) {
			throw e;
		}finally {
			if(null != pstmt) {
				try {
					pstmt.close();
				} catch(SQLException e) {
					
				}
			}
			//	connection 자원 반납
			if(null != connection) {
				try {
					connection.close();
				} catch(SQLException e) {
					
				}
			}
		}
		return flag;
	}
	
	/**
	 * 사용자 등록
	 * 
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws ClassCastException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int add(UserVO inVO) throws SQLException{
		int flag = 0;
		
		StatementStrategy stmt = new UserDaoAddStatement(inVO);
		flag = jdbcContextWithStatementStratege(stmt);

		return flag;
	}
	
	//메서드를 통해 변하는 부분(SQL, SQL수행 부분),
	/**
	 * 전체 데이터 삭제
	 * @throws SQLException
	 */
	public void deleteAll() throws SQLException{
		jdbcContextWithStatementStratege(new UserDaoDeleteAllStatement());
	}

	/**
	 * 회원단건 return
	 * 
	 * @param inVO
	 * @return outVO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public UserVO get(UserVO inVO) throws SQLException{
		UserVO outVO = null;
		// 1. db연결
		// 2. sql Statement(X), PreparedStatement
		// 3. PreparedStatement 수행
		// 4. ResultSet으로 정보를 받아와 처리
		// 5. 자원반납

		// 1.
		Connection connection = null;
		connection = dataSource.getConnection();

		// 2.
		StringBuilder sb = new StringBuilder(100);
		sb.append("SELECT u_id,     \n");
		sb.append("	     name,      \n");
		sb.append("	     passwd     \n");
		sb.append("	FROM            \n");
		sb.append("	     hr_member  \n");
		sb.append("	WHERE u_id = ?  \n");

		LOG.debug("==============================");
		LOG.debug("=param: " + inVO.toString());
		LOG.debug("=sql: \n" + sb.toString());
		LOG.debug("==============================");

		// 3.
		PreparedStatement pstmt = connection.prepareStatement(sb.toString());
		pstmt.setString(1, inVO.getuId());

		// 4.
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			outVO = new UserVO();

			outVO.setuId(rs.getString("u_id"));
			outVO.setName(rs.getString("name"));
			outVO.setPasswd(rs.getString("passwd"));
		}
		
		// 데이터가 없으면 예외 발생
		if(null == outVO) {
			throw new NullPointerException();
		}
		
		LOG.debug("==============================");
		LOG.debug("=outVO: " + outVO.toString());
		LOG.debug("==============================");

		// 5.
		rs.close();
		pstmt.close();
		connection.close();
		return outVO;
	}

	


	
}
