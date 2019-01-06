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

import app.Backend;
import app.Message;
import componentsV2.Album;
import componentsV2.Playlist;
import componentsV2.Song;
import componentsV2.User;
import exceptions.AlbumNotFoundException;
import exceptions.EmptyFieldException;
import mainApp.MainMenu;

public class AddSong extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField albumIDField;
	private JTextField genreField;
	private JLabel lblD;
	private JComboBox<Integer> dayscomboBox;
	private JLabel lblM;
	private JComboBox<Integer> monthsComboBox;
	private JLabel lblY;
	private JComboBox<Integer> yearsComboBox;
	private JPanel panel;
	private JButton btnAddUser;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSong frame = new AddSong();
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
	public AddSong(MainMenu mainMenu) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Add a New Song");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		albumIDField = new JTextField();
		albumIDField.setColumns(10);
		
		genreField = new JTextField();
		genreField.setColumns(10);
		
		JLabel lblRealName = new JLabel("Song Name:");
		
		JLabel lblDob = new JLabel("Release Date:");
		
		JLabel lblUsername = new JLabel("Album ID:");
		
		JLabel lblEmail = new JLabel("Song Genre:");
		
		lblD = new JLabel("d");
		
		dayscomboBox = new JComboBox(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31});
		
		lblM = new JLabel("m");
		
		monthsComboBox = new JComboBox(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
		
		lblY = new JLabel("y");
		
		yearsComboBox  = new JComboBox(new Integer[] {2018, 2017, 2016, 2015, 2014, 2013, 2012, 2011, 2010, 2009, 2008, 2007, 2006, 2005, 2004, 2003, 2002, 2001, 2000, 1999, 1998, 1997, 1996, 1995, 1994, 1993, 1992, 1991, 1990, 1989, 1988, 1987, 1986, 1985, 1984, 1983, 1982, 1981, 1980, 1979, 1978, 1977, 1976, 1975, 1974, 1973, 1972, 1971, 1970, 1969, 1968, 1967, 1966, 1965, 1964, 1963, 1962, 1961, 1960, 1959, 1958, 1957, 1956, 1955, 1954, 1953, 1952, 1951, 1950, 1949, 1948, 1947, 1946, 1945, 1944, 1943, 1942, 1941, 1940, 1939, 1938, 1937, 1936, 1935, 1934, 1933, 1932, 1931, 1930, 1929, 1928, 1927, 1926, 1925, 1924, 1923, 1922, 1921, 1920, 1919, 1918, 1917, 1916, 1915, 1914, 1913, 1912, 1911, 1910, 1909, 1908, 1907, 1906, 1905, 1904, 1903, 1902, 1901, 1900});
		
		
		panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(59)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblRealName)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDob, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
								.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(71)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(albumIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(genreField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(lblD, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dayscomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(16)
									.addComponent(lblM, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(monthsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(16)
									.addComponent(lblY, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(yearsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblD)
								.addComponent(dayscomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(monthsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblM)
								.addComponent(yearsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblY)
								.addComponent(lblDob))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(albumIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(genreField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail)))
						.addComponent(lblRealName))
					.addGap(31)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		btnAddUser = new JButton("Add Song");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Message messageBox;
				try {
					String temp;
					temp = nameField.getText();
					if(temp.equals(""))
						throw new EmptyFieldException("Song Name");
					LocalDate date = LocalDate.of((int) yearsComboBox.getSelectedIndex(),(int) monthsComboBox.getSelectedItem(),(int) dayscomboBox.getSelectedItem());
					int id = Integer.parseInt(albumIDField.getText());
					Album mainAlbum = Backend.getAlbum(id);
					if(mainAlbum==null)
						throw new AlbumNotFoundException(id);
					ArrayList<Album> albums = new ArrayList<>();
					albums.add(mainAlbum);
					temp = genreField.getText();
					if(temp.equals(""))
						throw new EmptyFieldException("Email");
					Song newSong = Backend.addSong(nameField.getText(), genreField.getText(), date, albums, mainAlbum.getArtist());
					messageBox = new Message("Song was created successfully with id " + newSong.getId(), mainMenu, AddSong.this);
					messageBox.setTitle("Success");
				}
				catch (EmptyFieldException e) {
					messageBox = new Message(e.getMessage() + "\nThe song was not added!", mainMenu, null);
					messageBox.setTitle("Error");
				}
				catch (DateTimeException e) {
					messageBox = new Message("The date entered is invalid\nThe song was not added!", mainMenu, null);
					messageBox.setTitle("Error");
				}
				catch (NumberFormatException e1) {
					messageBox = new Message("Invalid Album ID format", mainMenu, null);
					messageBox.setTitle("Error");
				}
				catch (AlbumNotFoundException e) {
					messageBox = new Message(e.getMessage(), mainMenu, null);
					messageBox.setTitle("Error");
				}
				messageBox.pack();
				messageBox.setVisible(true);
			}
		});
		panel.add(btnAddUser);
		contentPane.setLayout(gl_contentPane);
	}

}
