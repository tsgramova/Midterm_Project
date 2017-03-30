 package user;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import products.Product;
import recipe.Recipe;
import recipe.RecipeException;
import recipe.RecipeManager;


public class User {
	
	public enum Rang {BEGGINER,MIDDLE,MASTER};
	
	private long userId;
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
		this.isAdmin= isAdmin;

	}
	
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmailAddress() {
		return emailAddress;
	}



	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}



	public String getPassword() {
		return password;
	}


	public Rang getRang() {
		return rang;
	}



	public void setRang(Rang rang) {
		this.rang = rang;
	}



	public boolean isAdmin() {
		return isAdmin;
	}

	public HashSet<Recipe> getFavorites() {
		return favorites;
	}



	public HashSet<Recipe> getCooked() {
		return cooked;
	}



	public Set<Recipe> getAdded() {
		return Collections.unmodifiableSet(added);
	}


	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}



	public void addNewRecipe(Recipe r) {
		this.added.add(r);
		System.out.println("A recipe added to user's recipes!");
	}
	
	public void addRecipeToFavorites(Recipe r) {
		this.favorites.add(r);
		r.addToFavorites();
	}
	
	public void cookRecipe(Recipe r) {
		this.cooked.add(r);
		r.cook();
	}
	
	
	private boolean checkUsername(String username) {
		if (username!=null && !username.isEmpty() && username.length()>5) {
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

	private boolean isValidEmailAddress(String email) {
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
	
	
}




