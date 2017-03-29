<%@page import="recipe.RecipeManager"%>
<%@page import="user.UsersManager"%>
<%@page import="db.RecipeDAO"%>
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
if(session.getAttribute("logged")!= null){
	boolean logged = (Boolean) request.getSession().getAttribute("logged");
	if(logged){
		//recipes

		User user =  UsersManager.getInstance().getRegisteredUsers().get(session.getAttribute("username")); 
		for(Recipe recipe : RecipeManager.getInstance().getRecipes()) {
			out.println(recipe.getName());
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