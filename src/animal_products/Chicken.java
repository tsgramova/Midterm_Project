package animal_products;

import products.Animal.Meat;

public class Chicken extends Meat {

	private enum ChickType { BREAST, FILLET, LEGS};
	
	private ChickType type;

	// two constructors:
			//one with the enum value
	protected Chicken(int callories, ChickType type) throws InvalidProductParamException {
		super(callories);
		this.type = type;
	}
			//another without it -> some recipes don't call for a specific type
	protected Chicken(int callories) throws InvalidProductParamException {
		super(callories);
	}
	
}
