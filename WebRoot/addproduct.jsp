<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 

private String id;
	private String name;
	private Double price;
	private int count;
	private String category;
	private String imageurl;
	private String description;
 -->
     
	<h2>Products adding</h2>
	<h3>${message}</h3>
	<form action="${pageContext.request.contextPath}/AddProductServlet" enctype="multipart/form-data" method="post">
		<table>
			<tr>
				<td>商品名称</td>
				<td>
					<input type="text" name="name">
				</td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td>
					<input type="text" name="price">
				</td>
			</tr>
			<tr>
				<td>商品数量</td>
				<td>
					<input type="text" name="count">
				</td>
			</tr>
			<tr>
				<td>商品种类</td>
				<td>
					<select name="category">
						<option value="家用电器">家用电器</option>
						<option value="手机、数码、黑马通信">手机、数码、黑马通信</option>
						<option value="电脑、办公">电脑、办公</option>
						<option value="男装、女装、内衣、珠宝">男装、女装、内衣、珠宝</option>
						<option value="营养保健">营养保健</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>商品靓照</td>
				<td>
					<input type="file" name="image">
				</td>
			</tr>
			<tr>
				<td>商品描述</td>
				<td>
					<textarea rows="5" cols="80" name="description"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="添加">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>