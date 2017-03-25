package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import products.Product;

public class ProductDAO {

	private static ProductDAO instance;
	  
	  private ProductDAO() {}
	  
	  public static synchronized ProductDAO getInstance() {
	    if (instance == null) {
	      instance = new ProductDAO();
	    }
	    return instance;
	  }
	  
	  public synchronized Set<Product> getAllProducts() {
		  Set<Product> products = new HashSet<Product>();
	    try {
	      Statement st = DBManager.getInstance().getConnection().createStatement();
	      ResultSet resultSet = st.executeQuery("SELECT product_id,name,calories FROM products;");
	      while (resultSet.next()) {
	    	  Product p = new Product(resultSet.getInt("calories"), 
	    			  			resultSet.getString("type"), 
	    			  			resultSet.getString("name")
	    			  			);
	    	  products.add(p);
	    	  p.setProductId(resultSet.getLong("product_id"));
	      }
	    }
	  
	    catch (SQLException e) {
	      System.out.println("Cannot make statement." + e.getMessage());
	      return products;
	    } 
	    catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    
	    System.out.println("Products loaded successfully");
	    return products;
	  }
	 
}
