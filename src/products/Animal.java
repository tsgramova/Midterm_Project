package products;

public abstract class Animal extends Product {

	// the mother-class of all animal products(meat, dairy, honey etc)
	
	protected Animal(int callories) throws InvalidProductParamException {
		super(callories);
	}

	public static abstract class Meat extends Animal{

		protected Meat(int callories) throws InvalidProductParamException {
			super(callories);
		}
		
	}
	
	public static abstract class Byproduct extends Animal{

		protected Byproduct(int callories) throws InvalidProductParamException {
			super(callories);
		}
		
	}
}
