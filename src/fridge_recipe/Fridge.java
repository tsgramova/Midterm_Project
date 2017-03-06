package fridge_recipe;

import java.util.HashSet;

import products.Product;

public class Fridge {  //The fridge should look like a list with all products that the user have
	//
	
	private HashSet<Product> products;
	//drop down menu to choose products(from predefined list) and add them 
	
	public void whatShouldIcook() {
		//return the recipes sorted in some way
	}
	
	public HashSet<Product> getProducts() {
		return this.products;
	}
	
	public void addProduct(Product p) {
		this.products.add(p);
	}

}
