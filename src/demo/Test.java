package demo;

import fridge_recipe.AllRecipes;
import user.AllUsers;
import user.User;
import user.UserException;

public class Test {

	public static void main(String[] args) {
		AllUsers.getInstance();
		AllRecipes.getInstance();
		
		try {
			User u1 = new User("Pesho_p", "Petur", "Petrov", "pesho_p739@abv.bg", "36*2hFkd0", false);
			User u2 = new User("Goshi_g", "Goergi", "Georgiev", "gogi_go@abv.bg", "38d832Jhd840.", false);
			User u3 = new User("Tosheto_t", "Todor", "Todorov", "tuti_fruti@abv.bg", "74J3jd93-..", false);
			User admin = new User("Vankata_v", "Ivan", "Ivanov", "ivan_iv@abv.bg", "3dff.,6A2h3kd0.", true);
			User u5 = new User("Pesho_p", "Petur", "Petrov", "pesho_p739@abv.bg", "36*2hFkd0", false);

		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
 }
}
