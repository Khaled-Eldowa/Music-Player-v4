package functionsGUIs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Backend;
import app.Message;
import componentsV2.Album;
import componentsV2.Playlist;
import componentsV2.User;
import exceptions.EmptyFieldException;
import mainApp.MainMenu;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AddUser extends JFrame {

	private JPanel contentPane;
	private JTextField realNameField;
	private JTextField userNameField;
	private JTextField emailField;
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
					AddUser frame = new AddUser();
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
	public AddUser(MainMenu mainMenu) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Add a New User");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		realNameField = new JTextField();
		realNameField.setColumns(10);
		
		userNameField = new JTextField();
		userNameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		
		JLabel lblRealName = new JLabel("Real Name:");
		
		JLabel lblDob = new JLabel("DOB:\r\n");
		
		JLabel lblUsername = new JLabel("Username:");
		
		JLabel lblEmail = new JLabel("email:");
		
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRealName)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDob, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(71)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(realNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
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
							.addComponent(realNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
								.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail)))
						.addComponent(lblRealName))
					.addGap(31)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Message messageBox;
				try {
					String temp;
					temp = realNameField.getText();
					if(temp.equals(""))
						throw new EmptyFieldException("Real Name");
					LocalDate date = LocalDate.of((int) yearsComboBox.getSelectedItem(),(int) monthsComboBox.getSelectedItem(),(int) dayscomboBox.getSelectedItem());
					temp = userNameField.getText();
					if(temp.equals(""))
						throw new EmptyFieldException("User Name");
					temp = emailField.getText();
					if(temp.equals(""))
						throw new EmptyFieldException("Email");
					User newUser = Backend.addUser(realNameField.getText(), date, userNameField.getText(), new ArrayList<Album>(), new ArrayList<Playlist>(), emailField.getText());
					messageBox = new Message("User was created successfully with id " + newUser.getId(), mainMenu, AddUser.this);
					messageBox.setTitle("Success");
				}
				catch (EmptyFieldException e) {
					messageBox = new Message(e.getMessage() + "\nThe user was not added!", mainMenu, null);
					messageBox.setTitle("Error");
				}
				catch (DateTimeException e) {
					messageBox = new Message("The date entered is invalid\nThe user was not added!", mainMenu, null);
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
