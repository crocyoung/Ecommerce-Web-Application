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
			// 拿工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			
			// 设置缓冲区大小,  以及临时文件的存放位置
			
			factory.setSizeThreshold(1024*1024);  // 1M
			//缓冲区是这个文件夹
			factory.setRepository(new File(request.getServletContext().getRealPath("/temp")));
			
			// 建立 上传文件的 核心对象    
			ServletFileUpload uploader = new ServletFileUpload(factory);
			
			// 防止文件名乱码(name)  下面有防止 value 乱码的
			uploader.setHeaderEncoding("UTF-8");
			
			// 设置上传文件大小
			uploader.setFileSizeMax(1000*1000*500); //5M
			
			// 将 uploader 里面的文件都 解析出来
			List<FileItem> list = uploader.parseRequest(request);
			
			// 新建一个 map 作为返回值
			Map<String, String> map = new HashMap<String, String>();
			
			for(FileItem fileItem : list)
			{
				// 判断是否为普通输入项
				// 获得参数名称 和 value
				if(fileItem.isFormField())
				{
					String name = fileItem.getFieldName();
					String value = fileItem.getString("UTF-8");  // value 防乱码
					// 将拿到的 name 和 value 封装到map中去
					map.put(name, value);
				}
				else
				{
					// 是文件上传类型
					String filename = fileItem.getName();
					
					// TODO 这里还可以对 文件名后缀 进行进行 检测
					
					// 对不同浏览器导致上传文件名不同 的请款
					// 对 文件名前 无效路径去除 
					String realFileName = UploadUtils.subFileName(filename);
					
					// 将文件 写到 server 的硬盘中去
					// 生成随机文件名 -- 避免同名文件
					String UUIDFileName = UploadUtils.generateRandonFileName(realFileName);
					
					// 生成随机文件夹的路径 -- 避免单个文件夹 文件过多 过慢   /1/5
					String randomDir = UploadUtils.generateRandomDir(UUIDFileName);
					
					// 建立一个images 文件夹用来存图片
					String imageurl = "/images" + randomDir + "/" + UUIDFileName;
					
					String realPath = request.getServletContext().getRealPath("/images")
			             	+ randomDir;
					
					// 用一个文件 判断这个目录 是否存在 如果 不存在需要去创建
					File checkPathFile = new File(realPath);
					if(!checkPathFile.exists())
					{
						checkPathFile.mkdirs(); // 创建目录
					}
					
					// 新建一个此目录的文件 等待数据传入
					//      创建文件              路径               文件名
					File file = new File(realPath, UUIDFileName);
					
					//将数据 从 fileItem 读进来 然后写到 新目录的 file 中去 
					InputStream in = fileItem.getInputStream();
					OutputStream out = new FileOutputStream(file);  
					
					int len = 0;
					byte[] buf = new byte[1024];
					while((len=in.read(buf)) > 0) // 如果读完了 in.read() 就没数据了 就=0
					{
						out.write(buf, 0, len);
					}
					in.close();
					out.close();
					
					//删除 temp 中的临时文件
					fileItem.delete();
					
					// 然后将 路径 输入 map 中
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
