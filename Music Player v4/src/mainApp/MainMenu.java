package mainApp;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import app.Backend;
import app.DataPopulation;
import app.outputTable;
import componentsV2.Song;
import componentsV2.User;
import functionsGUIs.AddArtist;
import functionsGUIs.AddPlaylist;
import functionsGUIs.AddSong;
import functionsGUIs.AddSongToPlaylist;
import functionsGUIs.AddUser;
import functionsGUIs.DeleteArtist;
import functionsGUIs.DeletePlaylist;
import functionsGUIs.DeleteSong;
import functionsGUIs.DeleteUser;
import functionsGUIs.FollowPerson;
import functionsGUIs.GetPlaylistDetails;
import functionsGUIs.GetSongAlbum;
import functionsGUIs.ListFollowers;
import functionsGUIs.ViewUserDetails;
import security.DigestManager;
import serialization.SerializationManager;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainMenu {

	private JFrame frame;
	private static JTextPane outputPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/**
		 * Main Frame Invocation + initial operations (Db read, deserialize, check MD5) 
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					
					//Do the required operations at start:
					Backend.dataStorage.readData(window); //get data from the database
					SerializationManager.deserilaizeAndCheckMD5(); //deserialize the users file and check its MD5
					
					window.frame.pack();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//when closing the program:
		frame.addComponentListener(new ComponentAdapter() { 
			@Override
			public void componentHidden(ComponentEvent arg0) { 
				//Before exiting
				Backend.dataStorage.writeData(MainMenu.this); //write data to the database
				SerializationManager.serializeUsers(); //serialize the users
				DigestManager.generateAndStoreMD5(); //generate and store MD5 for the users
				MainMenu.this.frame.dispose(); //dispose of the main frame
				System.exit(0); //close the program
			}
		});
		frame.setTitle("Music Player");
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 500, 350);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //closes in the action listener
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblOutput = new JLabel("Logs:");
		lblOutput.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblOutput.setForeground(Color.WHITE);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(Color.WHITE);
		btnClear.setBackground(Color.RED);
		btnClear.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		
		JTextPane announcement = new JTextPane();
		announcement.setEditable(false);
		announcement.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		announcement.setForeground(Color.WHITE);
		announcement.setBackground(Color.BLACK);
		announcement.setText("Choose the Required Functionality from the Menu Bar");
		
		JButton btnLoadMusicplayerData = new JButton("Load Data from Database");
		btnLoadMusicplayerData.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnLoadMusicplayerData.setForeground(Color.WHITE);
		btnLoadMusicplayerData.setBackground(new Color(255, 69, 0));
		btnLoadMusicplayerData.addActionListener(new ActionListener() {
			/**
			 * Load data from the database
			 */
			public void actionPerformed(ActionEvent e) {
				Backend.dataStorage.readData(MainMenu.this);
			}
		});
		
		JButton btnSaveData = new JButton("Save Data to Database");
		btnSaveData.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnSaveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Backend.dataStorage.writeData(MainMenu.this);
			}
		});
		btnSaveData.setForeground(Color.WHITE);
		btnSaveData.setBackground(new Color(255, 69, 0));
		
		JButton btnClearDatabase = new JButton("Clear Database");
		btnClearDatabase.addActionListener(new ActionListener() {
			/**
			 * Store data to the database
			 */
			public void actionPerformed(ActionEvent arg0) {
				Backend.dataStorage.clearDataBase(MainMenu.this);
			}
		});
		btnClearDatabase.setForeground(Color.WHITE);
		btnClearDatabase.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnClearDatabase.setBackground(Color.RED);
		
		JButton btnLoadHardcodedData = new JButton("Load Hardcoded Data");
		btnLoadHardcodedData.addActionListener(new ActionListener() {
			//This is just a backup in case the DB data were lost, you can use the data population class of assginment 2
			public void actionPerformed(ActionEvent arg0) {
				DataPopulation.populateBackend(); //just for testing, you can use to populate if you lose the persistent file
				logMessage("Hardcoded Data was Loaded");
			}
		});
		btnLoadHardcodedData.setForeground(Color.WHITE);
		btnLoadHardcodedData.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnLoadHardcodedData.setBackground(new Color(255, 69, 0));
		
		JButton btnWipeCurrentApp = new JButton("Wipe Current App Data");
		btnWipeCurrentApp.addActionListener(new ActionListener() {
			/**
			 * Clears all the current data in the program
			 */
			public void actionPerformed(ActionEvent e) {
				Backend.users.clear();
				Backend.artists.clear();
				Backend.albums.clear();
				Backend.songs.clear();
				Backend.playlists.clear();
				logMessage("Current App Data was Wiped");
			}
		});
		btnWipeCurrentApp.setForeground(Color.WHITE);
		btnWipeCurrentApp.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnWipeCurrentApp.setBackground(Color.RED);
		
		JButton btnSerializeMd = new JButton("Serialize + generate MD5");
		btnSerializeMd.addActionListener(new ActionListener() {
			/**
			 * Serialize the users and generate MD5 in a separate file
			 */
			public void actionPerformed(ActionEvent e) {
				SerializationManager.serializeUsers();
				DigestManager.generateAndStoreMD5();
			}
		});
		btnSerializeMd.setForeground(Color.WHITE);
		btnSerializeMd.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnSerializeMd.setBackground(new Color(255, 69, 0));
		
		JButton btnDeserializeCheck = new JButton("Deserialize + check MD5");
		btnDeserializeCheck.addActionListener(new ActionListener() {
			//deserialize the users and check the MD5 file against a newly generated MD5
			public void actionPerformed(ActionEvent e) {
				SerializationManager.deserilaizeAndCheckMD5();
			}
		});
		btnDeserializeCheck.setForeground(Color.WHITE);
		btnDeserializeCheck.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnDeserializeCheck.setBackground(new Color(255, 69, 0));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addComponent(announcement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(58, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblOutput)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClear))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnClearDatabase, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnLoadMusicplayerData, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(btnSerializeMd, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))))
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnWipeCurrentApp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnLoadHardcodedData, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnSaveData, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnDeserializeCheck, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
					.addGap(31))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(announcement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoadMusicplayerData)
						.addComponent(btnSaveData))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClearDatabase)
						.addComponent(btnLoadHardcodedData))
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSerializeMd)
						.addComponent(btnDeserializeCheck))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOutput)
						.addComponent(btnClear)
						.addComponent(btnWipeCurrentApp))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
		);
		
		outputPanel = new JTextPane();
		outputPanel.setEditable(false);
		outputPanel.setBackground(Color.BLACK);
		outputPanel.setForeground(Color.WHITE);
		outputPanel.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		outputPanel.setText("*Dialog history will be displayed here*\n");
		scrollPane.setViewportView(outputPanel);
		frame.getContentPane().setLayout(groupLayout);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outputPanel.setText("");
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setBackground(new Color(255, 69, 0));
		frame.setJMenuBar(menuBar);
		
		JMenu mnMainMenu = new JMenu("Main Menu");
		mnMainMenu.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.setBackground(new Color(255, 69, 0));
		mnMainMenu.setForeground(Color.WHITE);
		menuBar.add(mnMainMenu);
		
		JMenuItem mntmAddUser = new JMenuItem("Add User");
		mntmAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUser addUser = new AddUser(MainMenu.this);
				addUser.pack();
				addUser.setVisible(true);
			}
		});
		mntmAddUser.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mntmAddUser.setForeground(Color.WHITE);
		mntmAddUser.setBackground(new Color(255, 69, 0));
		mnMainMenu.add(mntmAddUser);
		
		JMenuItem mntmDeleteUserBy = new JMenuItem("Delete User");
		mntmDeleteUserBy.setForeground(Color.WHITE);
		mntmDeleteUserBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteUser deleteUser = new DeleteUser(MainMenu.this);
				deleteUser.pack();
				deleteUser.setVisible(true);
			}
		});
		mntmDeleteUserBy.setBackground(new Color(255, 69, 0));
		mntmDeleteUserBy.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmDeleteUserBy);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("View User Details");
		mntmNewMenuItem.setForeground(Color.WHITE);
		mntmNewMenuItem.setBackground(new Color(255, 69, 0));
		mntmNewMenuItem.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewUserDetails viewUserDetails = new ViewUserDetails(MainMenu.this);
				viewUserDetails.pack();
				viewUserDetails.setVisible(true);
						
			}
		});
		mnMainMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmAddSong = new JMenuItem("Add Song");
		mntmAddSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSong addSong = new AddSong(MainMenu.this);
				addSong.pack();
				addSong.setVisible(true);
			}
		});
		mntmAddSong.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mntmAddSong.setForeground(Color.WHITE);
		mntmAddSong.setBackground(new Color(255, 69, 0));
		mnMainMenu.add(mntmAddSong);
		
		JMenuItem mntmDeleteSong = new JMenuItem("Delete Song");
		mntmDeleteSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteSong deleteSong = new DeleteSong(MainMenu.this);
				deleteSong.pack();
				deleteSong.setVisible(true);
			}
		});
		mntmDeleteSong.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mntmDeleteSong.setBackground(new Color(255, 69, 0));
		mntmDeleteSong.setForeground(Color.WHITE);
		mnMainMenu.add(mntmDeleteSong);
		
		JMenuItem mntmGetSongAlbum = new JMenuItem("Get Song Album");
		mntmGetSongAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetSongAlbum getSongAlbum = new GetSongAlbum(MainMenu.this);
				getSongAlbum.pack();
				getSongAlbum.setVisible(true);
			}
		});
		mntmGetSongAlbum.setBackground(new Color(255, 69, 0));
		mntmGetSongAlbum.setForeground(Color.WHITE);
		mntmGetSongAlbum.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmGetSongAlbum);
		
		JMenuItem mntmAddPlaylist = new JMenuItem("Add Playlist");
		mntmAddPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPlaylist addPlaylist = new AddPlaylist(MainMenu.this);
				addPlaylist.pack();
				addPlaylist.setVisible(true);
			}
		});
		mntmAddPlaylist.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mntmAddPlaylist.setForeground(Color.WHITE);
		mntmAddPlaylist.setBackground(new Color(255, 69, 0));
		mnMainMenu.add(mntmAddPlaylist);
		
		JMenuItem mntmAddSongTo = new JMenuItem("Add Song to Playlist");
		mntmAddSongTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSongToPlaylist addSongToPlaylist = new AddSongToPlaylist(MainMenu.this);
				addSongToPlaylist.pack();
				addSongToPlaylist.setVisible(true);
			}
		});
		mntmAddSongTo.setForeground(Color.WHITE);
		mntmAddSongTo.setBackground(new Color(255, 69, 0));
		mntmAddSongTo.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmAddSongTo);
		
		JMenuItem mntmGetPlaylistDetails = new JMenuItem("Get Playlist Details");
		mntmGetPlaylistDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetPlaylistDetails getPlaylistDetails = new GetPlaylistDetails(MainMenu.this);
				getPlaylistDetails.pack();
				getPlaylistDetails.setVisible(true);
			}
		});
		mntmGetPlaylistDetails.setBackground(new Color(255, 69, 0));
		mntmGetPlaylistDetails.setForeground(Color.WHITE);
		mntmGetPlaylistDetails.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmGetPlaylistDetails);
		
		JMenuItem mntmDeletePlaylistFor = new JMenuItem("Delete Playlist for User");
		mntmDeletePlaylistFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeletePlaylist deletePlaylist = new DeletePlaylist(MainMenu.this);
				deletePlaylist.pack();
				deletePlaylist.setVisible(true);
			}
		});
		mntmDeletePlaylistFor.setForeground(Color.WHITE);
		mntmDeletePlaylistFor.setBackground(new Color(255, 69, 0));
		mntmDeletePlaylistFor.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmDeletePlaylistFor);
		
		JMenuItem mntmDisplayAllSongs = new JMenuItem("Display All Songs");
		mntmDisplayAllSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnTitles = new String[] {"ID", "Name", "Genre", "Release Date", "Album", "Artist"};
				String[][] tableContent = new String[Backend.songs.size()][6];
				for(int i = 0; i<Backend.songs.size(); i++)
				{
					Song temp = Backend.songs.get(i);
					tableContent[i] = new String[] {Integer.toString(temp.getId()), temp.getName(), temp.getGenre(), temp.getReleaseDate().toString(), temp.getAlbums().get(0).getName(), temp.getArtist().getName()};
				}
				outputTable oTable = new outputTable("All Songs Details", columnTitles, tableContent);
				oTable.pack();
				oTable.setVisible(true);
			}
		});
		mntmDisplayAllSongs.setForeground(Color.WHITE);
		mntmDisplayAllSongs.setBackground(new Color(255, 69, 0));
		mntmDisplayAllSongs.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmDisplayAllSongs);
		
		JMenuItem mntmDisplayAllUsers = new JMenuItem("Display All Users");
		mntmDisplayAllUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnTitles = new String[] {"Real Name", "DOB", "Username", "Subscription Type", "Subscription Expiry"};
				String[][] tableContent = new String[Backend.users.size()][5];
				for(int i = 0; i<Backend.users.size(); i++)
				{
					User temp = Backend.users.get(i);
					tableContent[i] = new String[] {temp.getName(), temp.getDOB().toString(), temp.getUserName(), temp.getSubscription().getSubType(), temp.getSubscription().getExpiryDate().toString()};
				}
				outputTable oTable = new outputTable("All Users Details", columnTitles, tableContent);
				oTable.pack();
				oTable.setVisible(true);
			}
		});
		mntmDisplayAllUsers.setForeground(Color.WHITE);
		mntmDisplayAllUsers.setBackground(new Color(255, 69, 0));
		mntmDisplayAllUsers.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmDisplayAllUsers);
		
		JMenuItem mntmAddArtist = new JMenuItem("Add Artist");
		mntmAddArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddArtist addArtist = new AddArtist(MainMenu.this);
				addArtist.pack();
				addArtist.setVisible(true);
			}
		});
		mntmAddArtist.setForeground(Color.WHITE);
		mntmAddArtist.setBackground(new Color(255, 69, 0));
		mntmAddArtist.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmAddArtist);
		
		JMenuItem mntmDeleteArtist = new JMenuItem("Delete Artist");
		mntmDeleteArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteArtist deleteArtist = new DeleteArtist(MainMenu.this);
				deleteArtist.pack();
				deleteArtist.setVisible(true);
			}
		});
		mntmDeleteArtist.setForeground(Color.WHITE);
		mntmDeleteArtist.setBackground(new Color(255, 69, 0));
		mntmDeleteArtist.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmDeleteArtist);
		
		JMenuItem mntmFollowPerson = new JMenuItem("Follow Person");
		mntmFollowPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FollowPerson followPerson = new FollowPerson(MainMenu.this);
				followPerson.pack();
				followPerson.setVisible(true);
			}
		});
		mntmFollowPerson.setForeground(Color.WHITE);
		mntmFollowPerson.setBackground(new Color(255, 69, 0));
		mntmFollowPerson.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmFollowPerson);
		
		JMenuItem mntmListFollowers = new JMenuItem("List Followers");
		mntmListFollowers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListFollowers listFollowers = new ListFollowers(MainMenu.this);
				listFollowers.pack();
				listFollowers.setVisible(true);
			}
		});
		mntmListFollowers.setForeground(Color.WHITE);
		mntmListFollowers.setBackground(new Color(255, 69, 0));
		mntmListFollowers.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnMainMenu.add(mntmListFollowers);
	}
	
	public static void logMessage(String message)
	{
		outputPanel.setText(outputPanel.getText() + "-> " + message + "\n");
	}
}

