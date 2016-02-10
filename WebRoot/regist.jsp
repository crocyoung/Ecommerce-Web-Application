<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register </title>
</head>
<body>
<h3>Register</h3>
<script type="text/javascript">
    function changeImage()
    {
    // avoid get the page from cookie
    document.getElementById("image").src="${pageContext.request.contextPath }/CheckImageServlet?" 
                                                                   + new Date().getTime();
    }
    
</script>
<h3>${message}</h3>
<form action="${pageContext.request.contextPath}/RegistServlet" method="post" >
<table>
   <tr>
       <td>Username</td>
       <td><input type="text" name = "username"></td>
   <tr>
   <tr>
       <td>Password</td>
       <td><input type="text" name = "password"></td>
   <tr>
    <tr>
       <td>RePassword</td>
       <td><input type="text" name = "repassword"></td>
   <tr>
   <tr>
       <td>Email</td>
       <td><input type="text" name = "email"></td>
   <tr>
   <tr>
       <td>Nickname</td>
       <td><input type="text" name = "nickname"></td>
   <tr>
   <tr>
       <td>Enter code</td>
       <td><input type="text" name = "checkcode">
       <img src ="${pageContext.request.contextPath }/CheckImageServlet"  onclick="changeImage();" id="image" style="cursor:pointer" >
       </td>
   <tr>
   <tr>
       <td colspan="2">
       <input type="submit" value = "Register"></td>
   <tr>
</table>
</form>
</body>
</html>