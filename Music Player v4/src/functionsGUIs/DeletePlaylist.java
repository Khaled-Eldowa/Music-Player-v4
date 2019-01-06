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
import componentsV2.Playlist;
import componentsV2.Song;
import componentsV2.User;
import exceptions.EmptyFieldException;
import exceptions.PlaylistNotFoundException;
import exceptions.SongNotFoundException;
import exceptions.UserNotFoundException;
import mainApp.MainMenu;

public class DeletePlaylist extends JFrame {

	private JPanel contentPane;
	private JTextField playlistIDField;
	private JTextField userIDField;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletePlaylist frame = new DeletePlaylist();
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
	public DeletePlaylist(MainMenu mainMenu) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(75, 75, 300, 200);
		setTitle("Delete Playlist for a User");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		playlistIDField = new JTextField();
		playlistIDField.setColumns(10);
		
		JLabel lblEntereTheUsers = new JLabel("Enter the playlist's id:");
		
		JPanel panel = new JPanel();
		
		JLabel lblEnterTheUsers = new JLabel("Enter the user's id:");
		
		userIDField = new JTextField();
		userIDField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblEnterTheUsers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblEntereTheUsers, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(userIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(playlistIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEntereTheUsers)
						.addComponent(playlistIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterTheUsers)
						.addComponent(userIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message messageBox;
				try {
					int playlistId = Integer.parseInt(playlistIDField.getText());
					Playlist playlist = Backend.getPlaylist(playlistId);
					if(playlist==null)
						throw new PlaylistNotFoundException(playlistId);
					
					int userId = Integer.parseInt(userIDField.getText()); 
					User user = Backend.getUser(userId);
					if(user==null)
						throw new UserNotFoundException(userId);
					
					int check = Backend.deletePlaylist(userId, playlistId);
					
					if(check == -1)
						throw new Exception("The playlist was not previously added to the user in the first place.");
					else if (check == -2)
						messageBox = new Message("The Playlist with id = "+ playlistId + " was successfully deleted for all users, because this user was its owner", mainMenu, DeletePlaylist.this);
					else 
						messageBox = new Message("The Playlist with id = "+ playlistId + " was deleted successfully for the user with id = " + userId, mainMenu, DeletePlaylist.this);
					messageBox.setTitle("Success");
				}
				catch (NumberFormatException e1) {
					messageBox = new Message("Invalid ID format", mainMenu, null);
					messageBox.setTitle("Error");
				}
				catch (PlaylistNotFoundException e2) {
					messageBox = new Message(e2.getMessage(), mainMenu, null);
					messageBox.setTitle("Error");
				}
				catch (UserNotFoundException e3) {
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
