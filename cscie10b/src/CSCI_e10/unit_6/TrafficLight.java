package CSCI_e10.unit_6;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * 
 * @author M Bret Blackford
 * CSCI E-10b  Spring 2016
 * Unit 6 Problem Set
 * Question [3]
 *
 * This application models a traffic light by
 * drawing 3 circles of red, yellow, and green
 * in a horizontal rectangle.
 */
public class TrafficLight {
	

	public static void main(String args[]) {

		//create JPanels containing circles 
		MyDrawingPanel redPanel = new MyDrawingPanel(Color.RED);
		MyDrawingPanel yellowPanel = new MyDrawingPanel(Color.YELLOW);
		MyDrawingPanel greenPanel = new MyDrawingPanel(Color.GREEN);
		
		JFrame frame = new JFrame("StopLight");
		frame.setSize(170,500);
		frame.setLayout( new BorderLayout() );
		
		JPanel lights = new JPanel();
		lights.setLayout( new GridLayout(3,0) );
		
		lights.setSize(150,450);
		lights.add( redPanel );
		lights.add( yellowPanel );
		lights.add( greenPanel );
		
		//adding the light panel to the JFrame
		frame.add(lights, BorderLayout.CENTER);
		frame.add(new JPanel(), BorderLayout.NORTH);
	
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
}

/**
 * This class is used to create a JPanel of a circle
 * Color of circle is based on Color object passed in the constructor
 *
 */
class MyDrawingPanel extends JPanel{
	Color c;
	Border blackline = BorderFactory.createLineBorder(Color.black);
	
	/**
	 * Construction requires passing the Color desired
	 * for each circle to be created
	 * @param inColor
	 */
	public MyDrawingPanel(Color inColor) {
		c = inColor;
		//put a black line around each circle
		this.setLayout(new GridLayout(1,0) );
		this.setBorder(blackline);
	}
	
	/**
	 * Method creates a circle of the Color passed
	 * in the constructor
	 */
	public void paintComponent(Graphics g) {
		g.setColor( c );
		g.fillOval(0, 0, 150, 150);
	}
}
