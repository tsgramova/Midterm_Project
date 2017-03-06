package fridge_recipe;
import java.util.HashMap;
import products.Product;

public class Recipe {
	
	public enum FoodType { APPETIZER, MAIN_MEAL, DESSERT} 
	public enum Difficulty {EASY, MEDIUM, DIFFICULT}
	public enum Duration { FIFTEEN(15),THIRTY(30),FOURTY_FIVE(45),SIXTY(60),NINETY(90),MORE_THAN_NINETY(120);
		private int value;
		private Duration(int value) {
			this.value=value;
		}
	}
	private String name;
	private String description;
	private Duration duration; //drop down menu with 15,30,60,90,>90 minutes
	private Difficulty difficulty;
	private double rating; //users only vote once
	private HashMap<Product, Integer> products;
	private FoodType type;
	private int voters;
	
	
	
	
	
	public Recipe(String name, String description, Duration duration, Difficulty difficulty,
			HashMap<Product, Integer> products, FoodType type) throws RecipeException {
		
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
		this.duration = duration;
		this.difficulty = difficulty;
		if(products!=null) {
			this.products = products;
		}
		else {
			throw new RecipeException("Invalid products!");
		}
		this.type = type;
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
	
	public void rate(int x) {
		this.voters+=1;
		this.rating+=x;
		this.rating/=voters;
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
	
}
