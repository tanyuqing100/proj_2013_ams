package AccountManagementGUI;


import MainMenuGUI.DisplayFrame;
import MainMenuGUI.MenuButton;
import commands.*;

import javax.swing.*;

import entities.TenantData;
//import java.lang.*;

import java.awt.Color;
//import java.awt.*;
import java.awt.event.*;
import java.text.*;
//import java.util.*;

public class ModifyTenantAccountPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private SpringLayout.Constraints cons = null;
	private SpringLayout spLayout;
 
	private TenantProfileCommand cmdTenant = null;
	private TenantData dataTenant = null;
	private int userID;
	
	private ViewPhonePanel pnViewPhone;
	private ChangePhonePanel pnChangePhone;
	private ViewEmailPanel pnViewEmail;
	private ChangeEmailPanel pnChangeEmail;
	private ViewCTypePanel pnViewCType;
	private ChangeCTypePanel pnChangeCType;
	private JTextField txPhone;
	private JTextField txEmail;
	private JRadioButton rbPhone;
	private JRadioButton rbEmail;
	
	public ModifyTenantAccountPanel(int userID){
		
		
		this.userID = userID;
		cmdTenant = new TenantProfileCommand();
		dataTenant = cmdTenant.getTenantProfile(userID);
		if (null == dataTenant){
			System.out.println("cannot access tenant profile");
			//errormessage = "cannot access tenant profile"
		}
		
		setLayout(new SpringLayout());
		
		try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        JLabel lbTitle = new JLabel("Tenant Profile");
		cons = spLayout.getConstraints(lbTitle);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(20));
        add(lbTitle);
        
        JLabel lbUsername1 = new JLabel("Name: ");
		cons = spLayout.getConstraints(lbUsername1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(60));
        add(lbUsername1);

        JLabel lbUsername2 = new JLabel(dataTenant.getFirstName()+" "+dataTenant.getLastName());
		cons = spLayout.getConstraints(lbUsername2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(60));
        add(lbUsername2);

        JLabel lbBuilding1 = new JLabel("Building: ");
		cons = spLayout.getConstraints(lbBuilding1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(100));
        add(lbBuilding1);

        JLabel lbBuilding2 = new JLabel(dataTenant.getBuildingName());
		cons = spLayout.getConstraints(lbBuilding2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(100));
        add(lbBuilding2);

        JLabel lbRoom1 = new JLabel("Room Number: ");
		cons = spLayout.getConstraints(lbRoom1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(140));
        add(lbRoom1);

        JLabel lbRoom2 = new JLabel(dataTenant.getRoomNumber()+"");
		cons = spLayout.getConstraints(lbRoom2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(140));
        add(lbRoom2);
        
        System.out.print(dataTenant.getFirstName());

        ///////////////////////
        

        
        JLabel lbPhone = new JLabel("Phone Number: ");
		cons = spLayout.getConstraints(lbPhone);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(180));
        add(lbPhone);
        
        pnViewPhone = new ViewPhonePanel();
        cons = spLayout.getConstraints(pnViewPhone);
        cons.setX(Spring.constant(175));
        cons.setY(Spring.constant(170));

        add(pnViewPhone);
        pnViewPhone.setVisible(true);
        
        pnChangePhone = new ChangePhonePanel();
        cons = spLayout.getConstraints(pnChangePhone);
        cons.setX(Spring.constant(175));
        cons.setY(Spring.constant(170));
        add(pnChangePhone);
        pnChangePhone.setVisible(false);
        
        JLabel lbEmail = new JLabel("Email: ");
		cons = spLayout.getConstraints(lbEmail);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(220));
        add(lbEmail);
        
        pnViewEmail = new ViewEmailPanel();
        cons = spLayout.getConstraints(pnViewEmail);
        cons.setX(Spring.constant(175));
        cons.setY(Spring.constant(210));
        add(pnViewEmail);
        pnViewEmail.setVisible(true);
        
        pnChangeEmail = new ChangeEmailPanel();
        cons = spLayout.getConstraints(pnChangeEmail);
        cons.setX(Spring.constant(175));
        cons.setY(Spring.constant(210));
        add(pnChangeEmail);
        pnChangeEmail.setVisible(false);

        JLabel lbCType = new JLabel("Preferred Contact Type: ");
		cons = spLayout.getConstraints(lbCType);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(260));
        add(lbCType);

        pnViewCType = new ViewCTypePanel();
        cons = spLayout.getConstraints(pnViewCType);
        cons.setX(Spring.constant(175));
        cons.setY(Spring.constant(250));
        add(pnViewCType);
        pnViewCType.setVisible(true);
        
        pnChangeCType = new ChangeCTypePanel();
        cons = spLayout.getConstraints(pnChangeCType);
        cons.setX(Spring.constant(175));
        cons.setY(Spring.constant(250));
        add(pnChangeCType);
        pnChangeCType.setVisible(false);
        
		MenuButton done= new MenuButton("Done",60);
		done.setForeground(Color.WHITE);
		cons = spLayout.getConstraints(done);
        cons.setX(Spring.constant(220));
        cons.setY(Spring.constant(350));
		done.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				getTopLevelAncestor().setVisible(false);
			}
	    });
        add(done);
		
	}
	
	private class ViewPhonePanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ViewPhonePanel(){
	        add(new JLabel(dataTenant.getPhoneNumber()+""));
	        MenuButton change= new MenuButton("Change",60);
	        change.setForeground(Color.WHITE);
	        change.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent evt){
					pnViewPhone.setVisible(false);
					pnChangePhone.setVisible(true);
				}
		    });
	        add(change);
		}
	}

	private class ChangePhonePanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ChangePhonePanel(){
			txPhone = new JFormattedTextField(NumberFormat.getIntegerInstance());
			txPhone.setColumns(15);
	        add(txPhone);
	        
	        //add(new JLabel(dataTenant.getPhoneNumber()+""));
	        MenuButton btAcceptPhone= new MenuButton("Accept Change",120);
	        btAcceptPhone.setForeground(Color.WHITE);
	        btAcceptPhone.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent evt){
					try
					{
						long value = Long.parseLong(txPhone.getText());
						cmdTenant.changeTenantPhone(userID, value);
						reload();	
					} catch (NumberFormatException e) 
					{
						DisplayFrame frame = new DisplayFrame("Error:", "<html>invalid phone number<html>");
						frame.setLocation(400, 400);
						frame.setVisible(true);
					}
				}
		    });
	        add(btAcceptPhone);
	        
	        MenuButton btCancelPhone= new MenuButton("Cancel",60);
	        btCancelPhone.setForeground(Color.WHITE);
	        btCancelPhone.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent evt){
					txPhone.setText("");
					pnViewPhone.setVisible(true);
					pnChangePhone.setVisible(false);
				}
		    });
	        add(btCancelPhone);
		}
	}
	
	private class ViewEmailPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ViewEmailPanel(){
	        add(new JLabel(dataTenant.getEmailAddress()));
	        MenuButton btChangeEmail= new MenuButton("Change",60);
	        btChangeEmail.setForeground(Color.WHITE);
	        btChangeEmail.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent evt){
					pnViewEmail.setVisible(false);
					pnChangeEmail.setVisible(true);
				}
		    });
	        add(btChangeEmail);
		}
	}

	private class ChangeEmailPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ChangeEmailPanel(){
			txEmail = new JTextField(15);
	        add(txEmail);
	        
	        MenuButton btAcceptEmail= new MenuButton("Accept Change",120);
	        btAcceptEmail.setForeground(Color.WHITE);
	        btAcceptEmail.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent evt){
					if (null == txEmail.getText()){
						String info = "<html>invalid email address<html>";
						DisplayFrame frame = new DisplayFrame("Error:", info);
						frame.setLocation(400, 400);
						frame.setVisible(true);
					}else{
						cmdTenant.changeTenantEmail(userID, txEmail.getText());
						reload();
					}
				}
		    });
	        add(btAcceptEmail);
	        
	        MenuButton btCancelEmail= new MenuButton("Cancel",60);
	        btCancelEmail.setForeground(Color.WHITE);
	        btCancelEmail.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent evt){
					txEmail.setText("");
					pnViewEmail.setVisible(true);
					pnChangeEmail.setVisible(false);
				}
		    });
	        add(btCancelEmail);
		}
	}

	private class ViewCTypePanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ViewCTypePanel(){
	        add(new JLabel(dataTenant.getContactType()));
	        
	        MenuButton btChangeCType= new MenuButton("Change",60);
	        btChangeCType.setForeground(Color.WHITE);
	        btChangeCType.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent evt){
					pnViewCType.setVisible(false);
					pnChangeCType.setVisible(true);
				}
		    });
	        add(btChangeCType);
		}
	}

	private class ChangeCTypePanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ChangeCTypePanel(){
			rbPhone = new JRadioButton("Phone");
			rbEmail = new JRadioButton("Email");
			ButtonGroup bgCType = new ButtonGroup();
			bgCType.add(rbPhone);
			bgCType.add(rbEmail);
			add(rbPhone);
			add(rbEmail);
			
			MenuButton btAcceptCType= new MenuButton("Accept Change",120);
			btAcceptCType.setForeground(Color.WHITE);
			btAcceptCType.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent evt){
					if (rbPhone.isSelected()){ cmdTenant.changeTenantCType(userID, 0);reload(); }
					else if (rbEmail.isSelected()){ cmdTenant.changeTenantCType(userID, 1);reload(); }
					else {
						String info = "<html>invalid contact type<html>";
						DisplayFrame frame = new DisplayFrame("Error:", info);
						frame.setLocation(400, 400);
						frame.setVisible(true);
					}
				}
		    });
	        add(btAcceptCType);
	  
	        MenuButton btCancelCType= new MenuButton("Cancel",60);
	        btCancelCType.setForeground(Color.WHITE);
	        btCancelCType.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent evt){
					rbPhone.setSelected(false);
					rbEmail.setSelected(false);
					pnViewCType.setVisible(true);
					pnChangeCType.setVisible(false);
				}
		    });
	        add(btCancelCType);
		}
	}
	
	private void reload(){
		ModifyTenantAccountPanel panel = new ModifyTenantAccountPanel(userID);
		ModifyAccountPanel.pnl1.removeAll();
		ModifyAccountPanel.pnl1.add(panel);
		ModifyAccountPanel.pnl1.validate();
		ModifyAccountPanel.pnl1.repaint();	
	}

}
	
	
	


