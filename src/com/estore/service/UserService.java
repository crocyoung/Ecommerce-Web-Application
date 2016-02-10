package com.estore.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.estore.domain.User;
import com.estore.utils.JdbcUtils;


public class UserService {	

	public int regist(User user) {
		Connection conn = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		ResultSet set1 = null;
		ResultSet set2 = null;
		
		try {
			conn = JdbcUtils.getConnection();
			
			//check the username 
			stmt1 = conn.createStatement();
			String sql1 =" select * from users where username="+"'"+user.getUsername()+"'";
			set1 = stmt1.executeQuery(sql1);
			if(set1.next())
			{
				// username duplicate
				return 1;	
			}
			
			//check the email
			stmt2 = conn.createStatement();
			String sql2 =" select * from users where email="+"'"+user.getEmail()+"'";
			set2 = stmt2.executeQuery(sql2);
			if(set2.next())
			{
				// username duplicate
				return 2;	
			}
			
			// no duplicate username and email
			//udao.insert(user);
			addUser(user);
			return 3;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}finally
		{
			JdbcUtils.release(set1, stmt1, null);
			JdbcUtils.release(set2, stmt2, conn);
		}
	}
	
public void addUser(User user) {
		/*
		private int id;
		private String name;
		private String password;
		private String email;
		private String nickname;
		*/

		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());

		String sql =" insert into users values(null, ?, ?, ?, ?, null, null, null)";
		Object[] params = {user.getUsername(), user.getPassword(), user.getEmail(), user.getNickname() };
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

public User checkUser(String username, String password) {
	
        DataSource ds = JdbcUtils.getDataSource();
        QueryRunner runner = new QueryRunner(ds);
       
        String sql = "select * from users where username = ? and password = ?";
        
        try {
			User loginUser = runner.query(sql, new BeanHandler<User>(User.class), username, password);
			return loginUser;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	return null;
}	
}
	
		

