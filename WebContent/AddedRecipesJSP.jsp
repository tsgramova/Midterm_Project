<%@page import="java.io.IOException"%>
<%@page import="recipe.Recipe"%>
<%@page import="user.User"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
chgvjbknl
<%
HttpSession ses = request.getSession();
if(ses.getAttribute("logged")!= null){
	boolean logged = (Boolean) request.getSession().getAttribute("logged");
	if(logged){
		//recipes

		for(User user : db.UserDao.getInstance().getAllUsers()) {
			if(user.getUserName().equals(request.getSession().getAttribute("username"))) {
				for(Recipe recipe : user.getAdded()) {
					out.println("Name" + recipe.getName());
					out.println("Type" + recipe.getType());
					out.println("Description" + recipe.getDescription());
					out.println("Duration" + recipe.getDuration());
					out.println("Rating" + recipe.getRating());
					
				}
			}
					
		}
	}
	
	else{
		response.sendRedirect("Login.html");
	}
}
else{
	response.sendRedirect("Login.html");
}
%>
</body>
</html>