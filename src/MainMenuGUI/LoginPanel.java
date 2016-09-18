package MainMenuGUI;





import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;

import ClientSideGUI.ClientSideFrame;


import startup.Database;

import commands.LoginCommand;

/** 
 * The panel to obtain input to log in the system, 
 * and then it will start the main system. 
 */
public class LoginPanel extends JPanel 
{
	/**
	 * The panel for the entry of username.
	 */
	ValueEntryPanel userNamePanel;
	
	/**
	 * The panel for the entry of the password.
	 */
	ValuePasswordPanel passwordPanel;

	
	/**
	 * A panel for an error message, if an error should occur.
	 */
	JTextArea error = null;

	/**
	 * Create a panel with a field for the entry of username, 
	 * a field for entry of password and a submit button to 
	 * submit the data for login.
	 */
	public LoginPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createVerticalGlue());

		JLabel prompt = new JLabel("AMS Login");
		prompt.setFont(new Font("Serif", Font.BOLD, 20));
		prompt.setMaximumSize(prompt.getPreferredSize());
		add(prompt);
		prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());

		userNamePanel = new ValueEntryPanel("username");
		userNamePanel.setMaximumSize(userNamePanel.getPreferredSize());
		add(userNamePanel);
		userNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());

		passwordPanel = new ValuePasswordPanel("password");
		passwordPanel.setMaximumSize(passwordPanel.getPreferredSize());
		add(passwordPanel);
		passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());

		MenuButton Submit = new MenuButton("Login",100);
		Submit.setForeground(Color.WHITE);
		Submit.addMouseListener( new SubmitMouseListener());
		Submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Submit);
		add(Box.createVerticalGlue());
		
		MenuButton exitButton = new MenuButton("Cancel",100);
		exitButton.setMaximumSize(exitButton.getPreferredSize());
		add(exitButton);
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				Database.closeConnection();
				System.exit(0);
			}
	    });
		add(Box.createVerticalGlue());
	}

	/**
	 * The listener for the press of the submit button.
	 * When it happens, obtain the data values from the fields, and
	 * try to login.  Afterwards, create and make visible the window
	 * with the main options for the user.
	 */
	private class SubmitMouseListener implements MouseListener
	{
		
		public SubmitMouseListener(){
		}
		
		public void mouseClicked (MouseEvent event)
		{
			if (error != null)
			{
				remove(error);  // remove error from previous submission
				error = null;
			}
			String name = userNamePanel.getValueAsString();
			String password = passwordPanel.getValueAsString();
			LoginCommand lgn = new LoginCommand();
			lgn.Login(name, password);
			if (lgn.wasSuccessful())
			{
				getTopLevelAncestor().setVisible(false);
				if(lgn.isManager()){
					MainMenuFrame frame = new MainMenuFrame(lgn.getUserID());  
					frame.setLocation(300, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}else{
					ClientSideFrame frame = new ClientSideFrame(lgn.getUserID());
					frame.setLocation(300, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
				
			}
			else
			{
				/* Divide the error message into lines short enough to fit in the 
				 * window, and store the message in the error text area. */
				error = new JTextArea(SplitString.at(lgn.getErrorMessage(), 50));
				error.setMaximumSize(error.getPreferredSize());
				add(error);
				error.setAlignmentX(Component.CENTER_ALIGNMENT);
				//add(Box.createVerticalGlue());
				revalidate();
			}
		}


		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	public static final long serialVersionUID = 1;
}