package products;

public class Product {
	
	private long product_id;
	private String type;
	private String name;
	private int callories;

	
	public Product(int callories, String type, String name) throws InvalidProductParamException {
		if(callories > 0) {
			this.callories = callories;
		}
		else {
			throw new InvalidProductParamException("Invalid calories.");
		}
		
		if(type != null && !type.isEmpty()) {
			this.type = type;
		}
		else {
			throw new InvalidProductParamException("Invalid type.");
		}
		if(name != null && !name.isEmpty()) {
			this.name = name;
		}
		else {
			throw new InvalidProductParamException("Invalid name.");	
		}
	}
	
	public static class InvalidProductParamException extends Exception{
		private static final long serialVersionUID = 2881604593550182939L;
		public InvalidProductParamException(String message) {
			super(message);
		}
	
	}

	public long getProductId() {
		return this.product_id;
	}
	
	public void setProductId(long id) {
		if(id > 0)
			this.product_id = id; 
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	public int getCallories() {
		return callories;
	}
}


