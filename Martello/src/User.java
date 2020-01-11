import java.awt.Color;
import java.util.ArrayList;

public class User {
	String name;
	Color color;
	
	public User(String name, Color color) {
		this.name=name;
		this.color=color;
	}
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean equals(Object o) {
		if (o instanceof User) {
			User user = (User) o;
			if (name.equals(user.getName())) {
				return true;
			}
		} 
		return false;

	}
	
	public static ArrayList<User> setupUsers() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("Veronica", 	new Color(255, 0, 0)));
		users.add(new User("Jason",		new Color(51, 102, 255)));
		users.add(new User("Thomas", 	new Color(204, 102, 0)));
		users.add(new User("Eugene",	new Color(204, 255, 51)));
		users.add(new User("Salina",	new Color(153, 0, 255)));
		users.add(new User("Rob",		new Color(0, 153, 0)));
		users.add(new User("Kristina",	new Color(102, 0, 204)));
		users.add(new User("Alok",		new Color(255, 255, 0)));
		users.add(new User("Marc-Andre",new Color(51, 204, 204)));
		users.add(new User("Dave",		new Color(255, 102, 0)));
		users.add(new User("Harrison",	new Color(0, 204, 0)));
		users.add(new User("James",		new Color(0, 0, 255)));
		
		return users;
	}
}
