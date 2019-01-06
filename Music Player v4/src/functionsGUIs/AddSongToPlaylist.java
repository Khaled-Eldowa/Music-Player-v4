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
import exceptions.EmptyFieldException;
import exceptions.PlaylistNotFoundException;
import exceptions.SongAlreadyAddedException;
import exceptions.SongNotFoundException;
import mainApp.MainMenu;

public class AddSongToPlaylist extends JFrame {

	private JPanel contentPane;
	private JTextField songIDField;
	private JTextField playlistIDField;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSongToPlaylist frame = new AddSongToPlaylist();
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
	public AddSongToPlaylist(MainMenu mainMenu) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(75, 75, 300, 200);
		setTitle("Add a song to the playlist");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		songIDField = new JTextField();
		songIDField.setColumns(10);
		
		JLabel lblEntereTheUsers = new JLabel("Enter the song's id:");
		
		JPanel panel = new JPanel();
		
		JLabel lblEntereThePlaylists = new JLabel("Enter the playlist's id:");
		
		playlistIDField = new JTextField();
		playlistIDField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblEntereTheUsers, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblEntereThePlaylists, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(playlistIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(songIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEntereTheUsers)
						.addComponent(songIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEntereThePlaylists)
						.addComponent(playlistIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton btnAddSong = new JButton("Add Song to Playlist");
		btnAddSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message messageBox;
				try {
					int songID = Integer.parseInt(songIDField.getText());
					int playlistID = Integer.parseInt(playlistIDField.getText());
					Song song = Backend.getSong(songID);
					if(song==null)
						throw new SongNotFoundException(songID);
					Playlist playlist = Backend.getPlaylist(playlistID);
					if(playlist==null)
						throw new PlaylistNotFoundException(playlistID);
					int check = Backend.addSongtoPlaylist(songID, playlistID);
					if(check<0)
						throw new SongAlreadyAddedException(songID);
					messageBox = new Message("The Song with id = "+ songID + " was added successfully to the playlist with id = " + playlistID, mainMenu, AddSongToPlaylist.this);
					messageBox.setTitle("Success");
				}
				catch (NumberFormatException e1) {
					messageBox = new Message("Invalid ID format", mainMenu, null);
					messageBox.setTitle("Error");
				}
				catch (SongNotFoundException e2) {
					messageBox = new Message(e2.getMessage() + "\nNo song was added!", mainMenu, null);
					messageBox.setTitle("Error");
				}
				catch (PlaylistNotFoundException e3) {
					messageBox = new Message(e3.getMessage() + "\nNo song was added!", mainMenu, null);
					messageBox.setTitle("Error");
				}
				catch (SongAlreadyAddedException e3) {
					messageBox = new Message(e3.getMessage(), mainMenu, null);
					messageBox.setTitle("Error");
				}
				messageBox.pack();
				messageBox.setVisible(true);
			}
		});
		panel.add(btnAddSong);
		contentPane.setLayout(gl_contentPane);
	}

}
