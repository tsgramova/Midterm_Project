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
		int foodType = Integer.parseInt(request.getParameter("foodtype"));
		String [] productNames;
		productNames = request.getParameterValues("product");
		
		String sql = "SELECT * FROM products WHERE name = ?;";
		HashMap<Product, Integer> recipeProducts = new HashMap<>();

			PreparedStatement statement;
			try {
				statement = DBManager.getInstance().getConnection().prepareStatement(sql);
				for (int i =1; i<productNames.length-1; i++) {
					if(productNames[i+1] != null) {
						statement.setString(1, productNames[i]);
						ResultSet res = statement.executeQuery();
						res.next();
						Product p = new Product(res.getInt("calories"), 
								res.getString("type"), 
								res.getString("name"));
						String quantity = "quantity" + i;
						recipeProducts.put(p, Integer.parseInt(request.getParameter(quantity)));
					}
				}
				
				Recipe r = new Recipe(name, description, duration, difficulty,0, foodType, recipeProducts);
				String htmlFile = "";
				if(RecipeManager.getInstance().validateRecipe(name, description, duration, difficulty, foodType, 0)) {
					RecipeManager.getInstance().addNewRecipe(r);
					htmlFile = "Success.html";
				}
				else {
					htmlFile = "AddRecipeFailed.html";
				}
				RequestDispatcher view = request.getRequestDispatcher(htmlFile);
				view.forward(request, response);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

