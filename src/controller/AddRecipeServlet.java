package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import db.DBManager;
import products.Product;
import recipe.Recipe;
import recipe.RecipeManager;

@WebServlet("/AddRecipeServlet")
@MultipartConfig
public class AddRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int duration = Integer.parseInt(request.getParameter("duration"));
		int difficulty = Integer.parseInt(request.getParameter("difficulty"));
		int foodType = Integer.parseInt(request.getParameter("foodtype"));
		String [] productNames;
		productNames = request.getParameterValues("products");
		
		String sql = "SELECT * FROM products WHERE name = ?;";
		HashMap<Product, Integer> recipeProducts = new HashMap<>();
		

			PreparedStatement statement;
			try {
				statement = DBManager.getInstance().getConnection().prepareStatement(sql);
				for (int i =0; i<productNames.length; i++) {
					if(productNames[i] != null) {
						System.out.println(productNames[i]);
						statement.setString(1, productNames[i]);
						ResultSet res = statement.executeQuery();
						res.next();
						Product p = new Product(res.getInt("calories"), 
								res.getString("type"), 
								res.getString("name"));
						String quantity = "quantity" + (i+1);
						recipeProducts.put(p, Integer.parseInt(request.getParameter(quantity)));
					}
				}
				Part picture = request.getPart("picture");
				InputStream picStream = picture.getInputStream();
				Recipe r = new Recipe(name, description, duration, difficulty,0, foodType, recipeProducts,picStream);
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

