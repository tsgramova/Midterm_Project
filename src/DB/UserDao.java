package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
  
  public Set<User> getAllUsers() {
    Set<User> users = new HashSet<User>();
    try
    {
      Statement st = DBManager.getInstance().getConnection().createStatement();
      ResultSet resultSet = st.executeQuery("SELECT username,first_name,last_name,email,password,role FROM users;");
      while (resultSet.next()) {
    	  users.add(new User(resultSet.getString("username"), 
          resultSet.getString("first_name"), 
          resultSet.getString("last_name"), 
          resultSet.getString("email"), 
          resultSet.getString("password"), 
          resultSet.getString("role").equals("admin")));
      }
      
    }
    catch (SQLException e) {
      System.out.println("Cannot make statement.");
      return users;
    } 
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
    System.out.println("Users loaded successfully");
    return users;
  }
  
  public void saveUser(User user) {
    try {
      PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement("INSERT INTO users (username,first_name,last_name,email,password,role) VALUES (?,?,?,?,?,?);");
      st.setString(1, user.getUserName());
      st.setString(2, user.getFirstName());
      st.setString(3, user.getLastName());
      st.setString(4, user.getEmailAddress());
      st.setString(5, user.getPassword());
      st.setString(6, user.isAdmin() ? "admin" : "user");
      st.executeQuery();
      System.out.println("User added successfully");
    } 
    catch (SQLException e) {
      System.out.println("The user has not been saved!");
      e.printStackTrace();
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
