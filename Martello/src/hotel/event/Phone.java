package hotel.event;

import hotel.Room;
import hotel.UnknownUser;

/**
 *  A Phone Event in the Hotel
 *  
 * @author Adam Prins
 */
public class Phone extends HotelEvent {
	
	private boolean pickUp;
	Room room;
	
	/**
	 * Creates a phone event
	 * 
	 * @param time time in epoc
	 * @param room room where event occurred 
	 * @param event the type of phone event as a string
	 */
	Phone(long time, Room room, String event) {
		super(time);
		this.room=room;
		if (event.equals("off hook")) {
			pickUp=true;
		}
		else {
			pickUp=false;
		}
	}
	
	@Override
	public void activate() {
		if (pickUp) {
			room.addUser(new UnknownUser("phone", room, time));
		}
		else {
			room.removeUser(new UnknownUser("phone", room, time));
		}
	}
	
	@Override
	public String toString() {
		if (pickUp) {
			return "Call started in " + room;
		}
		else {
			return "Call ended in " + room;
		}
	}
}
