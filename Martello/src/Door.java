import java.awt.Color;
import java.awt.Graphics;

/**
 * Contains all the parameters of a door, including position, 
 * alignment, and if it's open or closed
 * 
 * @author Adam Prins
 */
public class Door {
	
	//The two alignments a door can have
	public static enum direction{horizontal,vertical};
	
	//Weather the current state is open or closed
	private boolean isOpen;
	//The current alignment of the Door
	private direction position;
	//the x position
	private int x;
	//The y position
	private int y;
	//The current colour of the Door. Describes who last used it.
	Color color;

	/**
	 * Creates a door with given x, y, and positional values
	 * 
	 * @param x The center x position of the Door
	 * @param y The center y position of the Door
	 * @param pos The initial alignment of the Door
	 */
	public Door(int x, int y , direction pos) {
		position = pos;
		this.x=x;
		this.y=y;
		isOpen=false;
		color=Color.black;
	}
	
	/**
	 * toggles the Position of the Door. This changes its alignment, and its
	 * status as opened or closed.
	 */
	public void toggleDoor() {
		isOpen=!isOpen;
		if (position==direction.horizontal) {
			position=direction.vertical;
		}
		else {
			position=direction.horizontal;
		}
	}
	
	/**
	 * Returns the status of the door as open (true) or closed (false)
	 * 
	 * @return Returns true if the door is open, false otherwise
	 */
	public boolean isDoorOpen() {
		return isOpen;
	}
	
	/**
	 * Changes the y value of the door by the given amount. This is used to 
	 * fix alignment issues with the Executive suite intersecting the comfort rooms
	 * 
	 * @param y the amount by which the y value should change
	 */
	public void changeY(int y) {
		this.y+=y;
	}
	
	/**
	 * This paints the Door based of its properties.
	 * It will show if it is open or closed, and the color of
	 * the last user to interact with it.
	 * 
	 * @param g the Graphics that will be used to draw the Door
	 */
	public void paint(Graphics g) {
		
		int width=0;	int height=0;
		
		if (position==direction.horizontal) {
			width=25; 	height=5;
		}
		else {
			width=5;	height=25;
		}
		
		g.setColor(color);
		g.fillRect(x-width/2, y-height/2, width, height);
		g.setColor(Color.black);
		g.drawRect(x-width/2, y-height/2, width, height);
		
	}
}
