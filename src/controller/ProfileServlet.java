package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession ses = request.getSession();
		if(ses.getAttribute("logged")!= null){
			boolean logged = (Boolean) request.getSession().getAttribute("logged");
			if(logged){
				String html = "This is your profile with all your data.";
				response.getWriter().append(html);
			}
			else{
				response.sendRedirect("Login.html");
			}
		}
		else{
			response.sendRedirect("Login.html");
		}
	}


}
