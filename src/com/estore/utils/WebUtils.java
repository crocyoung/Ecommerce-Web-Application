package com.estore.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class WebUtils {

	public static Map<String, String> doFileUpload(HttpServletRequest request) throws FileSizeLimitExceededException {
		
		try {
			// �ù���
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			
			// ���û�������С,  �Լ���ʱ�ļ��Ĵ��λ��
			
			factory.setSizeThreshold(1024*1024);  // 1M
			//������������ļ���
			factory.setRepository(new File(request.getServletContext().getRealPath("/temp")));
			
			// ���� �ϴ��ļ��� ���Ķ���    
			ServletFileUpload uploader = new ServletFileUpload(factory);
			
			// ��ֹ�ļ�������(name)  �����з�ֹ value �����
			uploader.setHeaderEncoding("UTF-8");
			
			// �����ϴ��ļ���С
			uploader.setFileSizeMax(1000*1000*500); //5M
			
			// �� uploader ������ļ��� ��������
			List<FileItem> list = uploader.parseRequest(request);
			
			// �½�һ�� map ��Ϊ����ֵ
			Map<String, String> map = new HashMap<String, String>();
			
			for(FileItem fileItem : list)
			{
				// �ж��Ƿ�Ϊ��ͨ������
				// ��ò������� �� value
				if(fileItem.isFormField())
				{
					String name = fileItem.getFieldName();
					String value = fileItem.getString("UTF-8");  // value ������
					// ���õ��� name �� value ��װ��map��ȥ
					map.put(name, value);
				}
				else
				{
					// ���ļ��ϴ�����
					String filename = fileItem.getName();
					
					// TODO ���ﻹ���Զ� �ļ�����׺ ���н��� ���
					
					// �Բ�ͬ����������ϴ��ļ�����ͬ �����
					// �� �ļ���ǰ ��Ч·��ȥ�� 
					String realFileName = UploadUtils.subFileName(filename);
					
					// ���ļ� д�� server ��Ӳ����ȥ
					// ��������ļ��� -- ����ͬ���ļ�
					String UUIDFileName = UploadUtils.generateRandonFileName(realFileName);
					
					// ��������ļ��е�·�� -- ���ⵥ���ļ��� �ļ����� ����   /1/5
					String randomDir = UploadUtils.generateRandomDir(UUIDFileName);
					
					// ����һ��images �ļ���������ͼƬ
					String imageurl = "/images" + randomDir + "/" + UUIDFileName;
					
					String realPath = request.getServletContext().getRealPath("/images")
			             	+ randomDir;
					
					// ��һ���ļ� �ж����Ŀ¼ �Ƿ���� ��� ��������Ҫȥ����
					File checkPathFile = new File(realPath);
					if(!checkPathFile.exists())
					{
						checkPathFile.mkdirs(); // ����Ŀ¼
					}
					
					// �½�һ����Ŀ¼���ļ� �ȴ����ݴ���
					//      �����ļ�              ·��               �ļ���
					File file = new File(realPath, UUIDFileName);
					
					//������ �� fileItem ������ Ȼ��д�� ��Ŀ¼�� file ��ȥ 
					InputStream in = fileItem.getInputStream();
					OutputStream out = new FileOutputStream(file);  
					
					int len = 0;
					byte[] buf = new byte[1024];
					while((len=in.read(buf)) > 0) // ��������� in.read() ��û������ ��=0
					{
						out.write(buf, 0, len);
					}
					in.close();
					out.close();
					
					//ɾ�� temp �е���ʱ�ļ�
					fileItem.delete();
					
					// Ȼ�� ·�� ���� map ��
					map.put("imageurl", imageurl );
				}	
			}
			
			return map;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

}
