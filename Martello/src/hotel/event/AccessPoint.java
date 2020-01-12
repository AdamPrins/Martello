package hotel.event;

import hotel.Room;
import hotel.User;

public class AccessPoint extends HotelEvent {

	User user; 
	Room room;
	boolean connected;
	
	AccessPoint(long time, User user, Room room, String event) {
		super(time);
		this.user=user;
		this.room=room;
		if (event.equals("user disconnected")) { connected = false; }
		else {connected=true;}
	}
	
	public User getUser() {
		return user;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public void activate() {
		if (connected) {
			room.addUser(user);
		}
		else {
			room.removeUser(user);
		}
	}
	
	public static String toRoomNumber(String s) {
		if (s.equals("ap1-1")) {
			s="110";
		}
		else if (s.equals("ap1-2")) {
			s="-12";
		}
		else if (s.equals("ap1-3")) {
			s="105";
		}
		else if (s.equals("ap1-4")) {
			s="100";
		}
		else if (s.equals("ap2-1")) {
			s="-21";
		}
		else if (s.equals("ap2-2")) {
			s="200";
		}
		else if (s.equals("ap2-3")) {
			s="-23";
		}
		return s;
	}
	
}
