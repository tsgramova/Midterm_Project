<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" href="nomnom.png">
<title>Вход</title>
<style>

body {
    background-color: lightgrey;
}
form {
    border: 2px solid #f1f1f1;
}

input[type=text], input[type=password] {
    width: 30%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
    left:100px;
}

button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 4px 0;
    border: none;
    cursor: pointer;
    width: 20%;
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
    width: 40%;
    border-radius: 8px;
}

.container {
    padding: 1px;
}

span.psw {
    float: left;
    padding-top: 8px;
}
.app-button{
     list-style: none;
     text-align: center;
     background-color: #01AAAD;
     width: 150px;margin:0;
     line-height: 60px;
     float: left;
   }


</style>
</head>
<body>
<%@ include file="menu.jsp" %>

<% if (session.getAttribute("logged") != null && (Boolean)session.getAttribute("logged")) { %>
	<jsp:forward page="profile.jsp"></jsp:forward>
<%} else {%>
	<form class= "form-signin" action="LoginServlet" method = "POST">
	  <div class="container">
	    <label><b>Потребителско име</b></label> </br>
	    <input type="text" placeholder="Въведете потребителско име" name="username" required>
		</br>
	    <label><b>Парола</b></label> </br>
	    <input type="password" placeholder="Въведете парола" name="password" required>
	    </br>
	    <input type="checkbox" checked="checked"> Запомни ме
	    </br>
	    
	    <button type="submit">Вход</button>
	    
	   <h2> Забравена <a href="forgotPassword.html">парола?</a></h2>
	  </div>	  
	</form>
	
	<a href="index.jsp"><button>Върни ме на началната страница</button></a>

	  <% } %>
</head>
<body>

</body>
</html>