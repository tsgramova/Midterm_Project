package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import user.User;

public class UserDao {
	
  private static UserDao instance;
  
  private UserDao() {}
  
  public static synchronized UserDao getInstance() {
    if (instance == null) {
      instance = new UserDao();
    }
    return instance;
  }
  
  public synchronized Set<User> getAllUsers() {
	  Set<User> users = new HashSet<User>();
    try {
      Statement st = DBManager.getInstance().getConnection().createStatement();
      ResultSet resultSet = st.executeQuery("SELECT users_id,username,first_name,last_name,email,password,role FROM users;");
      while (resultSet.next()) {
    	  User u = new User(resultSet.getString("username"), 
    			  			resultSet.getString("first_name"), 
    			  			resultSet.getString("last_name"), 
    			  			resultSet.getString("email"), 
    			  			resultSet.getString("password"),  //TODO hash pass
    			  			resultSet.getString("role").equals("admin"));
    	  users.add(u);
    	  u.setUserId(resultSet.getLong("users_id"));
    	  
      }
      
    }
    catch (SQLException e) {
      System.out.println("Cannot make statement." + e.getMessage());
		return Collections.unmodifiableSet(users);
    } 
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
    System.out.println("Users loaded successfully");
	return Collections.unmodifiableSet(users);
  }
  
  public synchronized void saveUser(User user) {
      PreparedStatement st;
	try {
		st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO users (username,first_name,last_name,email,password,role) VALUES (?,?,?,?,?,?);");
	    st.setString(1, user.getUserName());
	    st.setString(2, user.getFirstName());
	    st.setString(3, user.getLastName());
	    st.setString(4, user.getEmailAddress());
	    st.setString(5, user.getPassword()); //TODO hash pass
	    st.setString(6, user.isAdmin() ? "admin" : "user");
	    st.executeUpdate();
	    ResultSet res = st.getGeneratedKeys();
		res.next();
		long id = res.getLong(1);
		user.setUserId(id);
	    System.out.println("User added successfully");
	    res.close();
	    st.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
  }
  
  public synchronized void deleteUser(User u) {
	  PreparedStatement prepSt;
	  try {
		prepSt = DBManager.getInstance().getConnection().prepareStatement("DELETE FROM TABLE users WHERE user_id=?");
		prepSt.setLong(1, u.getUserId());
		prepSt.executeUpdate();
		System.out.println("A user successfully deleted!");
	  } catch (Exception e) {
		 System.out.println(e.getMessage());
	  }
  }
  
  //update user's profile by different properties given as parameters..
  public synchronized void updateUser(User u){
	  
  };
}
