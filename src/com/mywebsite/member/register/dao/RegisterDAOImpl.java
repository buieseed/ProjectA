package com.mywebsite.member.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class RegisterDAOImpl implements RegisterDAO {
	private DataSource dataSource;

	public RegisterDAOImpl() {
		// TODO Auto-generated constructor stub
	}
    public RegisterDAOImpl(DataSource dataSource){
    	this.dataSource = dataSource;
    }
	@Override
	public boolean userIsExist(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String userIsExistSQL = "select * from Members where name = ?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(userIsExistSQL);
			pstmt.setString(1, username);
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

}
