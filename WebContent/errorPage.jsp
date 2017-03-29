<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
   <%@ page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Грешка</title>
</head>
<body>
<% if(response.getStatus()==404) { %>
		<img src="erro404.jpg">
<% } else { %>
		<img src="errorIceCream.jpg">
<% } %>>

<a href="index.html"><button>Върни ме на началната страница</button></a>
</body>
</html>