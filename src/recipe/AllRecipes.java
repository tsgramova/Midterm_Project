package recipe;

import java.util.Collections;
import java.util.HashSet;

public class AllRecipes {
	
	
	private static AllRecipes instance=null;
	private static HashSet<Recipe> recipes;
	
	private AllRecipes() {
		this.recipes=new HashSet<>();
	}
	
	public static AllRecipes getInstance() {
		if(AllRecipes.instance ==null) {
			AllRecipes.instance= new AllRecipes();
		}
		return AllRecipes.instance;
	}
	
	public static void addNewRecipe(Recipe r) {
		AllRecipes.recipes.add(r); //admin checks the recipe and adds it
	}
	
	public HashSet<Recipe> getRecipes() {
		return (HashSet<Recipe>) Collections.unmodifiableSet(AllRecipes.recipes);
	}
 	
	

}
