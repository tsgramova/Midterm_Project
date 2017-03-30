<%@page import="java.util.Scanner"%>
<%@page import="java.io.PrintWriter"%>
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
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" href="nomnom.png">
<title>Добави рецепта</title>
</head>
<style>
body {
    background-color: lightgrey;
}
form {
    border: 3px solid #f1f1f1;
}
.style1 {
	width: 20%;
}

input[type=text] {
    width: 50%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 25%;
}

button:hover {
    opacity: 0.8;
}

.cancelbtn {
    width: auto;
    padding: 10px 18px;
    background-color: #f44336;
}

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

</style>
<body>
<%@ include file="menu.jsp" %>
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

for(Recipe recipe : allRecipes) {
String image = "";

Scanner sc = new Scanner(recipe.getPicture());
while(sc.hasNext()) {
	image = image + sc.next();
}
sc.close();
%>
<form action="image.jsp">
  <input type="hidden" name="image" value="image">
</form>
<%@ include file="image.jsp" %>
<u>Име:</u><%=recipe.getName()%>
<u>Трудност:</u><%=(recipe.getDifficulty() == 1?"лесна":recipe.getDifficulty() == 2?"средна":"трудна")%>
<u>Тип:</u><%=(recipe.getDifficulty() == 1?"предястие":recipe.getDifficulty() == 2?"основно":"десерт"%>
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