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
import componentsV2.Song;
import componentsV2.User;
import exceptions.EmptyFieldException;
import exceptions.SongNotFoundException;
import exceptions.UserNotFoundException;
import mainApp.MainMenu;

public class DeleteSong extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteSong frame = new DeleteSong();
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
	public DeleteSong(MainMenu mainMenu) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(75, 75, 300, 200);
		setTitle("Delete Song");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblEntereTheUsers = new JLabel("Entere the song's name:");
		
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
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message messageBox;
				try {
					String name = textField.getText();
					if(name.equals(""))
						throw new EmptyFieldException("Song Name");
					Song temp = Backend.deleteSong(name);
					if(temp==null)
						throw new SongNotFoundException(name);
					messageBox = new Message("The Song with name = "+ temp.getName() + " was deleted successfully", mainMenu, DeleteSong.this);
					messageBox.setTitle("Success");
				}
				catch (EmptyFieldException e1) {
					messageBox = new Message(e1.getMessage() + "\nNo song was deleted!", mainMenu, null);
					messageBox.setTitle("Error");
				}
				catch (SongNotFoundException e2) {
					messageBox = new Message(e2.getMessage() + "\nNo song was deleted!", mainMenu, null);
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
