package ClientSideGUI;

//import commands.*;
import containers.*;
import java.awt.*;
import java.awt.event.*;
//import java.text.*;
import java.util.*;
import javax.swing.*;

import entities.NoticeData;

public class NoticeOptionPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	JTextArea error = null;
	
	public NoticeOptionPanel(final int userID){
		
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createVerticalGlue());

		JLabel lbTitle = new JLabel("View Options:");
		lbTitle.setMaximumSize(lbTitle.getPreferredSize());
		add(lbTitle);
		lbTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(Box.createVerticalGlue());

		JCheckBox ckUnread = new JCheckBox("Show unread notifications only");
		ckUnread.setOpaque(false);
		ckUnread.setMaximumSize(ckUnread.getPreferredSize());
		add(ckUnread);
		ckUnread.setAlignmentX(Component.LEFT_ALIGNMENT);
		ckUnread.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event)
			{
				/*
				String query = "SELECT * " +
						"FROM notifications, notiftype, users " +
						"WHERE nt_code = n_notice_type AND " +
						"n_sender = u_id AND " +
						"n_receiver = " + userID + " AND " +
						"n_read = false";
				*/
				LinkedList<NoticeData> lsNotice = new LinkedList<NoticeData>(); 
				Iterator<NoticeData> itrNotice = null;
				NoticeData curNotice = null;
				try {itrNotice = NoticeList.getInstance().iterator();} 
				catch (Exception e) {e.printStackTrace();}

				while (itrNotice.hasNext()){
					curNotice = itrNotice.next();
					if ((curNotice.getReceiver() == userID)&&(curNotice.getRead() == 0)){
						lsNotice.add(curNotice);
					}
				}
				
				NoticeDisplayPanel pnNoticeDisplay = new NoticeDisplayPanel(userID, lsNotice);
				JScrollPane scNoticeDisplay = new JScrollPane(pnNoticeDisplay);
				scNoticeDisplay.setOpaque(false);
				scNoticeDisplay.getViewport().setOpaque(false);
				scNoticeDisplay.setBorder(null);
				ClientSidePanel.pnl4.removeAll();
				ClientSidePanel.pnl4.add(scNoticeDisplay);
				ClientSidePanel.pnl4.validate();
				ClientSidePanel.pnl4.repaint();
			}
		});
		add(Box.createVerticalGlue());
		
		JCheckBox ckMonth = new JCheckBox("Show last 30 days notifications only");
		ckMonth.setOpaque(false);
		ckMonth.setMaximumSize(ckMonth.getPreferredSize());
		add(ckMonth);
		ckMonth.setAlignmentX(Component.LEFT_ALIGNMENT);
		ckMonth.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event)
			{
				/*
				String query = "SELECT * " +
						"FROM notifications, notiftype, users " +
						"WHERE nt_code = n_notice_type AND " +
						"n_sender = u_id AND " +
						"n_receiver = " + userID + " AND " +
						"CAST(n_date AS DATE) > (current_date - 30)";
				*/
				LinkedList<NoticeData> lsNotice = new LinkedList<NoticeData>(); 
				Iterator<NoticeData> itrNotice = null;
				NoticeData curNotice = null;
				try {itrNotice = NoticeList.getInstance().iterator();} 
				catch (Exception e) {e.printStackTrace();}

				while (itrNotice.hasNext()){
					curNotice = itrNotice.next();
					if ((curNotice.getReceiver() == userID)){
						lsNotice.add(curNotice);
					}
				}

				NoticeDisplayPanel pnReceiveNotice = new NoticeDisplayPanel(userID, lsNotice);
				JScrollPane scReceiveNotice = new JScrollPane(pnReceiveNotice);
				scReceiveNotice.setOpaque(false);
				scReceiveNotice.getViewport().setOpaque(false);
				scReceiveNotice.setBorder(null);
				ClientSidePanel.pnl4.removeAll();
				ClientSidePanel.pnl4.add(scReceiveNotice);
				ClientSidePanel.pnl4.validate();
				ClientSidePanel.pnl4.repaint();	
			}
		});
		add(Box.createVerticalGlue());
	}

/*	private String getCurDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		return curDate;
	}
*/
	
}
