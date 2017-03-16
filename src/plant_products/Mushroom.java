package plant_products;
import products.Plant;

public class Mushroom extends Plant {

	private enum MushType { PECHURKA, PACHIKRAK };	
	
	private MushType type;
	// two constructors:
		//one with the enum value
	
	protected Mushroom(int callories, MushType type) throws InvalidProductParamException {
		super(callories);
		this.type = type;
	}
		//another without it -> some recipes don't call for a specific type
	
	protected Mushroom(int callories) throws InvalidProductParamException {
		super(callories);
	}
}
