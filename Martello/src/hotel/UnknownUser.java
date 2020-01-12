package hotel;

import java.awt.Color;
import java.util.ArrayList;

public class UnknownUser extends User {

	private long time;
	private Room room;
	
	private static ArrayList<UnknownUser> motionUsers = new ArrayList<UnknownUser>();
	
	public UnknownUser(String event, Room room, long time) {
		super(event, Color.white);
		this.room=room;
		this.time=time;
	}
	
	public long getTime() {
		return time;
	}
	
	public static void addMotionUser(UnknownUser user) {
		motionUsers.add(user);
	}
	
	public static void clearMotionUsers(long time) {
		ArrayList<UnknownUser> oldUsers = new ArrayList<UnknownUser>();
		for (UnknownUser user:motionUsers) {
			if ((time-user.time)>10) {
				user.room.removeUser(user);
				oldUsers.add(user);
			}
		}
		motionUsers.removeAll(oldUsers);
	}
	
}
