package functionsGUIs;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import app.outputTable;
import componentsV2.Person;
import componentsV2.Playlist;
import componentsV2.Song;
import componentsV2.User;
import exceptions.PersonNotFoundException;
import exceptions.PlaylistNotFoundException;
import exceptions.UserNotFoundException;
import mainApp.MainMenu;

public class ListFollowers extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListFollowers frame = new ListFollowers();
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
	public ListFollowers(MainMenu mainMenu) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(75, 75, 300, 200);
		setTitle("List Followers");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblEntereTheUsers = new JLabel("Enter the person's id:");
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(21, Short.MAX_VALUE)
					.addComponent(lblEntereTheUsers)
					.addGap(48)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEntereTheUsers))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton btnView = new JButton("List Followers");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message messageBox;
				try {
					int id = Integer.parseInt(textField.getText());
					Person temp = Backend.getPerson(id);
					if(temp==null)
						throw new PersonNotFoundException(id);
					String[] columnTitles = new String[] {"ID", "Name", "DOB"};
					ArrayList<Person> followers = temp.getListOfFollowers();
					String[][] contents = new String[followers.size()][3];
					for(int i=0; i<followers.size(); i++)
					{
						contents[i] = new String[] {Integer.toString(followers.get(i).getId()), followers.get(i).getName(), followers.get(i).getDOB().toString()};
					}
					outputTable oTable = new outputTable("Followers", columnTitles, contents);
					oTable.pack();
					oTable.setVisible(true);
				}
				catch (NumberFormatException e1) {
					messageBox = new Message("Invalid ID format", mainMenu, null);
					messageBox.setTitle("Error");
					messageBox.pack();
					messageBox.setVisible(true);
				}
				catch (PersonNotFoundException e2) {
					messageBox = new Message(e2.getMessage(), mainMenu, null);
					messageBox.setTitle("Error");
					messageBox.pack();
					messageBox.setVisible(true);
				}
			}
		});
		panel.add(btnView);
		contentPane.setLayout(gl_contentPane);
	}

}
