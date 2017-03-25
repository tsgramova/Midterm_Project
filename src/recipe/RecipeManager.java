package recipe;

import java.util.HashMap;
import java.util.HashSet;
import db.RecipeDAO;
import products.Product;

public class RecipeManager {

	private static RecipeManager instance;
	
	private RecipeManager(){}
	
	public static synchronized RecipeManager getInstance() {
	    if (instance == null)
	      instance = new RecipeManager();
	    return instance;
	  }
	
	public static synchronized void addNewRecipe(Recipe recipe, HashMap<Product, Integer> products) {
		RecipeDAO.getInstance().addRecipe(recipe);
	}
	
	public static HashSet<Recipe> getRecipes() {
		return RecipeDAO.getInstance().getAllRecipes();
	}
	
	public boolean validateRecipe(String name, String description,int duration, int difficulty, int type, int rating) {

		if(name == null || name.isEmpty()) {
			return false;
		}
		
		if(description == null || description.isEmpty()) {
			return false;
		}
		
		if(duration > 0) {
			return false;
		}
		
		if(difficulty > 0 && difficulty < 4) {
			return false;
		}
		
		if(!(type == 1 || type == 2 || type == 3)) {
			return false;
		}
		
		if(rating > -1) {
			return false;
		}
		return true;
		
	}
}
