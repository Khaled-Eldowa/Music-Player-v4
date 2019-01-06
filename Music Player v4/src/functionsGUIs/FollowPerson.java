package functionsGUIs;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import app.Backend;
import app.Message;
import componentsV2.Person;
import componentsV2.Playlist;
import componentsV2.User;
import exceptions.PersonNotFoundException;
import exceptions.PlaylistNotFoundException;
import exceptions.UserNotFoundException;
import mainApp.MainMenu;

public class FollowPerson extends JFrame {

	private JPanel contentPane;
	private JTextField yourIDField;
	private JTextField toFollowIDField;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FollowPerson frame = new FollowPerson();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public FollowPerson(MainMenu mainMenu) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(75, 75, 400, 200);
		setTitle("Follow a Person");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		yourIDField = new JTextField();
		yourIDField.setColumns(10);
		
		JLabel lblEntereTheUsers = new JLabel("Enter your id:");
		
		JPanel panel = new JPanel();
		
		JLabel lblEnterTheUsers = new JLabel("Enter the person to follow id:");
		
		toFollowIDField = new JTextField();
		toFollowIDField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEntereTheUsers, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEnterTheUsers, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(toFollowIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(yourIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEntereTheUsers)
						.addComponent(yourIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterTheUsers)
						.addComponent(toFollowIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton btnDelete = new JButton("Follow");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message messageBox;
				try {
					int yourID = Integer.parseInt(yourIDField.getText());
					Person you = Backend.getPerson(yourID);
					if(you==null)
						throw new PersonNotFoundException(yourID);
					
					int toFollowID = Integer.parseInt(toFollowIDField.getText()); 
					Person toFollow = Backend.getPerson(toFollowID);
					if(toFollow==null)
						throw new PersonNotFoundException(toFollowID);
					
					boolean check = Backend.followPerson(you, toFollow);
					
					if(!check)
						throw new Exception("You are already following that person");
					
					messageBox = new Message("Followed Successfully", mainMenu, FollowPerson.this);
					messageBox.setTitle("Success");
				}
				catch (NumberFormatException e1) {
					messageBox = new Message("Invalid ID format", mainMenu, null);
					messageBox.setTitle("Error");
				}
				catch (PersonNotFoundException e3) {
					messageBox = new Message(e3.getMessage(), mainMenu, null);
					messageBox.setTitle("Error");
				} catch (Exception e4) {
					messageBox = new Message(e4.getMessage(), mainMenu, null);
					messageBox.setTitle("Error");
				}
				messageBox.pack();
				messageBox.setVisible(true);
			}
		});
		panel.add(btnDelete);
		contentPane.setLayout(gl_contentPane);
	}

}
