package BuildingManagementGUI;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import MainMenuGUI.MenuButton;

import entities.BuildingData;
import commands.BuildingsCommand;

public class AddModifyBuildingPanel extends JPanel
{
	private static final long serialVersionUID = 1;
	private SpringLayout.Constraints cons = null;

	
	public AddModifyBuildingPanel(final BuildingData currentBuilding)
	{		
		setLayout(new SpringLayout());
		SpringLayout spLayout;
		try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }
		
		JLabel nameLabel = new JLabel();
		JLabel addressLabel = new JLabel();
		nameLabel.setText("Name");
		addressLabel.setText("Address");
		
		final JTextField nameTextBox = new JTextField();
		final JTextField addressTextBox = new JTextField();
		nameTextBox.setPreferredSize(new Dimension(150, 21));
		addressTextBox.setPreferredSize(new Dimension(150, 21));
		
		MenuButton saveButton = new MenuButton("Save",60);
		
		if (currentBuilding != null)
		{
			nameTextBox.setText(currentBuilding.getName());
			addressTextBox.setText(currentBuilding.getAddress());
		}
		
		cons = spLayout.getConstraints(nameLabel);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(30));
        
        cons = spLayout.getConstraints(nameTextBox);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(30));
        
        cons = spLayout.getConstraints(addressLabel);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(60));
        
        cons = spLayout.getConstraints(addressTextBox);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(60));
		
        cons = spLayout.getConstraints(saveButton);
        cons.setX(Spring.constant(120));
        cons.setY(Spring.constant(90));
		
        
		add(nameLabel);
		add(nameTextBox);
		
		add(addressLabel);
		add(addressTextBox);
		add(saveButton);
		
		if (currentBuilding != null)
		{
			final BuildingsCommand commands = new BuildingsCommand(currentBuilding);

			MenuButton deleteButton = new MenuButton("Delete",60);
			
			 cons = spLayout.getConstraints(deleteButton);
		     cons.setX(Spring.constant(200));
		     cons.setY(Spring.constant(90));
			
			add(deleteButton);
			
			deleteButton.addActionListener(new ActionListener(){
				public void actionPerformed (ActionEvent event){
					commands.removeBuilding();
				}
		    });
			
		}
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event){
				if (currentBuilding != null)
				{
					final BuildingsCommand commands = new BuildingsCommand(currentBuilding);
					commands.modifyBuilding(nameTextBox.getText(), addressTextBox.getText());
				}
				else
				{
					final BuildingsCommand commands = new BuildingsCommand();
					commands.addBuilding(nameTextBox.getText(), addressTextBox.getText());
				}
			}
	    });
	}
}
