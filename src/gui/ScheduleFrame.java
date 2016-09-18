/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.*;
/**
 *
 * @author mjl079
 */
public class ScheduleFrame extends JFrame{
  	/** The standard width for the frame. */
	public static final int DEFAULT_WIDTH = 550;
	
	/** The standard height for the frame. */
	public static final int DEFAULT_HEIGHT = 600;
	
	/** 
	 * Create the frame for login. 
	 */
	public ScheduleFrame ()
	{
		setTitle("Notification System");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		SchedulePanel panel = new SchedulePanel(1);
		add(panel);
	}

	public static final long serialVersionUID = 1;
 
}
