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

	ArrayList<Room> rooms; //All the Rooms in the Building
	ArrayList<User> users; //All the Users that were at the building in the last 24 hours
	Stack<HotelEvent> events; //All the upcoming Events
	Stack<HotelEvent> eventsPast; //All the past Events
	
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
	
	/**
	 * triggers the next event and moves it from 
	 * the event stack to the eventPast stack
	 * 
	 * @return the triggered event
	 */
	public HotelEvent stepForwards() {
		if (!events.isEmpty()) {
			HotelEvent event = events.pop();
			event.activate();
			eventsPast.add(event);
			return event;
		}
		return eventsPast.peek();
	}
	
	/**
	 * detriggers the previous event and moves it from 
	 * the eventPast stack to the event stack
	 * 
	 * @return the detriggered event
	 * 
	 * TODO This is not supported by most Events!
	 */
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
	
	/**
	 * The number of Events taken from the JSON
	 * 
	 * @return the number of events
	 */
	public int numberOfEvents() {
		return events.size()+eventsPast.size();
	}
	
}
