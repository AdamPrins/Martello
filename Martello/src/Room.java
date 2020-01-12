import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Holds all the data for individuals Rooms
 * Also contains the Room set up information
 * 
 * @author Adam Prins
 */
public class Room {
	
	public static enum direction{North, East, South, West};

	private int roomNumber;	//The room number
	private String name;	//The room name
	private int x;			//The x of the upper left corner
	private int y;			//The y of the upper left corner
	private int width;		//The width of the Room
	private int height;		//The height of the room
	
	//Determines if the rectangle around the room should be draws.
	private boolean drawRect;
	
	//ArrayLists holding the users and doors contained by the Room
	private ArrayList<User> users;
	private ArrayList<Door> doors;
	
	/**
	 * Constructor for the Room class
	 * 
	 * @param roomNumber The room number
	 * @param name The name of the Room
	 * @param x The x of the upper left corner 
	 * @param y The y of the upper left corner
	 * @param width The width of the Room
	 * @param height The height of the room
	 */
	public Room(int roomNumber,String name, int x, int y, int width, int height) {
		this.roomNumber=roomNumber;
		this.name=name;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		
		drawRect=true;
		
		users = new ArrayList<User>();
		doors = new ArrayList<Door>();
	}
	
	/**
	 * Adds a user to the Room
	 * 
	 * @param user the user to be added to the room
	 */
	public void addUser(User user) {
		if (!users.contains(user)) {
			users.add(user);
			user.addRoom(this);
		}
	}
	
	/**
	 * Removes a user from the Room
	 * 
	 * @param user to be removed from the Room
	 */
	public void removeUser(User user) {
		users.remove(user);
	}
	
	/**
	 * Returns the ArrayList of users  currently in the Room
	 * 
	 * @return the Users in the Room
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	
	/**
	 * Paints the room and all its components
	 * 
	 * @param g the Graphics that will be used to paint
	 */
	public void paint(Graphics g) {
		g.setColor(Color.black);
		
		//Draws the rectangle if the flag is set true (default behavior)
		if (drawRect) {
			g.drawRect(x, y, width, height);
		}
		
		//Draws the room number if its has a positive value, prints the name otherwise
		if (roomNumber>0) {
			g.drawString(Integer.toString(roomNumber), (x+width/2-10), (y+15));
		}
		else {
			g.drawString(name, (x+width/2-20), (y+15));
		}
		
		//Draws all the users located in the Room in a spiral
		int count = 0;
		for (User user:users) {
			int size = 20;
			int userX = (int) ((x + width/2)  + (count/6+1)*20*Math.sin(Math.toRadians(60*count)));
			int userY = (int) ((y + height/2) + (count/6+1)*20*Math.cos(Math.toRadians(60*count)));
			g.setColor(user.getColor());
			g.fillOval(userX, userY, size, size);
			g.setColor(Color.black);
			g.drawOval(userX, userY, size, size);
			String s = user.getName().substring(0, 1);
			g.drawString(s, userX+size/2-3, userY+size/2+3);
			
			count++;
		}
		
		//Calls the Room's Doors to paint themselves
		for (Door door:doors) {
			door.paint(g);
		}
	}
	
	/**
	 * Adds a Door to a Room. This is done by passing it a direction from the Room's 
	 * direction enum. The location will be handled by the coordinates of the Room.
	 * 
	 * @param dir the side of the Room in which the door is located
	 */
	public void addDoor(direction dir) {
		if (dir == direction.North) {
			Door door = new Door(x+width/2, y, Door.direction.horizontal);
			doors.add(door);
		}
		else if (dir == direction.South) {
			Door door = new Door(x+width/2, y+height, Door.direction.horizontal);
			doors.add(door);
		}
		else if (dir == direction.West) {
			Door door = new Door(x, y+height/2, Door.direction.vertical);
			doors.add(door);
		}
		else if (dir == direction.East) {
			Door door = new Door(x+width, y+height/2, Door.direction.vertical);
			doors.add(door);
		}
	}
	
	/**
	 * Used to fix the door locations for the two Executive Suits, as the natural location would
	 * have them intersecting with the walls of the respective Comfort Rooms.
	 * 
	 * @param amount the amount that the Y value of the Door must be shifted
	 */
	public void moveDoor(int amount) {
		doors.get(0).changeY(amount);
	}
	
	/**
	 * Disables or enables the drawing of the bounding box of the room. 
	 * This is used specifically for rooms that are adjacent to other rooms,
	 * but not separated by walls.
	 */
	public void toggleDrawRect() {
		drawRect=!drawRect;
	}
	
	
	/**
	 * Sets up the initial configuration of the Rooms. 
	 * This includes all the x, y, widths and heights of all Rooms.
	 * As well as any toggle of their rectangle drawing, 
	 * and the placement of all their Doors
	 * 
	 * @return an ArrayList of Rooms for the initial state of the building.
	 */
	public static ArrayList<Room> setupRooms() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(new Room(110, "Conference Room", 	 50,  50, 100, 150));
		rooms.get(rooms.size()-1).addDoor(direction.East);
		rooms.add(new Room(130, "Kitchen", 			 50, 200, 100, 100));
		rooms.get(rooms.size()-1).addDoor(direction.East);
		rooms.add(new Room(101, "Reception Closet", 300,  50,  75,  25));
		rooms.get(rooms.size()-1).addDoor(direction.West);
		rooms.add(new Room(151, "Gym", 				300,  75,  75,  75));
		rooms.get(rooms.size()-1).addDoor(direction.South);
		rooms.add(new Room(155, "Pool", 			375,  50,  75, 100));
		rooms.get(rooms.size()-1).addDoor(direction.South);
		rooms.add(new Room(152, "Men's Washroom",	300, 200,  50, 100));
		rooms.get(rooms.size()-1).addDoor(direction.North);
		rooms.add(new Room(154, "Woman's Washroom",	350, 200,  50, 100));
		rooms.get(rooms.size()-1).addDoor(direction.North);
		rooms.add(new Room(156, "Laundry Room",		400, 200,  50,  50));
		rooms.get(rooms.size()-1).addDoor(direction.North);
		rooms.add(new Room(158, "Stroage Room",		400, 250,  50,  50));
		rooms.get(rooms.size()-1).addDoor(direction.North);
		rooms.add(new Room(150, "Stairwell",		450, 150,  50,  50));
		rooms.get(rooms.size()-1).addDoor(direction.West);
		
		rooms.add(new Room(100, "Front Lobby", 		150,  50, 150, 150));
		rooms.get(rooms.size()-1).addDoor(direction.North);
		rooms.get(rooms.size()-1).toggleDrawRect();
		rooms.add(new Room(105, "Dinning Hall", 	150, 200, 150, 100));
		rooms.get(rooms.size()-1).toggleDrawRect();
		rooms.add(new Room(0, "AP1-2", 			300, 150, 150, 50));
		rooms.get(rooms.size()-1).toggleDrawRect();
		
		rooms.add(new Room(210, "Executive Suit",	 50, 325,  75,  125));
		rooms.get(rooms.size()-1).addDoor(direction.East);
		rooms.get(rooms.size()-1).moveDoor(40);
		rooms.add(new Room(220, "Executive Suit",	 50, 450,  75,  125));
		rooms.get(rooms.size()-1).addDoor(direction.East);
		rooms.get(rooms.size()-1).moveDoor(-40);
		rooms.add(new Room(231, "Comfort Room",	 	125, 325,  65,   75));
		rooms.get(rooms.size()-1).addDoor(direction.South);
		rooms.add(new Room(233, "Comfort Room",	 	190, 325,  65,   75));
		rooms.get(rooms.size()-1).addDoor(direction.South);
		rooms.add(new Room(235, "Comfort Room",	 	255, 325,  65,   75));
		rooms.get(rooms.size()-1).addDoor(direction.South);
		rooms.add(new Room(241, "Junior Suite",	 	320, 325,  65,  100));
		rooms.get(rooms.size()-1).addDoor(direction.South);
		rooms.add(new Room(247, "Junior Suite",	 	385, 325,  65,  100));
		rooms.get(rooms.size()-1).addDoor(direction.South);
		rooms.add(new Room(232, "Comfort Room",	 	125, 500,  65,   75));
		rooms.get(rooms.size()-1).addDoor(direction.North);
		rooms.add(new Room(236, "Comfort Room",	 	255, 500,  65,   75));
		rooms.get(rooms.size()-1).addDoor(direction.North);
		rooms.add(new Room(244, "Junior Suite",	 	320, 475,  65,  100));
		rooms.get(rooms.size()-1).addDoor(direction.North);
		rooms.add(new Room(248, "Junior Suite",	 	385, 475,  65,  100));
		rooms.get(rooms.size()-1).addDoor(direction.North);
		rooms.add(new Room(250, "Stairwell",		450, 425,  50,   50));
		rooms.get(rooms.size()-1).addDoor(direction.West);
		
		rooms.add(new Room(234, "Ice Machines",	 	190, 500,   65,  75));
		rooms.get(rooms.size()-1).toggleDrawRect();
		rooms.add(new Room(200, "Hall",	 			320, 425,  130,  50));
		rooms.get(rooms.size()-1).toggleDrawRect();
		rooms.add(new Room(0, "AP2-1",	 			125, 400,   65, 100));
		rooms.get(rooms.size()-1).toggleDrawRect();
		rooms.add(new Room(0, "AP2-3",	 			255, 400,   65, 100));
		rooms.get(rooms.size()-1).toggleDrawRect();
		
		rooms.add(new Room(0, "Elevator",	 		200, 150,   65, 50));
		rooms.add(new Room(0, "Elevator",	 		200, 425,   65, 50));
		
		int count = 0;
		for (User user:User.setupUsers()) {
			rooms.get(count/5).addUser(user);
			count++;
		}
		
		return rooms;
	}
	
	
}













