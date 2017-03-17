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

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {  //a lot more to be done here....
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		if(UsersManager.getInstance().validateRegistration(username, password, firstname, lastname, email)) {
			try {
				UsersManager.getInstance().register(username, firstname,lastname, password, email);
				//forward to html saying successful registration or the index page
			} catch (UserException | SQLException e) { // forward to html saying the registration failed
				System.out.println(e.getMessage());
			}
			RequestDispatcher view = request.getRequestDispatcher("index.html");
			view.forward(request, response);
		}
	}

}
