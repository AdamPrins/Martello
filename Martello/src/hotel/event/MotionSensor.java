package hotel.event;

import hotel.Room;
import hotel.UnknownUser;

/**
 * A Motion Sensor Event in the Hotel
 * 
 * @author Adam Prins
 */
public class MotionSensor extends HotelEvent {

	Room room;
	
	/**
	 * Creates a Motion Sensor Event
	 * 
	 * @param time the time in epoc
	 * @param room the Room in which motion was detected
	 */
	MotionSensor(long time, Room room) {
		super(time);
		this.room=room;
	}
	
	/**
	 * Triggers the event on a Room
	 */
	@Override
	public void activate() {
		UnknownUser user = new UnknownUser("motion detected", room, time);
		room.addUser(user);
		UnknownUser.addMotionUser(user);
	}
	
	/**
	 * Returns the event as a string
	 * 
	 * @return a String representation of the event
	 */
	@Override
	public String toString() {
		return "Mostion detected in " + room;
	}
	
}
