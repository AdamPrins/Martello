package hotel;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Anonymous user for Phone and motion sensors
 * 
 * @author Adam Prins
 *
 */
public class UnknownUser extends User {

	private long time;
	private Room room;
	
	//A list of all recent Motion Events
	private static ArrayList<UnknownUser> motionUsers = new ArrayList<UnknownUser>();
	
	/**
	 * 
	 * @param event type of event
	 * @param room where event occurs
	 * @param time time in epoch
	 */
	public UnknownUser(String event, Room room, long time) {
		super(event, Color.white);
		this.room=room;
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
	 * Adds user to list. Old event will be removed from the canvas
	 * 
	 * @param user user that is to be monitored
	 */
	public static void addMotionUser(UnknownUser user) {
		motionUsers.add(user);
	}
	
	/**
	 * Clears old users from canvas and list
	 * 
	 * @param time the current time stamp
	 */
	public static void clearMotionUsers(long time) {
		ArrayList<UnknownUser> oldUsers = new ArrayList<UnknownUser>();
		for (UnknownUser user:motionUsers) {
			if ((time-user.time)>10) {
				user.room.removeUser(user);
				oldUsers.add(user);
			}
		}
		//Prevents concurrent edit errors
		motionUsers.removeAll(oldUsers);
	}
	
}
