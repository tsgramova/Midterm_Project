package products;

public class Product {
	
	private int callories;
	private String type;
	private String name;

	//insert column "type" into db
	//users can't create new products
	
	protected Product(int callories, String type, String name) throws InvalidProductParamException {
		if(callories > 0)
			this.callories = callories;
		else
			throw new InvalidProductParamException();
		
		if(type != null && !type.isEmpty()) 
			this.type = type;
		else
			throw new InvalidProductParamException();
		
		if(name != null && !name.isEmpty()) 
			this.name = name;
		else
			throw new InvalidProductParamException();	
	}
	
	public static class InvalidProductParamException extends Exception{
		//print a msg into the console or log file
	}
}


