package com.mywebsite.member.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class LoginDAOImpl implements LoginDAO{
	private DataSource dataSource;

	public LoginDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginDAOImpl(DataSource dataSource){
		this.dataSource = dataSource;
	}

	@Override
	public boolean findUser(String username, String password) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String findUerSQL = "select username, password from members where username = ? and password = ?";
		ResultSet result = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(findUerSQL);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			result = pstmt.executeQuery();
			if(result.next()){
				flag = true;
			}
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
		return flag;
	}

}
