import java.util.ArrayList;

/**
 * Handles all the functions that change the state of the Rooms and Users
 * Also calls all set up functions
 * 
 * @author Adam Prins
 *
 */
public class Controller {

	ArrayList<Room> rooms;
	ArrayList<User> users;
	
	/**
	 * Does all the setup for the Rooms and Users
	 */
	public Controller() {
		rooms = Room.setupRooms();
		users = User.setupUsers();
	}
	
	/**
	 * Returns the list of all the Rooms
	 * 
	 * @return Returns an ArrayList of the Rooms 
	 */ 
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	/**
	 * Returns the list of all the Users
	 * 
	 * @return Returns an ArrayList of the Users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
}
