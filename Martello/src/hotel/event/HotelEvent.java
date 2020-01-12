package hotel.event;

import java.util.ArrayList;
import java.util.Stack;

import hotel.Room;
import hotel.User;

/**
 * An event in the hotel, extended by more specific events
 * 
 * @author Adam Prins
 *
 */
public abstract class HotelEvent {
	
	//Epoch time
	long time;
	
	/**
	 * Constructor for Hotel Event
	 * 
	 * @param time epoch time as a long
	 */
	HotelEvent(long time) {
		this.time=time;
	}
	
	/**
	 * Gets the time of the event in epoch time 
	 * 
	 * @return the time in epoch as a long
	 */
	public long getTime() {
		return time;
	}
	
	/**
	 * Activates the event on the GUI
	 */
	public void activate() {
		
	}
	
	/**
	 * Deactivates the event on the GUI
	 */
	public void deactivate() {
		//TODO not implemented
	}
	
	/**
	 * imports the data from the return of import in HotelEventString
	 * 
	 * @param rooms the rooms in the building
	 * @param users the users in the building
	 * 
	 * @return the events in the hotel as a Stack
	 */
	public static Stack<HotelEvent> importData(ArrayList<Room> rooms, ArrayList<User> users) {
		Stack<HotelEvent> stack =new Stack<HotelEvent>();
		ArrayList<HotelEventString> array = HotelEventString.importData();
		for (HotelEventString event:array) {
			stack.add(importHotelEventString(event, rooms, users));
		}
		
		return stack;
	}
	
	/**
	 * Imports a single HotelEventString to a HotelEvent
	 * 
	 * @param event the HotelEventString
	 * @param rooms the rooms in the building
	 * @param users the users in the building
	 * 
	 * @return the HotelEventString as a HotelEvent
	 */
	public static HotelEvent importHotelEventString(HotelEventString event, ArrayList<Room> rooms, ArrayList<User> users ) {
		HotelEvent hotelEvent;
		long time = event.getTime();
		User user = userFromString(users, event.getGuest_id());
		
		String device = event.getDevice();
		if (device.equals("access point")) {
			String deviceID = event.getDevice_id();
			Room room = roomFromString(rooms, AccessPoint.toRoomNumber(deviceID));
			hotelEvent = new AccessPoint(time, user, room, event.getEvent());
		}
		else if (device.equals("door sensor")) {
			Room room = roomFromRoomNumber(rooms, event.getDevice_id());
			hotelEvent = new DoorSensor(time, room, user, event.getEvent());
		}
		else if (device.equals("motion sensor")) {
			Room room = roomFromName(rooms, event.getDevice_id());
			hotelEvent = new MotionSensor(time, room);
		}
		else { //Its a phone
			Room room = roomFromName(rooms, event.getDevice_id());
			if (room==null) {room = roomFromRoomNumber(rooms, event.getDevice_id());}
			hotelEvent = new Phone(time, room, event.getEvent()); 
		}
		return hotelEvent;
	}
	
	/**
	 * Gets a room from a given room number as a string
	 * 
	 * @param rooms the rooms in the building
	 * @param number the room number, as a string
	 * 
	 * @return the matching Room
	 */
	public static Room roomFromRoomNumber(ArrayList<Room> rooms, String number) {
		if (number.equals("156b")) {
			number="158";
		}
		int roomNumber = Integer.parseInt(number);
		for (Room room:rooms) {
			if (room.getRoomNumber()==roomNumber) {
				return room;
			}
		}
		return null;
	}
	
	/**
	 * Gets a room from a given room name
	 * 
	 * @param rooms the rooms in the building
	 * @param name the name of the room, as a string
	 * 
	 * @return the matching Room
	 */
	public static Room roomFromName(ArrayList<Room> rooms, String name) {
		for (Room room:rooms) {
			if (room.getName().equals(name)) {
				return room;
			}
		}
		return null;
	}
	
	/**
	 * Gets a User from a given name String
	 * 
	 * @param users the users in the building
	 * @param name the name of the user, as a String
	 * 
	 * @return the matching User
	 */
	public static User userFromString(ArrayList<User> users, String name) {
		for (User user:users) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * gets a room from a given name
	 * 
	 * @param rooms the rooms in the building
	 * @param name
	 * 
	 * @return the matching Room
	 */
	public static Room roomFromString(ArrayList<Room> rooms, String name) {
		for (Room room:rooms) {
			if (Integer.toString(room.getRoomNumber()).equals(name)) {
				return room;
			}
		}
		return null;
	}
}
