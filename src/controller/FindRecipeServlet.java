package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import db.DBManager;
import db.RecipeDAO;
import products.Product;
import recipe.Recipe;

/**
 * Servlet implementation class FindRecipeServlet
 */
@WebServlet("/FindRecipeServlet")
public class FindRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checkedProducts = request.getParameterValues("checkedProducts");
		ArrayList<Product> products = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE name = ?;";
		
		try {
			PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(sql);
			
			
			
			for (String productName : checkedProducts) {
				statement.setString(1, productName);
				ResultSet res = statement.executeQuery();
				res.next();
				products.add(new Product(res.getInt("calories"), 
						res.getString("type"), 
						res.getString("name")));
			}
			
			ArrayList<Recipe> desiredRecipes = new ArrayList<>();
			boolean containsProducts = true;
			
			for(Recipe recipe : RecipeDAO.getInstance().getAllRecipes()) {
				for(Product product : products) {
					if(!recipe.getProducts().containsKey(product)) {
						containsProducts = false;
					}
				}
				if(containsProducts) {
					desiredRecipes.add(recipe);
				}
			}
		
			//refers to html page with all desired recipes

			
		} catch (SQLException e) {
			System.out.println("Some SQL exception occured.");
		} catch (Exception e) {
			System.out.println("Custom exception occured." + e);
		}
		
		
	}


}
