package recipe;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import db.RecipeDAO;
import products.Product;

public class RecipeManager {

	private static RecipeManager instance;
	private static HashSet<Recipe> allRecipes;

	
	private RecipeManager() throws RecipeException{
		allRecipes = new HashSet<>();
		allRecipes.addAll(RecipeDAO.getInstance().getAllRecipes());
	}
	
	public static synchronized RecipeManager getInstance() throws RecipeException {
	    if (instance == null)
	      instance = new RecipeManager();
	    return instance;
	  }
	
	public synchronized void addNewRecipe(Recipe recipe,String username) throws SQLException, Exception {
		try{
			RecipeDAO.getInstance().addRecipe(recipe,username);
			allRecipes.add(recipe);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException("problem in db");
		}
		
	}
	
	public Set<Recipe> getRecipes() {
		return Collections.unmodifiableSet(allRecipes);
	}
	
	public boolean validateRecipe(String name, String description,int duration, int difficulty, int type, int rating) {
		for(Recipe r : allRecipes) {
			if(r.getName().equals(name)) {
				return false;
			}
		}
		if(name == null || name.isEmpty()) {
			return false;
		}
		
		if(description == null || description.isEmpty()) {
			return false;
		}
		
		if(duration < 0) {
			return false;
		}
		
		if(difficulty < 0 || difficulty > 3) {
			return false;
		}
		
		if(!(type == 1 || type == 2 || type == 3)) {
			return false;
		}
		
		if(rating < 0) {
			return false;
		}
		return true;
		
	}
}
