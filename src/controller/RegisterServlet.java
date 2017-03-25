package controller;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserException;
import user.UsersManager;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {  //a lot more to be done here....
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		String htmlFile="";
		if(UsersManager.getInstance().validateRegistration(username, password, firstname, lastname, email)) {
			try {
				UsersManager.getInstance().register(username, firstname,lastname, password, email);
				htmlFile= "Success.html";
				//forward to html saying successful registration
			} catch (UserException | SQLException e) { 
				System.out.println(e.getMessage());
			}
		}
			else {
				htmlFile = "RegisterFailed.html"; // forward to html saying the registration failed
			}
			RequestDispatcher view = request.getRequestDispatcher(htmlFile);
			view.forward(request, response);
		}
	}


