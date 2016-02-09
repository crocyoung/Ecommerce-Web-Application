<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script>
function add2Cart(id)
{
// 将商品添加到购物车中去
// 就是发起一个请求给 购物车 servlet 这个请求中包含 id 信息
// 当触发这个 function 的时候  页面跳转 到 location
location.href="${pageContext.request.contextPath}/Add2CartServlet?id=" + id;
}
</script>


<body>
<c:if test="${empty productList }">
     sorry, there are no product for sell now
</c:if>

<c:if test="${not empty productList}">
<h2>Products List</h2>

<c:forEach items="${productList}" var="product">
<div><img src ="${pageContext.request.contextPath}/${product.imageurl }" align="left" > </div>
Product Name : ${product.name }<br/>
Product Price: ${product.price}<br/>
Inventory   : ${product.count}<br/>
Description  : ${product.description} <br/><br/><br/><br/><br/><br/><br/><br/>
<img src="${pageContext.request.contextPath}/imgs/addToCart.png" style="cursor: pointer;"  onclick="add2Cart('${product.id}')"><br/>

<br clear="left">
<hr>
</c:forEach>

</c:if>
    
</body>
</html>