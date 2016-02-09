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
		
		// ��Ϊ�ϴ��� ����Ҫ��ʾ ���Է���ֵ��Ҫ���� product ����Ϣ ��һ��map
		// �����ϴ���web�����  Ȼ���� ��װjavaBean   ����JavaBean���� ����ҵ���
		
		try {
			
			Map<String, String> proMap = new HashMap<String, String>();
			
			proMap = WebUtils.doFileUpload(request);
			
			// ����  map ��װ javaBean BeanUtils.populate
			Product product = new Product();
			BeanUtils.populate(product, proMap);
			
			//@TODO Test System.out.print(product);
			
			// Ȼ�� ����װ�����ݿ�
			ProductService ps = new ProductService();
			ps.addProduct(product);
			
			// �ɹ��Ѻ���ʾ
			request.setAttribute("message", "product has been added successfully");
			request.getRequestDispatcher("/addproduct.jsp").forward(request, response);
	
			
		} catch (FileSizeLimitExceededException e) {
			request.setAttribute("message", "�ļ���С�������ֵ 500M");
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
