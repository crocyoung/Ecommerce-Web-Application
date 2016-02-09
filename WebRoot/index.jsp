<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>estore main page</title>
  </head>
  
  <body>
    <h3>E-store main page</h3>
  
    <c:if test="${empty loginUser}" >
    <a href="${pageContext.request.contextPath }/login.jsp" >LogIn</a>
    <a href="${pageContext.request.contextPath }/regist.jsp">Register</a>
    </c:if>
    
    <c:if test="${not empty loginUser}">
    <a href="${pageContext.request.contextPath }/addproduct.jsp">Add products</a><br/><br/>
    <a href="${pageContext.request.contextPath }/LogoutServlet">LogOut</a>
    </c:if> 
    <br/>
    <a href="${pageContext.request.contextPath}/ProductListServlet">Show Products</a>
  </body>
</html>

