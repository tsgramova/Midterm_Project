package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UsersManager;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if(UsersManager.getInstance().validateLogin(username, password)){
			HttpSession session = request.getSession();
			
			session.setAttribute("username", username);
			session.setAttribute("logged", true);
			response.sendRedirect("Success.html");
			

		}else{
			response.sendRedirect("LoginFailed.html");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("logged", false);
		resp.sendRedirect("index.html");
	}


}