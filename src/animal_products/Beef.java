package animal_products;
import products.Animal.Meat;

public class Beef extends Meat {

	private enum BeefType { STEAK, FILET, RIBS};
	
	private BeefType type;

	// two constructors:
			//one with the enum value
	protected Beef(int callories, BeefType type) throws InvalidProductParamException {
		super(callories);
	}
			//another without it -> some recipes don't call for a specific type
	
	protected Beef(int callories) throws InvalidProductParamException {
		super(callories);
	}
}
