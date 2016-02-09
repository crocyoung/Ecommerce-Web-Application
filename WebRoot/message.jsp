<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="5 ;url = ${pageContext.servletContext.contextPath}/index.jsp">
<title>Insert title here</title>
</head>
<body>

      ${message},   The page is being directed to E-store main page in <font color=orange id="number"></font> seconds，<br/>    
      Go to  <a href="${pageContext.request.contextPath}/index.jsp">E-Store Main Page</a> 


<script>
var start = 5;
var step = -1;
function count()
{
	document.getElementById("number").innerHTML = start;
	start += step;
	if(start < 0)
		start = 5;
	setTimeout("count()",1000);
}
window.onload = count;
</script>

</body>
</html>