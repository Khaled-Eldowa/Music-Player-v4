package componentsV2;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for users
 * 
 *
 */
public class User extends Person { //extends Person which implements serializable!
	private String userName;
	private ArrayList<Album> listOfAlbums;
	private ArrayList<Playlist> listOfPlaylists;
	private Subscription subscription;
	
	/**
	 * constructor with public subscription.
	 * @param name
	 * @param DOB
	 * @param userName
	 * @param listOfAlbums
	 * @param listOfPlaylists
	 * @param email
	 */
	public User(String name, LocalDate DOB, String userName, ArrayList<Album> listOfAlbums,
			ArrayList<Playlist> listOfPlaylists, String email) {
		super(name, DOB);
		this.userName = userName;
		this.listOfAlbums = listOfAlbums;
		this.listOfPlaylists = listOfPlaylists;
		this.subscription = new PublicSubscription(email);
	}
	
	/**
	 * constructor with passed subscription.
	 * @param name
	 * @param DOB
	 * @param userName
	 * @param listOfAlbums
	 * @param listOfPlaylists
	 * @param subscription
	 */
	public User(String name, LocalDate DOB, String userName, ArrayList<Album> listOfAlbums,
			ArrayList<Playlist> listOfPlaylists, Subscription subscription) {
		super(name, DOB);
		this.userName = userName;
		this.listOfAlbums = listOfAlbums;
		this.listOfPlaylists = listOfPlaylists;
		this.subscription = subscription;
	}
	
	
	/**
	 * constructor for all fields.
	 * @param id
	 * @param name
	 * @param dOB
	 * @param listOfFollowers
	 * @param listOfFollowings
	 * @param userName
	 * @param listOfAlbums
	 * @param listOfPlaylists
	 * @param subscription
	 */
	public User(int id, String name, LocalDate dOB, ArrayList<Person> listOfFollowers,
			ArrayList<Person> listOfFollowings, String userName, ArrayList<Album> listOfAlbums,
			ArrayList<Playlist> listOfPlaylists, Subscription subscription) {
		super(id, name, dOB, listOfFollowers, listOfFollowings);
		this.userName = userName;
		this.listOfAlbums = listOfAlbums;
		this.listOfPlaylists = listOfPlaylists;
		this.subscription = subscription;
	}

	/**
	 * Getters and Setters
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<Album> getListOfAlbums() {
		return listOfAlbums;
	}

	public void setListOfAlbums(ArrayList<Album> listOfAlbums) {
		this.listOfAlbums = listOfAlbums;
	}

	public ArrayList<Playlist> getListOfPlaylists() {
		return listOfPlaylists;
	}

	public void setListOfPlaylists(ArrayList<Playlist> listOfPlaylists) {
		this.listOfPlaylists = listOfPlaylists;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	/**
	 * Adds the given playlist to the user.
	 * @param p
	 */
	public void addPlaylist(Playlist p)
	{
		if(!this.listOfPlaylists.contains(p))
			this.listOfPlaylists.add(p);
	}
	
	/**
	 * Removes the given playlist from the user.
	 * @param p
	 */
	public void removePlaylist(Playlist p)
	{
		this.listOfPlaylists.remove(p);
	}
	
	public void printDetailed()
	{
		super.printPersonDetailed();
		System.out.println("Username: " + this.userName);
		System.out.println("List of albums: ");
		for(Album a: this.listOfAlbums)
		{
			System.out.print("\t");
			a.printBrief();
		}
		System.out.println("List of playlists: ");
		for(Playlist p: this.listOfPlaylists)
		{
			System.out.print("\t");
			p.printBrief();
		}
		System.out.println(this.subscription.toString());
	}
	/**
	 * Tostring implemented in parent
	 */
}
