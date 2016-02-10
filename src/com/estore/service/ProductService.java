package com.estore.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.estore.domain.Product;
import com.estore.utils.JdbcUtils;

public class ProductService {
	
	public void addProduct(Product product) {
		/*
		id        varchar(50) primary key,
	    name      varchar(50),
	    price     double,
	    category  varchar(50),
	    count     int,
	    imageurl  varchar(60) 
	    decription varchar(255)  
		*/
	 
	    //insert User into database
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());

		String sql =" insert into products values(?, ?, ?, ?, ?, ?, ?)";
		Object[] params = {generateId(product.getName()), product.getName(), product.getPrice(), product.getCategory(), product.getCount(), product.getImageurl(), product.getDescription()};
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			// insert error
			throw new RuntimeException(e);
		}
	}

	private static String generateId(String name) {
		// random id
		return "product-" + new Date().toString() + "-" + name;
	}

	public List<Product> getAllProduct() {
	
	try {
	
		// get connections from connection pool 
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		
		String sql ="select * from products";
		 
		// return List of Product
		List<Product> productList= runner.query(sql, new BeanListHandler<Product>(Product.class));
		return productList;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}




