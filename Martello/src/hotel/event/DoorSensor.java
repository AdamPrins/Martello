package hotel.event;

import java.awt.Color;

import hotel.Door;
import hotel.Room;
import hotel.User;

public class DoorSensor extends HotelEvent {

	Door door;
	User user;
	Room room;
	boolean isOpening;
	
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
}
