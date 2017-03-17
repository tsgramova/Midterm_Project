package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import products.Product;

import java.sql.Array;
import java.sql.PreparedStatement;

import recipe.Recipe;


public class RecipeDAO {

	private static RecipeDAO instance;
	private static HashSet<Recipe> allRecipes;
	
	private RecipeDAO() {
		allRecipes = new HashSet<>();
	}
	
	public static synchronized RecipeDAO getInstance() {
		if(instance == null) 
			instance = new RecipeDAO();
		return instance;
	}
	
	public synchronized HashSet<Recipe> getAllRecipes() {
		if(allRecipes == null) {
			try {
		      Statement st = DBManager.getInstance().getConnection().createStatement();
		      ResultSet resultSet = st.executeQuery("SELECT name, description, duration, difficulty, rating, food_type "
		      		+ "FROM recipes ");
		      Array productsSet =(Array) st.executeQuery("SELECT name, calories FROM products ");
		      while (resultSet.next()) {
		    	  allRecipes.add(new Recipe(resultSet.getString("name"), 
		          resultSet.getString("description"), 
		          resultSet.getInt("duration"), 
		          resultSet.getInt("difficulty"),
		          resultSet.getDouble("rating"),
		          resultSet.getString("food_type"),
		          productsSet));
		      }
			  }
			catch (SQLException e) {
			    System.out.println("Something went wrong while trying to get all recipes!");
			    } 
			catch (Exception e) {
				System.out.println("DB exception");
			}
		}
		return allRecipes;
	}
	
	public synchronized void addRecipe(Recipe recipe, Product ...products ) {
		try {
			//needs fixing!!!!!!!
			Array productsSet = (Array)DBManager.getInstance().getConnection().prepareStatement("INSERT INTO products (name, calories) VALUES (?,?); ");
			for (int i = 0; i < products.length; i++) {
				
			}
		     PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(
		    		  "INSERT INTO recipes (name, description, duration, difficulty, rating, food_type, products) VALUES (?,?,?,?,?,?,?);");
		     st.setString(1, recipe.getName());
		     st.setString(2, recipe.getDescription());
		     st.setInt(3, recipe.getDuration());
		     st.setInt(4, recipe.getDifficulty());
		     st.setDouble(5, recipe.getRating());
		     st.setString(6, recipe.getType());
		     st.setArray(7, productsSet);
		     st.executeQuery();
		    } 
		    catch (SQLException e) {
		    	System.out.println("Recipe not added");
		    } 
		    catch (Exception e) {
		    	System.out.println("DB went bust");
		    }
		  }
}
