import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Room {

	private int roomNumber;
	private String name;
	private int x;
	private int y;
	private int width;
	private int height;
	
	private boolean drawRect;
	
	private ArrayList<User> users;
	
	public Room(int roomNumber,String name, int x, int y, int width, int height) {
		this.roomNumber=roomNumber;
		this.name=name;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		
		drawRect=true;
		
		users = new ArrayList<User>();
	}
	
	public void addUser(User user) {
		if (!users.contains(user)) {
			users.add(user);
		}
	}
	
	public void removeUser(User user) {
		users.remove(user);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		
		if (drawRect) {
			g.drawRect(x, y, width, height);
		}
		
		if (roomNumber>0) {
			g.drawString(Integer.toString(roomNumber), (x+width/2-10), (y+15));
		}
		else {
			g.drawString(name, (x+width/2-20), (y+15));
		}
		
		int count = 0;
		for (User user:users) {
			g.setColor(user.getColor());
			int userX = (int) ((x + width/2)+15*Math.sin(Math.toRadians(30*count)));
			int userY = (int) ((x + width/2)+15*Math.cos(Math.toRadians(30*count)));
			g.fillOval(userX, userY, 10, 10);
			count++;
		}
	}
	
	public void toggleDrawRect() {
		drawRect=!drawRect;
	}
	
	
	public static ArrayList<Room> setupRooms() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(new Room(110, "Conference Room", 	 50,  50, 100, 150));
		rooms.add(new Room(130, "Kitchen", 			 50, 200, 100, 100));
		rooms.add(new Room(101, "Reception Closet", 300,  50,  75,  25));
		rooms.add(new Room(151, "Gym", 				300,  75,  75,  75));
		rooms.add(new Room(155, "Pool", 			375,  50,  75, 100));
		rooms.add(new Room(152, "Men's Washroom",	300, 200,  50, 100));
		rooms.add(new Room(154, "Woman's Washroom",	350, 200,  50, 100));
		rooms.add(new Room(156, "Laundry Room",		400, 200,  50,  50));
		rooms.add(new Room(158, "Stroage Room",		400, 250,  50,  50));
		rooms.add(new Room(150, "Stairwell",		450, 150,  50,  50));
		
		rooms.add(new Room(100, "Front Lobby", 		150,  50, 150, 150));
		rooms.get(rooms.size()-1).toggleDrawRect();
		rooms.add(new Room(105, "Dinning Hall", 	150, 200, 150, 100));
		rooms.get(rooms.size()-1).toggleDrawRect();
		rooms.add(new Room(0, "AP1-2", 			300, 150, 150, 50));
		rooms.get(rooms.size()-1).toggleDrawRect();
		
		rooms.add(new Room(210, "Executive Suit",	 50, 325,  75,  125));
		rooms.add(new Room(220, "Executive Suit",	 50, 450,  75,  125));
		rooms.add(new Room(231, "Comfort Room",	 	125, 325,  65,   75));
		rooms.add(new Room(233, "Comfort Room",	 	190, 325,  65,   75));
		rooms.add(new Room(235, "Comfort Room",	 	255, 325,  65,   75));
		rooms.add(new Room(241, "Junior Suite",	 	320, 325,  65,  100));
		rooms.add(new Room(247, "Junior Suite",	 	385, 325,  65,  100));
		rooms.add(new Room(232, "Comfort Room",	 	125, 500,  65,   75));
		rooms.add(new Room(236, "Comfort Room",	 	255, 500,  65,   75));
		rooms.add(new Room(244, "Junior Suite",	 	320, 475,  65,  100));
		rooms.add(new Room(248, "Junior Suite",	 	385, 475,  65,  100));
		rooms.add(new Room(250, "Stairwell",		450, 425,  50,   50));
		
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
		
		for (User user:User.setupUsers()) {
			rooms.get(0).addUser(user);
		}
		
		return rooms;
	}
	
	
}













