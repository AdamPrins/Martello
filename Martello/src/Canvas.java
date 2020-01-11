
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * This is the canvas for drawing the geometry of island
 * 
 *  
 * @authors Adam Prins
 * 
 * @version 0.3.0 
 * 		Paint booleans all default to false now 
 *		
 */
public class Canvas extends JPanel {
	
	boolean drawGrid=false;
		
	private static final long serialVersionUID = 3290417118952335835L;
	
	/* The pixels needed to display the game window */
	public static int DRAWING_SIZE_X = 550;
	public static int DRAWING_SIZE_Y = 625;
	BufferedImage image;
	
	
	/**
	 * The constructor of this drawing component
	 */
	public Canvas() {
		/* creates a boarder around the canvas */
        setBorder(BorderFactory.createLineBorder(Color.black));
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
        
        if (drawGrid) {
	        g.setColor(Color.gray);
	        drawGrid(g, 25);
	        g.setColor(Color.black);
	        drawGrid(g, 100);
        }
        
        g.setColor(Color.black);
        g.drawRect(50, 50, 400, 250);	//First floor outline
        g.drawRect(50, 325, 400, 250);	//Second floor outline
        for(Room room:Room.setupRooms()) {
        	room.paint(g);
        }
    }  
    
    public void drawGrid(Graphics g, int step) {
    	for (int x=step; x<DRAWING_SIZE_X; x+=step) {
    		g.drawLine(x, 0, x, DRAWING_SIZE_Y);
    	}
    	for (int y=step; y<DRAWING_SIZE_Y; y+=step) {
    		g.drawLine(0, y, DRAWING_SIZE_X, y);
		}
    }
    
}
