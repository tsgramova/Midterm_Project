<%@page import="java.util.Map.Entry"%>
<%@page import="user.User"%>
<%@page import="user.UsersManager"%>
<%@page import="java.util.Map"%>
<%@page import="products.Product"%>
<%@page import="java.util.HashSet"%>
<%@page import="recipe.Recipe"%>
<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>Всички твои рецепти</title>
</head>
<body>
<%
if(session.getAttribute("logged")==null || !(boolean) session.getAttribute("logged")) {%>
<jsp:forward page="login.jsp"></jsp:forward>
<% } else {

HashSet<Recipe> allRecipes = new HashSet<>();
User user = null;
for(Map.Entry<String, User> entry : UsersManager.getInstance().getRegisteredUsers().entrySet()) {
	if(session.getAttribute("username").equals(entry.getKey())) {
		user = entry.getValue();
		break;
	}
}
allRecipes.addAll(user.getAdded());

for(Recipe recipe : allRecipes) {%>
<u>Име:</u><%=recipe.getName()%>
<u>Трудност:</u><%=recipe.getDifficulty() %>
<u>Тип:</u><%=recipe.getType() %>
<u>Продукти:</u>
<%for(Map.Entry<Product, Integer> p : recipe.getProducts().entrySet()) {%>
<ul>
<li><%=p.getKey().getName() %> - <%=p.getValue() %></li>
</ul>
<%} %>
<u>Описание:</u><%=recipe.getDescription() %>
<u>Време за приготвяне:</u><%=recipe.getDuration() %>

<%}
}%>


</body>
</html>