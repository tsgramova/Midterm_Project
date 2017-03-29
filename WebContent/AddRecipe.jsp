<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
    width: 100%;
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

.imgcontainer {
    text-align: center;
    margin: 100px 0 12px 0;
}

img.avatar {
    width: 20%;
    border-radius: 8px;
}

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

</style>
</head>
<body>
	<%if (session.getAttribute("logged") != null || (Boolean)session.getAttribute("logged")) { %>
	<form action="AddRecipeServlet" method = "POST" enctype="multipart/form-data">
	  <div class="imgcontainer">
	    <img src="nomnom.png" alt="Avatar" class="avatar">
	  </div>
	
	  <div class="container">
	  	<h2 >Добавяне на нова рецепта</h2>
	    <label><b>Име</b></label>
	    <input type="text" placeholder="Въведете име на рецептата" name="name" required>
	    <label><b>Описание</b></label>
	    <input type="text" placeholder="Въведете описание" name="description" required>
	    <label><b>Време за приготвяне в минути</b></label>
	    <input type="text" placeholder="Въведете време за приготвяне" name="duration" required>
	     <label><b>Трудност</b></label>
	    <input type="text" placeholder="Въведете трудност от 1 до 3" name="difficulty" required>
	    <label><b>Тип на ястието</b></label>
	    <input type="text" placeholder="Въведете тип на ястието(първо,второ,трето)" name="foodtype" required> 
	    <label><b>Продукти:</b></label>
	    </br>
	    
	    <input type="checkbox" name="products" value="egg"> Яйца
	    <input type="checkbox" name="products" value="yogurt"> Кисело мляко
	    <input type="checkbox" name="products" value="chicken"> Пилешко месо
	    <input type="checkbox" name="products" value="tomatoes"> Домати
	    <input type="checkbox" name="products" value="cucumbers"> Краставици
	    <input type="checkbox" name="products" value="cheese"> Сирене </br>
	        <label><b>Количество:</b></label>
	        </br>
	    <input type="text" name="quantity1" style="width:8%;" >
	    <input type="text" name="quantity2" style="width:13%;">
	    <input type="text" name="quantity3" style="width:13%;">
	    <input type="text" name="quantity4" style="width:8%;" >
	    <input type="text" name="quantity5" style="width:15%;">
	    <input type="text" name="quantity6" style="width:8%;" >
	    </br>
	    <input type="file"  placeholder="снимка" name="picture" required >
	    </br>
	    <button type="submit">Добави рецепта</button>
	  </div>
	</form>
	<form method="link" action="index.html">
		<div class="container" style="background-color:#f1f1f3">
  	 	 <button type="submit" class="cancelbtn">Отказ</button>
 		 </div>
	</form>
	<%} else { %>
	<jsp:forward page="Login.html"></jsp:forward> 
	<%} %>
  </body>
</html>
</body>
</html>