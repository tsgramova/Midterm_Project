package plant_products;
import products.Plant;

public class HerbsSpices extends Plant {
	
	private enum HSType {SALT, BLACKPEPPER, OREGANO};
	
	private HSType type;

	protected HerbsSpices(int callories, HSType type) throws InvalidProductParamException {
		super(callories);
		this.type = type;
	}

	
}
