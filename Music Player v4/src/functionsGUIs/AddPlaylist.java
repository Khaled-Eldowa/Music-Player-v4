package functionsGUIs;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import app.AuxillaryReturnSong;
import app.Backend;
import app.Message;
import componentsV2.Album;
import componentsV2.Playlist;
import componentsV2.Song;
import componentsV2.User;
import exceptions.AlbumNotFoundException;
import exceptions.EmptyFieldException;
import exceptions.UserNotFoundException;
import mainApp.MainMenu;

public class AddPlaylist extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField userIDField;
	private JPanel panel;
	private JButton btnAddPlaylist;
	private JButton btnAddSongTo;
	private ArrayList<Song> listOfSongs = new ArrayList<>();

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPlaylist frame = new AddPlaylist();
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
	public AddPlaylist(MainMenu mainMenu) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Add a New Playlist");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		userIDField = new JTextField();
		userIDField.setColumns(10);
		
		JLabel lblRealName = new JLabel("Playlist Name:");
		
		JLabel lblDob = new JLabel((String) null);
		
		JLabel lblUsername = new JLabel("User ID:");
		
		
		panel = new JPanel();
		
		btnAddSongTo = new JButton("Add Songs to the Playlist");
		btnAddSongTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AuxillaryReturnSong aReturnSong = new AuxillaryReturnSong(mainMenu, AddPlaylist.this);
				aReturnSong.pack();
				aReturnSong.setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(59)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddSongTo)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(userIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblRealName)
									.addComponent(lblDob, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
								.addGap(90)
								.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(lblDob))
						.addComponent(lblRealName))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsername)
						.addComponent(userIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addComponent(btnAddSongTo)
					.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		btnAddPlaylist = new JButton("Add Playlist");
		btnAddPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Message messageBox;
				try {
					String temp;
					temp = nameField.getText();
					if(temp.equals(""))
						throw new EmptyFieldException("Playlist Name");
					LocalDate date = LocalDate.now();
					int id = Integer.parseInt(userIDField.getText());
					User user = Backend.getUser(id);
					if(user==null)
						throw new UserNotFoundException(id);
					Playlist newPlaylist = Backend.addPlaylist(nameField.getText(), user, date, listOfSongs);
					messageBox = new Message("The Playlist was created successfully with id " + newPlaylist.getId(), mainMenu, AddPlaylist.this);
					messageBox.setTitle("Success");
				}
				catch (EmptyFieldException e) {
					messageBox = new Message(e.getMessage() + "\nThe playlist was not added!", mainMenu, null);
					messageBox.setTitle("Error");
				}
				catch (NumberFormatException e1) {
					messageBox = new Message("Invalid User ID format", mainMenu, null);
					messageBox.setTitle("Error");
				}
				catch (UserNotFoundException e) {
					messageBox = new Message(e.getMessage(), mainMenu, null);
					messageBox.setTitle("Error");
				}
				messageBox.pack();
				messageBox.setVisible(true);
			}
		});
		panel.add(btnAddPlaylist);
		contentPane.setLayout(gl_contentPane);
	}
	
	public boolean addSong(Song song)
	{
		if(listOfSongs.contains(song))
			return false;
		else
		{
			listOfSongs.add(song);
			return true;
		}
	}

}
