package user;

import java.util.HashSet;

public class UsersList {

	private static UsersList usersList = null;
	private HashSet<User> users;
	
	private UsersList() {
		users = new HashSet<>();
	}
	
	public UsersList getInstance() { // THREADS!!! SYNCHRO!!! but how?
		if(this.usersList == null) {
			this.usersList = new UsersList();
		}
		
		return this.usersList;
	}
}
