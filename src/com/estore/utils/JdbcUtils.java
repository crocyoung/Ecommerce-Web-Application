package com.estore.utils;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
       
	    // use c3p0  connection pool  read  c3p0-config.xml
		private static DataSource dataSource = new ComboPooledDataSource();
        
		public static DataSource getDataSource() {
			return dataSource;
		}

		/**
		 * 当DBUtils需要手动控制事务时，调用该方法获得一个连接
		 * @throws SQLException
		 */
		public static Connection getConnection() throws SQLException {
			return dataSource.getConnection();
		}
		
		
		public static void release(ResultSet rs, Statement stmt, Connection conn)
		{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs =null;
			}
			
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt=null;
			}
			
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn=null;
			}
			
			
		}

	}



