package gui;
import java.util.ArrayList;
import java.util.Stack;

import hotel.Room;
import hotel.User;
import hotel.event.*;

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
	Stack<HotelEvent> events;
	Stack<HotelEvent> eventsPast;
	
	/**
	 * Does all the setup for the Rooms and Users
	 */
	public Controller() {
		rooms = Room.setupRooms();
		users = User.setupUsers();
		events = HotelEvent.importData(rooms, users);
		eventsPast = new Stack<HotelEvent>();
		
	}
	
	/**
	 * Returns the list of all the Rooms
	 * 
	 * @return Returns an ArrayList of the Rooms 
	 */ 
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public HotelEvent stepForwards() {
		if (!events.isEmpty()) {
			HotelEvent event = events.pop();
			event.activate();
			eventsPast.add(event);
			return event;
		}
		return eventsPast.peek();
	}
	
	public HotelEvent stepBackwards() {
		if(!eventsPast.isEmpty()) {
			HotelEvent event = eventsPast.pop();
			event.deactivate();
			events.add(event);
			return event;
		}
		return events.peek();
	}
	
	/**
	 * Returns the list of all the Users
	 * 
	 * @return Returns an ArrayList of the Users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public int numberOfEvents() {
		return events.size()+eventsPast.size();
	}
	
}
