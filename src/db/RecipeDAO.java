package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import products.Product;
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
		      ResultSet resultSet = st.executeQuery("SELECT recipe_id, name, description, duration, difficulty, rating, food_type "
		      		+ "FROM recipes ");
		      while (resultSet.next()) {
		    	  Statement productSt = DBManager.getInstance().getConnection().createStatement();
		    	  ResultSet productRS = st.executeQuery("SELECT p.name, p.type, p.callories, rp.quantity FROM products p JOIN recipes_has_products rp ON p.product_id=rp.product_id WHERE p.recipe_id = " 
		    	  + resultSet.getLong("recipe_id"));
		    	  
		    	//temp collection for the recipes products
		  		HashMap<Product, Integer> products = new HashMap<>();
		  		while(productRS.next()) {
			  		products.put(new Product(productRS.getInt("calories"),
			  						productRS.getString("type"), 
			  						productRS.getString("name")), 
			  						productRS.getInt("quantity"));
		  		}
		    	  
		    	  Recipe recipe = new Recipe(resultSet.getString("name"), 
		          resultSet.getString("description"), 
		          resultSet.getInt("duration"), 
		          resultSet.getInt("difficulty"),
		          resultSet.getDouble("rating"),
		          resultSet.getInt("food_type"),
		          products);
		
		    	  recipe.setRecipeId(resultSet.getLong("recipe_id"));
		    	  allRecipes.add(recipe);
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
	
	public synchronized void addRecipe(Recipe recipe) {
		try {
			//first insert recipe into db
			
		     PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(
		    		  "INSERT INTO recipes (name, description, duration, difficulty, rating, food_type) VALUES (?,?,?,?,?,?);");
		     st.setString(1, recipe.getName());
		     st.setString(2, recipe.getDescription());
		     st.setInt(3, recipe.getDuration());
		     st.setInt(4, recipe.getDifficulty());
		     st.setDouble(5, recipe.getRating());
		     st.setInt(6, recipe.getType());
		     st.executeUpdate();
		     
		     ResultSet res = st.getGeneratedKeys();
				res.next();
				long recipe_id = res.getLong(1);
				recipe.setRecipeId(recipe_id);	
		     
				HashMap<Product, Integer> products = recipe.getProducts();
		     //then insert  into recipe_has_products table
		     PreparedStatement productSt = DBManager.getInstance().getConnection().prepareStatement(
		    		  "INSERT INTO recipe_has_products (recipe_id, product_id, quantity VALUES (?,?,?);");
		     for(Entry<Product, Integer> entry : products.entrySet()) {
		    	 productSt.setLong(1, recipe_id);
		    	 productSt.setLong(2, entry.getKey().getProductId());
		    	 productSt.setInt(3, entry.getValue());
		     }
		    } 
		    catch (SQLException e) {
		    	System.out.println("Recipe not added");
		    } 
		    catch (Exception e) {
		    	System.out.println("DB went bust");
		    }
		  }
}
