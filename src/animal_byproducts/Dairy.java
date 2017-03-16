package animal_byproducts;

import products.Animal.Byproduct;

public class Dairy extends Byproduct {

	protected Dairy(int callories) throws InvalidProductParamException {
		super(callories);
		// TODO Auto-generated constructor stub
	}

	//dairy is a bit fuzzy in the class structure
	//only one constructor with enum type
	
	//??? probably inner classes for all the subtypes
	//(cheese - blue, white, yellow etc; milk - cow milk, goat milk, skim milk)
}
