package hotel.event;

import hotel.Room;
import hotel.UnknownUser;
import hotel.User;

public class MotionSensor extends HotelEvent {

	Room room;
	
	MotionSensor(long time, Room room) {
		super(time);
		this.room=room;
	}
	
	public void activate() {
		UnknownUser user = new UnknownUser("motion detected", room, time);
		room.addUser(user);
		UnknownUser.addMotionUser(user);
	}
	
	@Override
	public String toString() {
		return "Mostion detected in " + room;
	}
	
}
