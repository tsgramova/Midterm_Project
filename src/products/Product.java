package products;

public abstract class Product {
	//add fields and constructors
	private int callories;

	//this is the abstract mother-class of all our products
	protected Product(int callories) throws InvalidProductParamException {
		if(callories > 0)
			this.callories = callories;
		else
			throw new InvalidProductParamException();
	}
	
	public static class InvalidProductParamException extends Exception{
		//print a message on the web page
	}
}


