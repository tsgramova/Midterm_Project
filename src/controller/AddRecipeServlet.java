package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBManager;
import db.RecipeDAO;
import products.Product;
import recipe.Recipe;
import user.UserException;
import user.UsersManager;

@WebServlet("/AddRecipeServlet")
public class AddRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int duration = Integer.parseInt(request.getParameter("duration"));
		int difficulty = Integer.parseInt(request.getParameter("difficulty"));
		int rating = Integer.parseInt(request.getParameter("rating"));
		int foodType = Integer.parseInt(request.getParameter("foodType"));
		ArrayList<String> productNames = new ArrayList<>();
		productNames.add(request.getParameter("product1"));
		productNames.add(request.getParameter("product2"));
		productNames.add(request.getParameter("product3"));
		productNames.add(request.getParameter("product4"));
		productNames.add(request.getParameter("product5"));
		productNames.add(request.getParameter("product6"));
		int quantity1 = Integer.parseInt(request.getParameter("quantity1"));
		int quantity2 = Integer.parseInt(request.getParameter("quantity2"));
		int quantity3 = Integer.parseInt(request.getParameter("quantity3"));
		int quantity4 = Integer.parseInt(request.getParameter("quantity4"));
		int quantity5 = Integer.parseInt(request.getParameter("quantity5"));
		int quantity6 = Integer.parseInt(request.getParameter("quantity6"));
		
		ArrayList<Product> products = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE name = ?;";
		HashMap<Product, Integer> recipeProducts = new HashMap<>();

		try {
			PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(sql);
			for (int i =0; i<6; i++) {
				if(productNames.get(i) !=null) {
					statement.setString(1, productNames.get(i));
					ResultSet res = statement.executeQuery();
					res.next();
					Product p = new Product(res.getInt("calories"), 
							res.getString("type"), 
							res.getString("name"));
					recipeProducts.put(p, Integer.parseInt(request.getParameter("quantity"+(i+1))));
				}
			}
			
			Recipe r = new Recipe(name, description, duration, difficulty, rating, foodType, recipeProducts);
			RecipeDAO.getInstance().addRecipe(r);
		} catch (SQLException e) {
			System.out.println("Some SQL exception occured.");
		} catch (Exception e) {
			System.out.println("Custom exception occured." + e);
		}
	}
}