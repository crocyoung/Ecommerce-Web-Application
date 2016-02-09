<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Your Order</h3>
	<form action="/generateorder" method="post">	
		<table>
			<tr>
				<td>Shipping Address</td>
				<td>
					<textarea cols="65" rows="5" name="receiverinfo"></textarea>
				</td>
			</tr>
			<tr>
				<td>Payment</td>
				<td>
					<input type="radio" name="paymethod" value="PayPal">PayPal
					<input type="radio" name="paymethod" value="CreditCard">Credit Card
				</td>
			</tr>
		</table>
		<h3>Product Information</h3>
				<table width="100%" border="1" align="center" style="text-align: center">
			<tr>
				<th>Product</th>
				<th>Price</th>
				<th>Number</th>
				<th>SubTotal</th>
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
						${entry.value }
					</td>
					<td>
						${entry.key.price*entry.value }
					</td>
					<c:set var="totalprice" value="${totalprice + entry.key.price*entry.value }"></c:set>
				</tr>
			</c:forEach>
		</table>
		<div align="right">
			Order Total: ${totalprice }
			<input type="hidden" name="money" value="${totalprice }">
		</div>
		<div align="right">
			<input type="submit" value="Generate Order">
		</div>
	</form>
</body>
</html>