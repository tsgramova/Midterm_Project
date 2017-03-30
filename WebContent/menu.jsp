<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<style>
body {
    background-color: lightgrey;
}
button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 18%;
}

button:hover {
    opacity: 0.8;
}


.imgcontainer {
    text-align: center;
    margin: 10px 0 10px 0;
}

img.avatar {
    width: 10%;
    height:5%;
    border-radius: 1px;
}

.container {
    padding: 16px;
}

.app-button{
     list-style: none;
     text-align: center;
     background-color: #01AAAD;
     width: 100px;margin:0;
     line-height: 60px;
     float: left;
   }
</style>
</head>
<body>
<div class="imgcontainer">
    <img src="nomnom.png" alt="Avatar" class="avatar">
  </div>
<div>
<a href="profile.jsp"><button type="submit" method="get" >Моят профил</button></a>
<a href="login.jsp"><button>Влез в профил</button></a>
<a href="register.jsp"><button>Регистрирай се</button></a>
<a href="allrecipes.jsp"><button>Рецепти</button></a>
<a href="contactus.html"><button>Свържете се с нас</button></a>

</div>

</body>
</html>
