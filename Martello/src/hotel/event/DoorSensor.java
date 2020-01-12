package hotel.event;

import java.awt.Color;

import hotel.Door;
import hotel.Room;
import hotel.User;

/**
 * A Door Sensor Event
 * 
 * @author Adam Prins
 */
public class DoorSensor extends HotelEvent {

	Door door;
	User user;
	Room room;
	
	//True if the door is being opened, false if it is being closed
	boolean isOpening;
	
	/**
	 * Creats a DoorSensor Event
	 * 
	 * @param time time in epoc
	 * @param room the Room where the Door is located
	 * @param user the User that interacted with the door, may be null
	 * @param event the type of DoorSensor Even as a string
	 */
	DoorSensor(long time, Room room, User user, String event) {
		super(time);
		this.room=room;
		this.door=room.getDoor();
		this.user=user;
		
		if (event.equals("door closed")) {
			isOpening=false;
		}
		else {
			isOpening=true;
		}
		
	}
	
	/**
	 * Triggers the event on the Door
	 */
	@Override
	public void activate() {
		if (user==null) {
			door.setColor(Color.black);
		}
		else {
			door.setColor(user.getColor());
		}
		if (isOpening) {
			if (!door.isDoorOpen()) {
				door.toggleDoor();
				if (user!=null) {
					room.addUser(user);
				}
			}
		}
		else {
			if (door.isDoorOpen()) {
				door.toggleDoor();
			}
		}
	}
	
	/**
	 * Converts the DoorSensor Event to a String
	 * 
	 * @return the String representation of the DoorSensor Event
	 */
	@Override
	public String toString() {
		String s = "Door in room " + room + " has been ";
		if (isOpening) {
			s+="opened";
		}
		else {
			s+="closed";
		}
		if (user!=null) {
			s+=" by "+user;
		}
		else if (isOpening) {
			s+=" from inside";
		}
		return s;
	}
}
