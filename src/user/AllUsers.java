package user;

import java.util.HashMap;

public class AllUsers {
	
	private static HashMap<String, User> users;
	private static AllUsers instance=null;
	
	private AllUsers (){
		this.users=new HashMap<>();
	}
	
	public static AllUsers getInstance() {
		if(instance==null) {
			instance = new AllUsers();
		}
		return instance;
	}
	
	public static boolean checkForUsername(String username) {
		if (AllUsers.users!= null && AllUsers.users.containsKey(username)) {
			return true;
		}
		return false;
	}
	
	public static void addUser(User u) {
		
			AllUsers.users.put(u.getUserName(), u);
		
	}
	
	public static void removeUser(User u) {
		if(AllUsers.users.containsValue(u)) {
			AllUsers.users.remove(u.getUserName(), u);
		}
	}
	

}
