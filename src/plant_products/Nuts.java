package plant_products;
import products.Product;

public class Nuts extends Product {

	private enum NutType { WALNUT, ALMOND, HAZELNUT };
	
	private NutType type;
	
	protected Nuts(int callories, NutType type) throws InvalidProductParamException {
		super(callories);
		this.type = type;
	}
	
}
