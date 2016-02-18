package com.mywebsite.member.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String userIsExistSQL = "select * from Members where name = ?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(userIsExistSQL);
			pstmt.setString(1, username);
			result = pstmt.executeQuery();
			//有找到資料，代表己經有人使用了
			if(result.next()){
				flag = true;
			}
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
		return flag;
	}

}
