package hotel.event;

import java.util.ArrayList;
import java.util.Stack;

import hotel.Room;
import hotel.User;

public abstract class HotelEvent {
	
	long time;
	
	HotelEvent(long time) {
		this.time=time;
	}
	
	public long getTime() {
		return time;
	}
	
	public void activate() {
		
	}
	
	public void deactivate() {
		
	}
	
	public static Stack<HotelEvent> importData(ArrayList<Room> rooms, ArrayList<User> users) {
		Stack<HotelEvent> stack =new Stack<HotelEvent>();
		ArrayList<HotelEventString> array = HotelEventString.importData();
		for (HotelEventString event:array) {
			stack.add(importHotelEventString(event, rooms, users));
		}
		
		return stack;
	}
	
	public static HotelEvent importHotelEventString(HotelEventString event, ArrayList<Room> rooms, ArrayList<User> users ) {
		HotelEvent hotelEvent;
		long time = event.getTime();
		User user = userFromString(users, event.getGuest_id());
		
		String device = event.getDevice();
		if (device.equals("access point")) {
			String deviceID = event.getDevice_id();
			Room room = roomFromString(rooms, AccessPoint.toRoomNumber(deviceID));
			hotelEvent = new AccessPoint(time, user, room, event.getEvent());
		}
		else if (device.equals("door sensor")) {
			Room room = roomFromRoomNumber(rooms, event.getDevice_id());
			hotelEvent = new DoorSensor(time, room, user, event.getEvent());
		}
		else if (device.equals("motion sensor")) {
			Room room = roomFromName(rooms, event.getDevice_id());
			hotelEvent = new MotionSensor(time, room);
		}
		else { //Its a phone
			Room room = roomFromName(rooms, event.getDevice_id());
			if (room==null) {room = roomFromRoomNumber(rooms, event.getDevice_id());}
			hotelEvent = new Phone(time, room, event.getEvent()); 
		}
		return hotelEvent;
	}
	
	public static Room roomFromRoomNumber(ArrayList<Room> rooms, String number) {
		if (number.equals("156b")) {
			number="158";
		}
		int roomNumber = Integer.parseInt(number);
		for (Room room:rooms) {
			if (room.getRoomNumber()==roomNumber) {
				return room;
			}
		}
		return null;
	}
	
	public static Room roomFromName(ArrayList<Room> rooms, String name) {
		for (Room room:rooms) {
			if (room.getName().equals(name)) {
				return room;
			}
		}
		return null;
	}
	
	public static User userFromString(ArrayList<User> users, String name) {
		for (User user:users) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}
	
	public static Room roomFromString(ArrayList<Room> rooms, String name) {
		for (Room room:rooms) {
			if (Integer.toString(room.getRoomNumber()).equals(name)) {
				return room;
			}
		}
		return null;
	}
}
