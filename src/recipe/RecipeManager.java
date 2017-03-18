package recipe;

import java.util.HashSet;
import db.RecipeDAO;

public class RecipeManager {

	private static RecipeManager instance;
	
	private RecipeManager(){}
	
	public static synchronized RecipeManager getInstance() {
	    if (instance == null)
	      instance = new RecipeManager();
	    return instance;
	  }
	
	public static synchronized void addNewRecipe(Recipe recipe) {
		RecipeDAO.getInstance().addRecipe(recipe);
	}
	
	public static HashSet<Recipe> getRecipes() {
		return RecipeDAO.getInstance().getAllRecipes();
	}
}