package hotel.event;

import java.util.ArrayList;

import hotel.Room;
import hotel.UnknownUser;
import hotel.User;

public class Phone extends HotelEvent {
	
	private boolean pickUp;
	Room room;
	
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
