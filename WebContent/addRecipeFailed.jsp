<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ page errorPage="errorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

	<%if (session.getAttribute("logged") != null && (Boolean)session.getAttribute("logged")) { %>
	<form action="AddRecipeServlet" method = "POST" enctype="multipart/form-data">
	 	<h2> Въвели сте грешна рецепта. Опитайте пак!</h2>
	  <div class="container">
	  	<h2 >Добавяне на нова рецепта</h2>
	    <label><b>Име</b></label></br>
	    <input type="text" placeholder="Въведете име на рецептата" name="name" required></br>
	    <label><b>Описание</b></label></br>
	    <input type="text" placeholder="Въведете описание" name="description" required></br>
	    <label><b>Време за приготвяне в минути</b></label></br>
	    <input type="text" placeholder="Въведете време за приготвяне" name="duration" required></br>
	     <label><b>Трудност</b></label></br>
	    <input type="text" placeholder="Въведете трудност(лесно=1,средно=2,трудно=3)" name="difficulty" required></br>
	    <label><b>Тип на ястието</b></label></br>
	    <input type="text" placeholder="Въведете тип на ястието(първо=1,второ=2,трето=3)" name="foodtype" required> </br> 
	    <label><b>Продукти:</b></label>
	    </br>
	    
	    <input type="checkbox" name="products" value="яйца"> Яйца
	    <input type="checkbox" name="products" value="кисело мляко"> Кисело мляко
	    <input type="checkbox" name="products" value="пиле"> Пилешко месо
	    <input type="checkbox" name="products" value="домати"> Домати
	    <input type="checkbox" name="products" value="краставици"> Краставици
	    <input type="checkbox" name="products" value="сирене"> Сирене 
	   	<input type="checkbox" name="products" value="картофи"> Картофи 
	    <input type="checkbox" name="products" value="кайма"> Кайма
	    <input type="checkbox" name="products" value="лук"> Лук </br>
	    	    
	    
	        <label><b>Количество:</b></label>
	        </br>
	    <input type="text" name="quantity1" style="width:5%;" >
	    <input type="text" name="quantity2" style="width:11%;">
	    <input type="text" name="quantity3" style="width:11%;">
	    <input type="text" name="quantity4" style="width:5%;" >
	    <input type="text" name="quantity5" style="width:10%;">
	    <input type="text" name="quantity6" style="width:5%;" >
   	    <input type="text" name="quantity7" style="width:5%;" >
   	    <input type="text" name="quantity8" style="width:5%;" >
   	    <input type="text" name="quantity9" style="width:5%;" >
	    
	    </br>
	    <label><b>Снимка</b></label></br>
	    <input type="file"  placeholder="снимка" name="picture" required >
	    </br>
	    <button type="submit">Добави рецепта</button>
	  </div>
	</form>
	<form method="link" action="index.jsp">
		<div class="container" style="background-color:#f1f1f3">
  	 	 <button type="submit" class="cancelbtn">Отказ</button>
 		 </div>
	</form>
	<%} else { %>
	<jsp:forward page="login.jsp"></jsp:forward> 
	<%} %>
  </body>

</html>