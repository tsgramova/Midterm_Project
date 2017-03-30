package controller;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBManager;

@WebServlet("/contactus")
public class ContactUsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		System.out.println(message);
		String sql = "INSERT INTO comments (email,comment) VALUES (?,?)";
		PreparedStatement ps;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, message);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String htmlFile = "Success.html";
		RequestDispatcher view = request.getRequestDispatcher(htmlFile);
		view.forward(request, response);
	}

}
