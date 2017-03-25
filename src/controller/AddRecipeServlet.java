package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import recipe.RecipeManager;

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
		
		String sql = "SELECT * FROM products WHERE name = ?;";
		HashMap<Product, Integer> recipeProducts = new HashMap<>();

			PreparedStatement statement;
			try {
				statement = DBManager.getInstance().getConnection().prepareStatement(sql);
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
				String htmlFile = "";
				if(RecipeManager.getInstance().validateRecipe(name, description, duration, difficulty, foodType,rating)) {
					RecipeDAO.getInstance().addRecipe(r);
					htmlFile = "Success.html";
				}
				else {
					htmlFile = "AddRecipeFailed.html";
				}
				RequestDispatcher view = request.getRequestDispatcher(htmlFile);
				view.forward(request, response);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

