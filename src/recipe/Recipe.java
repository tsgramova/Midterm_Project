package recipe;
import java.util.HashMap;
import products.Product;

public class Recipe {
	
	private long recipe_id;
	private String name;
	private String description;
	private int duration; //drop down menu with 15,30,60,90,>90 minutes
	private int difficulty;
	private double rating; //users only vote once
	private HashMap<Product, Integer> products;
	private int foodType;
	
	//food type changed to int for easier access
	public Recipe(String name, String description, int duration, int difficulty, double rating, int foodType, HashMap<Product, Integer> products) throws RecipeException {
		
		if(checkName(name)) {
			this.name = name;
		}
		else {
			throw new RecipeException("Invalid name!");
		}
		
		if(checkDescription(description)) {
			this.description = description;
		}
		else {
			throw new RecipeException("Invalid description!");
		}
		
		if(duration > 0) {
			this.duration = duration;
		}
		else {
			throw new RecipeException("Invalid duration!");
		}
		
		if(difficulty > 0 && difficulty < 4) {
			this.difficulty = difficulty;
		}
		else {
			throw new RecipeException("Invalid difficulty!");
		}
		
		if(foodType==1 || foodType==2 || foodType==3) {
			this.foodType = foodType;
		}
		else {
			throw new RecipeException("Invalid food type!");
		}
		
		this.products = new HashMap<>();
		if(products!=null) {
			this.products.putAll(products);
		}
	}

	public Recipe reviewRecipe () {
		return this;
	}
	
	public void cook () {
		System.out.println("You have cooked this.");
	}
	
	public void addToFavorites() {
		System.out.println("You have added this to favorites.");
	}
	

	
	private boolean checkName(String name) {
		if(name!=null && !name.isEmpty()) {
			return true;
		}
		return false;
	}
	
	private boolean checkDescription(String description) {
		if(description!=null && !description.isEmpty()) {
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getDuration() {
		return duration;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public double getRating() {
		return rating;
	}

	public HashMap<Product, Integer> getProducts() {
		return products;
	}

	public int getType() {
		return foodType;
	}
	
	public long getRecipeId() {
		return this.recipe_id;
	}
	
	public void setRecipeId(long id) {
		this.recipe_id = id;
	}
	
}
