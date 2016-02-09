<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function updateCart(id,count){
		
		if(isNaN(count)){
			
			alert("对不起,您输入的不是数值,不要瞎搞...");
			return;
		}
		
		//去访问 一个 servlet程序, 去更新 购物车中的商品的 名称和数量的关系
		location.href="######/updateCart?id="+id+"&count="+count;
		
	}
</script>
</head>
<body>
	<c:if test="${empty cart }">
		sorry, there are no product in shopping cart
		<a href="${pageContext.request.contextPath}/ProductListServlet">Back to shopping</a>
	</c:if>
	<c:if test="${not empty cart }">
		<h3>Shopping Cart</h3>
		<table width="100%" border="1" align="center" style="text-align: center">
			<tr>
				<th>Product</th>
				<th>price</th>
				<th>number</th>
				<th>subTotal</th>
			</tr>
			<!-- map 
				Map<Product,Integer>
			 -->
			 <c:set var="totalprice"></c:set>
			<c:forEach items="${cart }" var="entry">
				<tr>
					<td>${entry.key.name }</td>
					<td>${entry.key.price }</td>
					<td>
						<a style="cursor: pointer" onclick="updateCart('${entry.key.id}','${entry.value-1 }');"> - </a>
						<input type="text" value="${entry.value }" size="1" style="text-align: center;" onblur="updateCart('${entry.key.id}',this.value);"> 
						<a style="cursor: pointer" onclick="updateCart('${entry.key.id}','${entry.value+1 }');"> + </a>
					</td>
					<td>
						${entry.key.price*entry.value }
					</td>
					<c:set var="totalprice" value="${totalprice+entry.key.price*entry.value }"></c:set>
				</tr>
			</c:forEach>
		</table>
		<div align="right">
			Total: ${totalprice }
			<a style="cursor: pointer" onclick="javascript:location.href='${pageContext.request.contextPath}/generateorder.jsp'"><font color="red">Generate Order</font></a>
		</div>
	</c:if>
</body>
</html>