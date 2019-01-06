package componentsV2;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for playlists
 */
public class Playlist implements Serializable{

	private static int nextID = 0;
	private int id;
	private String name;
	private User owner;
	private LocalDate dateCreated;
	private ArrayList<Song> songs;
	
	/**
	 * Constructor for playlists.
	 * auto ID.
	 * @param name
	 * @param owner
	 * @param date
	 * @param songs
	 */
	public Playlist(String name, User owner, LocalDate date, ArrayList<Song> songs) {
		this.id = nextID++;
		this.name = name;
		this.owner = owner;
		this.dateCreated = date;
		this.songs = songs;
	}
	
	/**
	 * auto ID.
	 * auto date.
	 * @param name
	 * @param owner
	 * @param songs
	 */
	public Playlist(String name, User owner, ArrayList<Song> songs) {
		this.id = nextID++;
		this.name = name;
		this.owner = owner;
		this.dateCreated = LocalDate.now();
		this.songs = songs;
	}
	
	/**
	 * Full fields constructor
	 * @param id
	 * @param name
	 * @param owner
	 * @param dateCreated
	 * @param songs
	 */
	public Playlist(int id, String name, User owner, LocalDate dateCreated, ArrayList<Song> songs) {
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.dateCreated = dateCreated;
		this.songs = songs;
	}

	/**
	 * Getters and Setters
	 */
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public User getOwner() {
		return owner;
	}



	public void setOwner(User owner) {
		this.owner = owner;
	}



	public LocalDate getDateCreated() {
		return dateCreated;
	}



	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}



	public ArrayList<Song> getSongs() {
		return songs;
	}



	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}



	/**
	 * Adds the given song to the playlist if it wasn't already.
	 * @param s
	 */
	public void addSong(Song s)
	{
		if(!this.songs.contains(s))
			this.songs.add(s);
	}
	
	/**
	 * Removes the given song from the playlist.
	 * @param s
	 */
	public void removeSong(Song s)
	{
		this.songs.remove(s);
	}
	
	/**
	 * Prints brief details about the playlist.
	 */
	public void printBrief()
	{
		System.out.println("ID: " + this.id + ", name: " + this.name);
	}
	
	/**
	 * Prints the playlist's details.
	 */
	public void printDetailed()
	{
		System.out.println("Playlist ID: " + this.id);
		System.out.println("Playlist Name: " + this.name);
		System.out.println("Playlist Owner: ");
		System.out.print("\t");
		this.owner.printBrief();
		System.out.println("Playlist Creation Date: " + this.dateCreated);
		System.out.println("Songs in the playlist: ");
		for(Song s: this.songs)
		{
			System.out.print("\t");
			s.printBrief();
		}
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id + ", name: " + this.name;
	}
}
