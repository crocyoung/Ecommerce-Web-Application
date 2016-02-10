package com.estore.service;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.estore.domain.Product;
import com.estore.utils.JdbcUtils;

public class CartService {
	public Product getProduct(String id) {
		
        DataSource ds = JdbcUtils.getDataSource();
        QueryRunner runner = new QueryRunner(ds);
       
        String sql = "select * from products where id = ?";
        
        try {
        	// if the database contains this product, return Product class object. Otherwise return null  		                             
			Product product = runner.query(sql, new BeanHandler<Product>(Product.class), id);
			return product;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
  }
}
