package user;

import DB.UserDao;
import java.util.concurrent.ConcurrentHashMap;

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
  
  public boolean validLogin(String username, String password) {
    if (!registeredUsers.containsKey(username)) { //if the username doesn't exist return false
      return false;
    }
    //return if user's password matches the given password 
    return ((User)registeredUsers.get(username)).getPassword().equals(password);
  }
  
  public void register(String username, String firstName, String lastName, String password, String email) throws UserException{
    User user = new User(username, firstName, lastName, email, password, false);
    registeredUsers.put(username, user); //put user in collection
    UserDao.getInstance().saveUser(user); //save user in DB
  }
}
