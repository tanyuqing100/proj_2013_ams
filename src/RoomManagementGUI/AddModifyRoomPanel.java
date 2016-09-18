package RoomManagementGUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import MainMenuGUI.DisplayFrame;
import MainMenuGUI.MenuButton;

import entities.*;
import commands.RoomsCommands;

public class AddModifyRoomPanel extends JPanel
{
	private static final long serialVersionUID = 1;
	private SpringLayout.Constraints cons = null;

	public AddModifyRoomPanel(final RoomData currentRoom, final BuildingData building)
	{
		setLayout(new SpringLayout());
		SpringLayout spLayout;
		try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }
		
		JLabel roomNumberLabel = new JLabel();
		JLabel squareFootageLabel = new JLabel();
		JLabel bedRoomNumberLabel = new JLabel();
		JLabel totalTenantsLabel = new JLabel();
		JLabel cost = new JLabel();


		roomNumberLabel.setText("Room Number");
		squareFootageLabel.setText("Square Feet");
		bedRoomNumberLabel.setText("Number of Bedrooms");
		totalTenantsLabel.setText("Total Capacity of Tenants");
		cost.setText("Cost of Appartment Room");
		
		
		final JTextField roomNumberTextBox = new JTextField();
		final JTextField squareFeetTextBox = new JTextField();
		final JTextField bedRoomNumberTextBox = new JTextField();
		final JTextField totalTenantsTextBox = new JTextField();
		final JTextField costTextBox = new JTextField();
	
		roomNumberTextBox.setPreferredSize(new Dimension(150, 21));
		squareFeetTextBox.setPreferredSize(new Dimension(150, 21));
		bedRoomNumberTextBox.setPreferredSize(new Dimension(150, 21));
		totalTenantsTextBox.setPreferredSize(new Dimension(150, 21));
		costTextBox.setPreferredSize(new Dimension(150, 21));

		
		MenuButton saveButton = new MenuButton("Save",60);
		
		if (currentRoom != null)
		{
			roomNumberTextBox.setText(currentRoom.getRoomNumber()+"");
			squareFeetTextBox.setText(currentRoom.getSquareFootage()+"");
			bedRoomNumberTextBox.setText(currentRoom.getBedroomNumber()+"");
			totalTenantsTextBox.setText(currentRoom.getTenantNumber()+"");
			costTextBox.setText(currentRoom.getCost()+"");

		}
		cons = spLayout.getConstraints(roomNumberLabel);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(30));

        cons = spLayout.getConstraints(roomNumberTextBox);
        cons.setX(Spring.constant(225));
        cons.setY(Spring.constant(30));
        
        cons = spLayout.getConstraints(squareFootageLabel);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(60));
        
        cons = spLayout.getConstraints(squareFeetTextBox);
        cons.setX(Spring.constant(225));
        cons.setY(Spring.constant(60));
        
        cons = spLayout.getConstraints(bedRoomNumberLabel);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(90));
        
        cons = spLayout.getConstraints(bedRoomNumberTextBox);
        cons.setX(Spring.constant(225));
        cons.setY(Spring.constant(90));
        
        cons = spLayout.getConstraints(totalTenantsLabel);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(120));

        cons = spLayout.getConstraints(totalTenantsTextBox);
        cons.setX(Spring.constant(225));
        cons.setY(Spring.constant(120));
        
        
        cons = spLayout.getConstraints(cost);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(150));
        
        
        cons = spLayout.getConstraints(costTextBox);
        cons.setX(Spring.constant(225));
        cons.setY(Spring.constant(150));
        
        cons = spLayout.getConstraints(saveButton);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(180));
        

		add(roomNumberLabel);
		add(roomNumberTextBox);
		add(squareFootageLabel);
		add(squareFeetTextBox);
		add(bedRoomNumberLabel);
		add(bedRoomNumberTextBox);
		add(totalTenantsLabel);
		add(totalTenantsTextBox);
		add(cost);
		add(costTextBox);

		
		
		add(saveButton);
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event){
				
				if (currentRoom != null)
				{
					final RoomsCommands commands = new RoomsCommands(currentRoom);
					try
					{
						commands.modifyRoom(Integer.parseInt(roomNumberTextBox.getText()), Integer.parseInt(squareFeetTextBox.getText()), Integer.parseInt(bedRoomNumberTextBox.getText()), Integer.parseInt(costTextBox.getText()), Integer.parseInt(totalTenantsTextBox.getText()));
					} catch (Exception e)
					{
						DisplayFrame frame = new DisplayFrame("Error:", "<html>invalid input<html>");
						frame.setLocation(400, 400);
						frame.setVisible(true);
					}
				}
				else
				{
					final RoomsCommands commands = new RoomsCommands();
					try
					{
						commands.addRoom(Integer.parseInt(roomNumberTextBox.getText()), Integer.parseInt(squareFeetTextBox.getText()), Integer.parseInt(bedRoomNumberTextBox.getText()), Integer.parseInt(costTextBox.getText()), Integer.parseInt(totalTenantsTextBox.getText()), building.getId());
					} catch (Exception e)
					{
						DisplayFrame frame = new DisplayFrame("Error:", "<html>invalid input<html>");
						frame.setLocation(400, 400);
						frame.setVisible(true);
					}
					
				}
			}
	    });
	}
}
