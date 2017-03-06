package animal_byproducts;

import products.Animal.Byproduct;

public class Honey extends Byproduct {

	private enum HoneyType {LEVANDER, PINE, LINDEN};
	
	private HoneyType type;	
	
	// two constructors:
			//one with the enum value
	protected Honey(int callories, HoneyType type) throws InvalidProductParamException {
		super(callories);
		this.type = type;
	}
			//another without it -> some recipes don't call for a specific type
	protected Honey(int callories) throws InvalidProductParamException {
		super(callories);
	}

}
