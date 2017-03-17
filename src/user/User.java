package user;

import java.util.HashSet;

import products.Product;
import recipe.AllRecipes;
import recipe.Fridge;
import recipe.Recipe;


public class User {
	
	public enum Rang {BEGGINER,MIDDLE,MASTER};
	
	private String userName;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
	private Rang rang;
	private boolean isAdmin;
	
	//image
	
	private HashSet<Recipe> favorites;
	private HashSet<Recipe> cooked;
	private HashSet<Recipe> added;
	
	
	public User(String userName, String firstName, String lastName, String emailAddress, 
			String password, boolean isAdmin) throws UserException {
		if(checkUsername(userName)) {
			this.userName = userName;
			AllUsers.addUser(this);

		} else {
			throw new UserException("Invalid username!");
		}
		
		if(checkName(firstName)) {
			this.firstName = firstName;
		}
		else {
			throw new UserException("Invalid first name!");
		}
		if(checkName(lastName)) {
			this.lastName = lastName;
		}
		else {
			throw new UserException("Invalid last name!");
		}
		if (isValidEmailAddress(emailAddress)) {
			this.emailAddress = emailAddress;
		}
		else {
			throw new UserException("Invalid email!");
		}
		if(validatePassword(password)){
			this.password = password;
		}
		else {
			throw new UserException("Invalid password!");
		}
		this.favorites = new HashSet<>();
		this.cooked = new HashSet<>();
		this.added = new HashSet<>();
		this.myFridge = new Fridge();
		this.isAdmin= isAdmin;

	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setRang (Rang r) {
		this.rang = r;
	}
	
	public void changeRang(Rang r) {
		this.rang = r;
	}
	
	public void changePassword (String password) {
		if (validatePassword(password)) {
			this.password = password;
		}
		else {
			System.out.println("Invalid password.");
		}
	}
	
	public void addNewRecipe(Recipe r) {
		AllRecipes.addNewRecipe(r);
		this.added.add(r);
	}
	
	public void addRecipeToFavorites(Recipe r) {
		this.favorites.add(r);
		r.addToFavorites();
	}
	
	public void cookRecipe(Recipe r) {
		this.cooked.add(r);
		r.cook();
	}
	
	public void approveRecipe(Recipe r) {
		if(this.isAdmin) {
			//do something..
		}
	}
	
	public void removeRecipe(Recipe r) {
		if (this.isAdmin) {
			//do something..
		}
	}
	
	public void removeUser(User u) {
		if (this.isAdmin) {
			//do something..
		}
	}
	private boolean checkUsername(String username) {
		//request sent to database check if username is taken
		if (username!=null && !username.isEmpty() && !AllUsers.checkForUsername(username) && username.length()>5) {
			return true;
		}
		return false;
	}
	
	private boolean checkName(String name) {
		if (name != null && !name.isEmpty()) {
			return true;
		}
		return false;
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


	//hashcode and equals for the hashset of all the users
	//depends on email and username
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	public String getFirstName() {
	    return firstName;
	  }
	  

	  public String getLastName()
	  {
	    return lastName;
	  }
	  

	  public String getEmailAddress()
	  {
	    return emailAddress;
	  }
	  

	  public boolean isAdmin()
	  {
	    return isAdmin;
	  }
	  

	  public User.Rang getRang()
	  {
	    return rang;
	  }
	  
	  public String getPassword() {
	    return password;
	  }
	}




