package com.estore.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estore.domain.Product;
import com.estore.service.CartService;

public class Add2CartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 //get id then get the product object corresponding the id
		 String id = request.getParameter("id");
	 
		 CartService cs = new CartService();
		 Product prod = cs.getProduct(id);
		 Product p = new Product();
		 p.setId(id);
		 		 
		 // use hashmap  <Product.class, num> to build the shoppingCart
		 // check if this cart in the session, if not, build new one, otherwise adjust
		  Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
		  
		 // The cart is not in the session, new the cart
		 if(cart == null)
		 {
			 // LinkedHashMap for keeping the order
			 cart = new LinkedHashMap<Product, Integer>(); 
		 }
		  
		 // adjust the cart
		 if(cart.contains(prod))
		 {
			cart.put(key, cart.get(prod)+1);
		 }
		 else
	 	 {
			cart.put(key, 1);
		 }
					 
		/* 
		 Set<Product> set = cart.keySet();
		 boolean newPro = true;
		 for(Product key : set)
		 {
			 if(isequals(key, prod))
			 {
				 int num = cart.get(key);
				 cart.put(key, num+1);
				 newPro = false;
				 break;
			 }
		 }      
		 if(newPro)
		 { 
			 cart.put(prod, 1);
		 }
		 */
 
		 // override the cart in Session  
		 request.getSession().setAttribute("cart", cart);
		 
		 // warning
		 request.setAttribute("message", "the product has been added into the shopping cart");
		 request.getRequestDispatcher("/addCartSuccess.jsp").forward(request, response);	 
	}

	
	private boolean isequals(Product key, Product prod) {
		return key.getId().equals(prod.getId());
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		     doGet(request,response);
	}
}
