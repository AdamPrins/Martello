package gui;
import java.awt.*;
import javax.swing.*;

import hotel.UnknownUser;
import hotel.event.HotelEvent;
import hotel.event.HotelEventString;

import java.awt.event.*;
import java.util.Date;

/**
 *	Handles all the display elements 
 *
 * @author Adam Prins
 */
public class GUI implements ActionListener {
	
	public static void main(String[] args) {
		new GUI();
	}
	
	/* JMenu File items */
    private JMenuItem quitItem;
    private JMenuItem redrawItem;
    
    /* Buttons for stepping though events */
    private JButton stepForwards;
    private JButton stepBackwards;
    private JButton stepForwards10;
    private JButton stepBackwards10;
    
    /* Displays time */
    private JLabel outputDate;
    private JLabel outputEventCount;
    private JTextArea outputEvent;
    
    private int eventCounter;
    
    /* The canvas where the drawing is performed */
    private Canvas canvas;
    
    /* The size of the canvas in terms of grid bag coordinates */
    private static final int CANVASE_SIZE = 10;
    
    Controller controller;
    
    
    /**
     * Constructor of the GUI 
     * Initializes the frame and configures the layouts
     * Sets all listeners
     */
	public GUI() {
		controller = new Controller();
		JFrame frame = new JFrame("tracker"); 
	    Container contentPane = frame.getContentPane(); 
	    contentPane.setLayout(new GridBagLayout());
	    // get the content pane so we can put stuff in
	    
	    createMenus(frame);
	    createCanvasPanel(contentPane);
	    createPanelSpacing(contentPane);
	    createInterfacePanel(contentPane);
	    
	    frame.setPreferredSize(new Dimension(1000,700));
	    frame.pack(); // pack contents into our frame
        frame.setResizable(false); // we can resize it
        frame.setVisible(true); // make it visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stops the program when the x is pressed
        
        
        canvas.setRooms(controller.getRooms());
        canvas.repaint();
	}
	
	/**
	 * Creates the menus for this GUI
	 * @param frame the frame that we will add the menus to
	 */
	private void createMenus(JFrame frame) {
		JMenuBar menubar = new JMenuBar();
	    frame.setJMenuBar(menubar); // add menu bar to our frame
	
	    //Creates and adds menus to our JFrame
	    JMenu fileMenu = new JMenu("File");
	    menubar.add(fileMenu);
	    
	    //Populates the fileMenu
	    quitItem = new JMenuItem("Quit");
	    fileMenu.add(quitItem);
	    quitItem.addActionListener(this);
	    
	    redrawItem = new JMenuItem("Redraw");
	    fileMenu.add(redrawItem);
	    redrawItem.addActionListener(this);
	    
	}
	
	/**
	 * Creates the content of the board Panel and adds it to the given contentPane
	 * @param contentPane the contentPane of the frame that we are adding this JPanel to
	 */
	private void createCanvasPanel(Container contentPane) {
		canvas = new Canvas();
	    
	    GridBagConstraints c = new GridBagConstraints();
	    c.gridx=0;					c.gridy=0;
		c.gridwidth=1;				c.gridheight=CANVASE_SIZE;
	    c.ipadx = 10; 				c.ipady = CANVASE_SIZE;	//c.ipadx fully controls the space between the left and the canvas
	    c.weightx=1;
	    contentPane.add(Box.createGlue(),c);
	    
	    c = new GridBagConstraints();
	    c.anchor = GridBagConstraints.LINE_START;
	    c.gridx=1;					c.gridy=0;
	    c.gridwidth=CANVASE_SIZE;	c.gridheight=CANVASE_SIZE;
	    c.weightx=1;
	    contentPane.add(canvas,c);
	}
	
	/**
	 * Creates the spacing between the two JPanels
	 * @param contentPane the contentPane of the frame that we are adding this JPanel to
	 */
	private void createPanelSpacing(Container contentPane) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=CANVASE_SIZE+2;		c.gridy=0;
		c.gridwidth=1;				c.gridheight=6;
	    c.ipadx = 5; 				c.ipady = 5;	//c.ipadx fully controls the space between contentPane and interfacePanel
	    c.weightx=0;
	    contentPane.add(Box.createGlue(),c);
	}
	
	/**
	 * Creates the Interface JPanel and adds it to the given contentPane
	 * @param contentPane the contentPane of the frame that we are adding this JPanel to
	 */
	private void createInterfacePanel(Container contentPane) {
		JPanel interfacePanel = new JPanel();
	    interfacePanel.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    c.anchor = (GridBagConstraints.LINE_START);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    
	    stepBackwards = new JButton("Step Backwards");
	    stepBackwards.addActionListener(this);
	    c.gridx = 0;			c.gridy = 2;
	    interfacePanel.add(stepBackwards,c);
	    
	    stepForwards = new JButton("Step Forwards");
	    stepForwards.addActionListener(this);
	    c.gridx = 1;			c.gridy = 2;
	    interfacePanel.add(stepForwards,c);
	    
	    stepBackwards10 = new JButton("Step Backwards 10");
	    stepBackwards10.addActionListener(this);
	    c.gridx = 0;			c.gridy = 3;
	    interfacePanel.add(stepBackwards10,c);
	    
	    stepForwards10 = new JButton("Step Forwards 10");
	    stepForwards10.addActionListener(this);
	    c.gridx = 1;			c.gridy = 3;
	    interfacePanel.add(stepForwards10,c);
	    
	    c.gridx = 0;			c.gridy = 4;
	    c.weightx=1;
	    c.gridwidth=3;
	    outputDate = new JLabel("Current Time: ");
	    outputDate.setPreferredSize(new Dimension(150,30));
	    interfacePanel.add(outputDate,c);
	    
	    eventCounter=0;
	    c.gridx = 0;			c.gridy = 5;
	    c.weightx=1;
	    c.gridwidth=3;
	    outputEventCount = new JLabel(eventCounter+"/"+controller.numberOfEvents());
	    outputEventCount.setPreferredSize(new Dimension(150,30));
	    interfacePanel.add(outputEventCount,c);
	    
	    c.gridx = 0;			c.gridy = 6;
	    c.weightx=1;
	    c.gridwidth=3; 			c.gridheight=2;
	    outputEvent = new JTextArea("Event: ");
	    outputEvent.setPreferredSize(new Dimension(150,40));
	    outputEvent.setLineWrap(true);
	    outputEvent.setWrapStyleWord(true);
	    outputEvent.setEditable(false);
	    interfacePanel.add(outputEvent,c);
	    
		c.gridx=CANVASE_SIZE+3;	c.gridy=0;
		c.gridwidth=4;			c.gridheight=CANVASE_SIZE;
	    c.ipadx = 5; 			c.ipady = 5;
	    c.weightx=1;
	    contentPane.add(interfacePanel,c);
	}
	
	
	/** 
	 * This action listener is called when the user clicks on 
     * any of the GUI's buttons. 
     */
    public void actionPerformed(ActionEvent e)
    {
        Object o = e.getSource(); // get the action 
        
        // see if it's a JButton
        if (o instanceof JButton) {
        	JButton button = (JButton) o;
        	 actionOnJButton(button);
        }
        // see if its a JMenuItem
        else if (o instanceof JMenuItem){
            JMenuItem item = (JMenuItem)o;
            actionOnJMenu(item);
        }
    }
    
    /**
     * This method handles the pressing of a JButton
     * 
     * @param button the button that was pressed
     */
	private void actionOnJButton(JButton button) {
		long time=0;
		if (button == stepBackwards) {
			HotelEvent event = controller.stepBackwards();
			if (event!=null) {
				eventCounter--;
				outputDate.setText("Current Time: " + new Date(event.getTime()*1000));
				time=event.getTime();
				outputEvent.setText("Event: " + event);
			}
		}
		if (button == stepBackwards10) {
			for (int i=0; i<10; i++) {
				HotelEvent event = controller.stepBackwards();
				if (event!=null) {
					eventCounter--;
					outputDate.setText("Current Time: " + new Date(event.getTime()*1000));
					time=event.getTime();
					outputEvent.setText("Event: " + event);
				}
			}
		}
		else if (button == stepForwards) {
			HotelEvent event = controller.stepForwards();
			if (event!=null) {
				eventCounter++;
				outputDate.setText("Current Time: " + new Date(event.getTime()*1000));
				time=event.getTime();
				outputEvent.setText("Event: " + event);
			}
		}
		else if (button == stepForwards10) {
			for (int i=0; i<10; i++) {
				HotelEvent event = controller.stepForwards();
				if (event!=null) {
					eventCounter++;
					outputDate.setText("Current Time: " + new Date(event.getTime()*1000));
					time=event.getTime();
					outputEvent.setText("Event: " + event);
				}
			}
		}
		outputEventCount.setText(eventCounter+"/"+controller.numberOfEvents());
		UnknownUser.clearMotionUsers(time);
		drawCanvas();
	
	}
	
	/**
	 * This method handles the pressing of a JMenuItem
	 * 
	 * @param item the item that was selected
	 */
	private void actionOnJMenu(JMenuItem item) {
		if (item == quitItem) {
            System.exit(0);
        }
		else if (item == redrawItem) {
			drawCanvas();
		}
	}
	
	/**
	 * Draw the canvas
	 */
	public void drawCanvas() {
		canvas.repaint();
	}
	
}
