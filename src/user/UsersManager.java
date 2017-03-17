package user;

import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import db.UserDao;

public class UsersManager {
	
	//concurrenthashmap because a lot of threads can use it
  private ConcurrentHashMap<String, User> registeredUsers; //username--> user
  private static UsersManager instance;
  
  private UsersManager() {
   
	registeredUsers = new ConcurrentHashMap<>();
   
    for (User u : UserDao.getInstance().getAllUsers()) {
      registeredUsers.put(u.getUserName(), u);
    }
  }
  
  public static synchronized UsersManager getInstance() {
    if (instance == null) {
      instance = new UsersManager();
    }
    return instance;
  }
  
  public boolean validateLogin(String username, String password) {
    if (!registeredUsers.containsKey(username)) { //if the username doesn't exist return false
      return false;
    }
    //return if user's password matches the given password 
    return ((User)registeredUsers.get(username)).getPassword().equals(password);
  }
  
 
  
  public boolean validateRegistration(String username, String password, String firstName, String lastName, String email) {
	 //TODO make validations for each parameter
	  return false;
  } 
  
  public void register(String username, String firstName, String lastName, String password, String email) throws UserException, SQLException{
    User user = new User(username, firstName, lastName, email, password, false);
    registeredUsers.put(username, user); //put user in collection
    UserDao.getInstance().saveUser(user); //save user in DB
  }
  
  public void delete(User u) {
	  registeredUsers.remove(u);
	  UserDao.getInstance().saveUser(u);
  }
  
  //update user's information by given parameters.. 
  //this is for the time when we learn sessions.. 
  public void updateUser(User u) {
	  //some validations of values for the fields to be changed
	  UserDao.getInstance().updateUser(u);
  }
  
}
