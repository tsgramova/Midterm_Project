package animal_byproducts;

import products.Animal.Byproduct;

public class Egg extends Byproduct {

	private enum EggType {FREERANGE, OSTRICH, CHICKEN};
	
	private EggType type;	

	// two constructors:
			//one with the enum value
	protected Egg(int callories, EggType type) throws InvalidProductParamException {
		super(callories);
	}
			//another without it -> some recipes don't call for a specific type
	protected Egg(int callories) throws InvalidProductParamException {
		super(callories);
	}
}
