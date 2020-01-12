
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 *  Handles the functions for drawing the layout of the building
 *   
 * @authors Adam Prins
 * 
 */
public class Canvas extends JPanel {
	
	boolean drawGrid=false;
		
	private static final long serialVersionUID = 3290417118952335835L;
	
	/* The pixels needed to display the game window */
	public static int DRAWING_SIZE_X = 550;
	public static int DRAWING_SIZE_Y = 625;
	
	ArrayList<Room> rooms;
	
	
	/**
	 * The constructor of this drawing component
	 */
	public Canvas() {
		/* creates a boarder around the canvas */
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        rooms=new ArrayList<Room>();
    }

	/**
	 * Returns the preferred size of this component
	 * 
	 * @return returns the preferred dimensions of the component 
	 */
    public Dimension getPreferredSize() {
        return new Dimension(DRAWING_SIZE_X,DRAWING_SIZE_Y);
    }
    
    
    /**
     * Paints the component
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);      
        
        /* Clears the canvas */
        g.setColor(Color.white);
        g.fillRect(0, 0, DRAWING_SIZE_X, DRAWING_SIZE_Y);
        
        //Draws a grid onto the canvas
        if (drawGrid) {
	        g.setColor(Color.gray);
	        drawGrid(g, 25);
	        g.setColor(Color.black);
	        drawGrid(g, 100);
        }
        
        //Paint all the rooms (including contents)
        g.setColor(Color.black);
        g.drawRect(50, 50, 400, 250);	//First floor outline
        g.drawRect(50, 325, 400, 250);	//Second floor outline
        for(Room room:rooms) {
        	room.paint(g);
        }
    }  
    
    /**
     * Draws a grid onto the canvas with the given steps
     * 
     * @param g the graphics that will be used to draw
     * @param step the distance between lines
     */
    public void drawGrid(Graphics g, int step) {
    	for (int x=step; x<DRAWING_SIZE_X; x+=step) {
    		g.drawLine(x, 0, x, DRAWING_SIZE_Y);
    	}
    	for (int y=step; y<DRAWING_SIZE_Y; y+=step) {
    		g.drawLine(0, y, DRAWING_SIZE_X, y);
		}
    }
    
    /**
     * Sets the rooms stored by the canvas to the passed list
     * 
     * @param rooms the arraylist of rooms
     */
    public void setRooms(ArrayList<Room> rooms) {
    	this.rooms=rooms;
    }
    
}
