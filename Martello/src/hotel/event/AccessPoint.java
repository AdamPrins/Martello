package hotel.event;

import hotel.Room;
import hotel.User;

/**
 * An Access Point event
 * 
 * @author Adam Prins
 */
public class AccessPoint extends HotelEvent {

	User user; 
	Room room;
	boolean connected;
	
	/**
	 * Creates an AccessPoint Event
	 * 
	 * @param time the time in epoc
	 * @param user the user that intereacted with the AccessPoint
	 * @param room the Room where the AccessPoint is
	 * @param event the type of AccessPoint event as a string
	 */
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
	
	/**
	 * Triggers the event on the Room
	 */
	@Override
	public void activate() {
		if (connected) {
			room.addUser(user);
		}
		else {
			room.removeUser(user);
		}
	}
	
	/**
	 * Converts access point names to room numbers
	 * 
	 * @param s
	 * @return
	 */
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
	
	@Override
	public String toString() {
		String s = user+" has ";
		if (connected) {
			s+="connected to ";
		}
		else {
			s+="disconnected from ";
		}
		s+="the access point in "+ room;
		
		return s;
	}
	
}
