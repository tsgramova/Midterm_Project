package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import products.Product;
import java.sql.PreparedStatement;
import recipe.Recipe;


public class RecipeDAO {

	private static RecipeDAO instance;
	
	private RecipeDAO() {
	}
	
	public static synchronized RecipeDAO getInstance() {
		if(instance == null) 
			instance = new RecipeDAO();
		return instance;
	}
	
	public synchronized Set<Recipe> getAllRecipes() {
		HashSet<Recipe> recipes = new HashSet<>();
			try {
		      Statement st = DBManager.getInstance().getConnection().createStatement();
		      ResultSet resultSet = st.executeQuery("SELECT recipe_id, name, description, duration, difficulty, rating, food_type,picture "
		      		+ "FROM recipes ");
		      while (resultSet.next()) {
		    	//  Statement productSt = DBManager.getInstance().getConnection().createStatement();
		    	  PreparedStatement productSt = DBManager.getInstance().getConnection().prepareStatement("SELECT p.name, p.type, p.calories, rp.quantity FROM products p JOIN recipes_has_products rp ON p.product_id=rp.product_id WHERE rp.recipe_id = ?");
		    	  productSt.setLong(1, resultSet.getLong("recipe_id"));
		    	  ResultSet productRS = productSt.executeQuery();
		    	  
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
		          products,resultSet.getBinaryStream("picture"));
		    	  recipe.setRecipeId(resultSet.getLong("recipe_id"));
		    	  recipes.add(recipe);
		      }
			}
			catch (SQLException e) {
			    System.out.println("Something went wrong while trying to get all recipes!");
			    e.printStackTrace();
			    } 
			catch (Exception e) {
				System.out.println("DB exception");
			}
		
		return Collections.unmodifiableSet(recipes);
	}
	
	public synchronized void addRecipe(Recipe recipe) {
		try {
			//first insert recipe into db
			String sql = "INSERT INTO recipes (name, description, duration, difficulty, rating, food_type, picture) VALUES (?,?,?,?,?,?,?);";
		     PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		     st.setString(1, recipe.getName());
		     st.setString(2, recipe.getDescription());
		     st.setInt(3, recipe.getDuration());
		     st.setInt(4, recipe.getDifficulty());
		     st.setDouble(5, recipe.getRating());
		     st.setInt(6, recipe.getType());
		     st.setBinaryStream(7, recipe.getPicture());
		     st.executeUpdate();
			// ResultSet res = st.getGeneratedKeys();
			//res.next();
			//long recipe_id = res.getLong(1);
			//recipe.setRecipeId(recipe_id);	
				HashMap<Product, Integer> products = recipe.getProducts();
		     //then insert  into recipe_has_products table
				for(Entry<Product, Integer> entry : products.entrySet()) {
		     PreparedStatement productSt = DBManager.getInstance().getConnection().prepareStatement(
		    		  "INSERT INTO recipes_has_products (recipe_id, product_id, quantity) VALUES ((SELECT recipe_id from recipes r WHERE r.name = ?) , (SELECT product_id from products WHERE name = ?),?);");
		     
		    	 productSt.setString(1, recipe.getName());
		    	 productSt.setString(2,entry.getKey().getName());
		    	 productSt.setInt(3, entry.getValue());
		    	 productSt.executeUpdate();
		     }
		    } 
		    catch (SQLException e) {
		    	e.printStackTrace();
		    	System.out.println("Recipe not added");
		    } 
		    catch (Exception e) {
		    	System.out.println("DB went bust");
		    }
		  }
}
