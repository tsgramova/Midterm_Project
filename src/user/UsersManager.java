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
			if(username == null || username.isEmpty()) {
				return false;
			}
			if(firstName == null || firstName.isEmpty()) {
				return false;
			}
			if(lastName == null || lastName.isEmpty()) {
				return false;
			}
			if (!isValidEmailAddress(email)) {
				return false;
			}
			
			if(!validatePassword(password)){
				return false;
			}
			
	  return true;
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
  
  private boolean validatePassword (String password) {
		boolean upperCaseLetter = false; 
		boolean lowerCaseLetter = false;
		boolean digit = false;
		if(password.length() < 5 || password.length() > 15) { 
			return false;
		}
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
				upperCaseLetter = true;
				continue;
			}
			if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z' ) { 
				lowerCaseLetter = true;
				continue;
			}
			if (password.charAt(i) >= '0' && password.charAt(i) <= '9'){ 
				digit = true;
				continue;
			}
			if (upperCaseLetter && lowerCaseLetter && digit) { 
				break;
			}
		}
		if (upperCaseLetter && lowerCaseLetter && digit) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isValidEmailAddress(String email) {
		//also check DB for the same email
      String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
      java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
      java.util.regex.Matcher m = p.matcher(email);
      return m.matches();
	}
  
}
