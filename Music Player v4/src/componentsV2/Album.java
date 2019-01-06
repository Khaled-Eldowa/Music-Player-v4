package componentsV2;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for albums
 */
public class Album implements Serializable{
	private static int nextID = 0;
	private int id;
	private String name;
	private String genre;
	private LocalDate releaseDate;
	private Artist artist;
	private ArrayList<Song> songs;
	
	/**
	 * Constructor for the album. We do not add songs to the album at this stage. We add the songs after creating their album.
	 * auto ID.
	 * @param name
	 * @param genre
	 * @param releaseDate
	 * @param artist
	 */
	public Album(String name, String genre, LocalDate date, Artist artist) {
		this.id = nextID++;
		this.name = name;
		this.genre = genre;
		this.releaseDate = date;
		this.artist = artist;
		this.songs = new ArrayList<>(); //No songs passed at creation
	}
	
	/**
	 * Constructor with songs passed at creation.
	 * auto ID.
	 * @param name
	 * @param genre
	 * @param date
	 * @param artist
	 * @param songs
	 */
	public Album(String name, String genre, LocalDate date, Artist artist, ArrayList<Song> songs) {
		this.id = nextID++;
		this.name = name;
		this.genre = genre;
		this.releaseDate = date;
		this.artist = artist;
		this.songs = songs;
		for(Song song:songs)
			song.getAlbums().add(this);
	}

	/**
	 * Full fields constructor
	 * @param id
	 * @param name
	 * @param genre
	 * @param releaseDate
	 * @param artist
	 * @param songs
	 */
	public Album(int id, String name, String genre, LocalDate releaseDate, Artist artist, ArrayList<Song> songs) {
		super();
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.artist = artist;
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



	public String getGenre() {
		return genre;
	}



	public void setGenre(String genre) {
		this.genre = genre;
	}



	public LocalDate getReleaseDate() {
		return releaseDate;
	}



	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}



	public Artist getArtist() {
		return artist;
	}



	public void setArtist(Artist artist) {
		this.artist = artist;
	}



	public ArrayList<Song> getSongs() {
		return songs;
	}



	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}



	/**
	 * Adds the given song to the album if it was not added already.
	 * @param song
	 */
	public void addSong(Song song)
	{
		if(!this.songs.contains(song))
			this.songs.add(song);
	}
	
	/**
	 * Removes the given song from the album.
	 * @param song
	 */
	public void removeSong(Song song)
	{
		this.songs.remove(song);
	}
	
	/**
	 * Prints brief details about the album.
	 */
	public void printBrief()
	{
		System.out.println("ID: " + this.id + ", name: " + this.name);
	}
	
	/**
	 * Prints all the album's details.
	 */
	public void printDetailed()
	{
		System.out.println("Album ID: " + this.id);
		System.out.println("Album Name: " + this.name);
		System.out.println("Album Genre: " + this.genre);
		System.out.println("Album Release Date: " + this.releaseDate);
		System.out.println("Album Artist: ");
		System.out.print("\t");
		this.artist.printBrief();
		System.out.println("Album Songs: ");
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
