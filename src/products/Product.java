package products;

public class Product {
	
	private long product_id;
	private String type;
	private String name;
	private int callories;

	//insert column "type" into db
	//users can't create new products
	
	public Product(int callories, String type, String name) throws InvalidProductParamException {
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

	public long getProductId() {
		return this.product_id;
	}
	
	public void setProductId(long id) {
		if(id > 0)
			this.product_id = id; 
	}
}


