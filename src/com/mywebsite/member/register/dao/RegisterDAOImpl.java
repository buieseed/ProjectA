package com.mywebsite.member.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mywebsite.member.register.Bean.User;

public class RegisterDAOImpl implements RegisterDAO {
	private DataSource dataSource;

	public RegisterDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public RegisterDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean userIsExist(String username) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String userIsExistSQL = "select * from Members where username = ?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(userIsExistSQL);
			pstmt.setString(1, username);
			result = pstmt.executeQuery();
			// 有找到資料，代表己經有人使用了
			if (result.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public void userRegister(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String insertSQL = "insert into Members (username, password, email) value (?, ?, ?)";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
