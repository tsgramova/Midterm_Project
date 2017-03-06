package animal_products;
import products.Animal.Meat;

public class Pork extends Meat {

	private enum PorkType { RIBS, NECK, DJOLAN};
	
	private PorkType type;	

	// two constructors:
			//one with the enum value
	protected Pork(int callories, PorkType type) throws InvalidProductParamException {
		super(callories);
	}
			//another without it -> some recipes don't call for a specific type
	protected Pork(int callories) throws InvalidProductParamException {
		super(callories);
	}
}
