package com.estore.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.estore.domain.Product;
import com.estore.service.ProductService;
import com.estore.utils.WebUtils;

public class AddProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//upload file
		if(!ServletFileUpload.isMultipartContent(request))
		{
			// if not file, dispatcher
			request.setAttribute("message", "sorry this is not an upload file form. Please check");
			request.getRequestDispatcher("/addproduct.jsp").forward(request, response);
			return;
		}
		// if this is a file, process the request and finish the file upload 
		
		// 因为上传完 还需要显示 所以返回值需要包含 product 的信息 用一个map
		// 整个上传在web层进行  然后在 封装javaBean   利用JavaBean处理 才在业务层
		
		try {
			
			Map<String, String> proMap = new HashMap<String, String>();
			
			proMap = WebUtils.doFileUpload(request);
			
			// 利用  map 封装 javaBean BeanUtils.populate
			Product product = new Product();
			BeanUtils.populate(product, proMap);
			
			//@TODO Test System.out.print(product);
			
			// 然后将 数据装入数据库
			ProductService ps = new ProductService();
			ps.addProduct(product);
			
			// 成功友好提示
			request.setAttribute("message", "product has been added successfully");
			request.getRequestDispatcher("/addproduct.jsp").forward(request, response);
	
			
		} catch (FileSizeLimitExceededException e) {
			request.setAttribute("message", "文件大小超过最大值 500M");
			request.getRequestDispatcher("/addproduct.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
		
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		   doGet(request, response);
	}
}
