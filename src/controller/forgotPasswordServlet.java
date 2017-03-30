package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UsersManager;

@WebServlet("/forgotpassword")
public class forgotPasswordServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String htmlFile="";
		if(!UsersManager.getInstance().getRegisteredUsers().containsKey(username)) {
			htmlFile="forgotPassword.html";
		} 
		else{
			htmlFile="password.jsp";
			String password = UsersManager.getInstance().getRegisteredUsers().get(username).getPassword();
			new MailSender(email, "Забравена парола за nomnom.bg", "Вашата парола е " + password + ". Заповядайте отново!");
		}
		request.getRequestDispatcher(htmlFile).forward(request, response);
		
	}

}
