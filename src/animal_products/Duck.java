package animal_products;
import products.Animal.Meat;

public class Duck extends Meat {

	private enum DuckType {BREAST, FILLET, LEGS};

	private DuckType type;

	// two constructors:
			//one with the enum value
	protected Duck(int callories, DuckType type) throws InvalidProductParamException {
		super(callories);
	}
			//another without it -> some recipes don't call for a specific type
	protected Duck(int callories) throws InvalidProductParamException {
		super(callories);
	}
}
