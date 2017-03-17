package recipe;

import java.util.HashSet;
import DB.RecipeDAO;
import products.Product;

public class RecipeManager {

	private static RecipeManager instance;
	
	private RecipeManager(){}
	
	public static synchronized RecipeManager getInstance() {
	    if (instance == null)
	      instance = new RecipeManager();
	    return instance;
	  }
	
	public static synchronized void addNewRecipe(Recipe recipe, Product ...products) {
		RecipeDAO.getInstance().addRecipe(recipe, products);
	}
	
	public HashSet<Recipe> getRecipes() {
		return RecipeDAO.getInstance().getAllRecipes();
	}
}
