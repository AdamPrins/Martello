package hotel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Holds the data for individual Users
 * Also contains the User set up information
 * 
 * @author Adam Prins
 */
public class User {
	
	//The name of the User
	String name;
	//The colour associated with the User
	Color color;
	//The Previous locations of the User
	Stack<Room> history;
	
	/**
	 * The constructor for User
	 * 
	 * @param name the Name of the User
	 * @param color the Color associated with the User
	 */
	public User(String name, Color color) {
		this.name=name;
		this.color=color;
		history = new Stack<Room>();
	}
	
	/**
	 * Gets the Room the User was Last in
	 * 
	 * @return the last Room the User was in
	 */
	public Room getRoom() {
		return history.peek();
	}
	
	/**
	 * Sets a Users location to the given Room
	 * 
	 * @param room The Room the the User has moved into
	 */
	public void addRoom(Room room) {
		if (!history.isEmpty() && !history.peek().equals(room)) {
			history.peek().removeUser(this);
		}
		history.add(room);
	}
	
	/**
	 * Removes a user from the last Room they were in
	 */
	public void removeRoom() {
		history.pop().removeUser(this);;
	}
	
	/**
	 * Returns the users name
	 * 
	 * @return the name of the user
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the Color associated with the User
	 * 
	 * @return the Color associated with the User
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Paints the User object
	 * 
	 * @param g the graphics to draw
	 * @param x the x position of the User
	 * @param y the y position of the User
	 */
	public void paint(Graphics g, int x, int y) {
		int size = 20;
		x-=size/2;
		y-=size/2;
		g.setColor(color);
		g.fillOval(x, y, size, size);
		g.setColor(Color.black);
		g.drawOval(x, y, size, size);
		String s = name.substring(0, 1);
		g.drawString(s, x+size/2-3, y+size/2+3);
	}
	
	/**
	 * This determines if a given object is equivalent to this User
	 * 
	 * @return Returns true if the given object is the same as this User, false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof User) {
			User user = (User) o;
			if (name.equals(user.getName())) {
				return true;
			}
		} 
		return false;

	}
	
	/**
	 * Returns the String representation of the User
	 * 
	 * @return the String representation of the User (their name)
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Sets up the List of all 12 Users suspected on the night of the murder
	 *  
	 * @return the ArrayList containing all the Users
	 */
	public static ArrayList<User> setupUsers() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("Veronica", 	new Color(255, 0, 0)));
		users.add(new User("Jason",		new Color(51, 102, 255)));
		users.add(new User("Thomas", 	new Color(204, 102, 0)));
		users.add(new User("Eugene",	new Color(204, 255, 51)));
		users.add(new User("Salina",	new Color(153, 0, 255)));
		users.add(new User("Rob",		new Color(0, 153, 0)));
		users.add(new User("Kristina",	new Color(255, 192, 203)));
		users.add(new User("Alok",		new Color(255, 255, 0)));
		users.add(new User("Marc-Andre",new Color(51, 204, 204)));
		users.add(new User("Dave",		new Color(255, 102, 0)));
		users.add(new User("Harrison",	new Color(245, 222, 179)));
		users.add(new User("James",		new Color(150, 150, 150)));
		
		return users;
	}
	
}
