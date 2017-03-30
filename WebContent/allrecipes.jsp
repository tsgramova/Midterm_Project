<%@page import="java.util.Map"%>
<%@page import="products.Product"%>
<%@page import="recipe.RecipeException"%>
<%@page import="recipe.RecipeManager"%>
<%@page import="java.util.HashSet"%>
<%@page import="recipe.Recipe"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Всички рецепти</title>
</head>
<%!
private HashSet<Recipe> getRecipes() throws RecipeException{
HashSet<Recipe> allRecipes = new HashSet<>();
allRecipes.addAll(RecipeManager.getInstance().getRecipes());
return allRecipes;
}
%>
<body>
<%for(Recipe recipe : getRecipes()) {%>
<h2>Име:</h2><%=recipe.getName()%>
<h2>Трудност:</h2><%=recipe.getDifficulty() %>
<h2>Тип:</h2><%=recipe.getType() %>
<h2>Продукти:</h2>
<%for(Map.Entry<Product, Integer> p : recipe.getProducts().entrySet()) {%>
<ul>
<li><%=p.getKey().getName() %> - <%=p.getValue() %></li>
</ul>
<%} %>
<h2>Описание:</h2><%=recipe.getDescription() %>
<h2>Време за приготвяне:</h2><%=recipe.getDuration() %>

<%}%>

</body>
</html>