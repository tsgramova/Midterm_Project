package plant_products;

import products.Plant;

public class Grain extends Plant {
	
	private enum GrainType { RICE, BEANS, LENTILS };
	
	private GrainType type;

	protected Grain(int callories, GrainType type) throws InvalidProductParamException {
		super(callories);
		this.type = type;
	}

}
