package plant_products;
import products.Plant;

public class Veggie extends Plant {

	private enum VegType { TOMATO, ONION, CARROT};
	
	private VegType type;
	
	protected Veggie(int callories, VegType type) throws InvalidProductParamException {
		super(callories);
		this.type = type;
	}
	
}
