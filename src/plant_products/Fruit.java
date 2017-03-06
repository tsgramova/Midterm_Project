package plant_products;

import products.Plant;

public class Fruit extends Plant {
	
	private enum FruitType {APPLE, ORANGE, BANANA};
	
	private FruitType type;
	

	protected Fruit(int callories, FruitType type) throws InvalidProductParamException {
		super(callories);
		this.type = type;
	}

}
