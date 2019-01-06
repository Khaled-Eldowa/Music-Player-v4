package componentsV2;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for songs.
 */
public class Song implements Serializable{
	private static int nextID = 0;
	private int id;
	private String name;
	private String genre;
	private LocalDate releaseDate;
	private ArrayList<Album> albums;
	private Artist artist;
	
	/**
	 * Constructor for songs.
	 * auto ID.
	 * @param name
	 * @param genre
	 * @param date
	 * @param album
	 * @param artist
	 */
	public Song(String name, String genre, LocalDate date, ArrayList<Album> albums, Artist artist) {
		this.id = nextID++;
		this.name = name;
		this.genre = genre;
		this.releaseDate = date;
		this.albums = albums;
		this.artist = artist;
	}
	
	
	
	/**
	 * auto genre
	 * @param id
	 * @param name
	 * @param releaseDate
	 * @param albums
	 * @param artist
	 */
	public Song(int id, String name, LocalDate releaseDate, ArrayList<Album> albums, Artist artist) {
		this.id = id;
		this.name = name;
		this.genre = artist.getTypeOfMusic();
		this.releaseDate = releaseDate;
		this.albums = albums;
		this.artist = artist;
	}




	/**
	 * Full fields constructor
	 * @param id
	 * @param name
	 * @param genre
	 * @param releaseDate
	 * @param albums
	 * @param artist
	 */
	public Song(int id, String name, String genre, LocalDate releaseDate, ArrayList<Album> albums, Artist artist) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.albums = albums;
		this.artist = artist;
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



	public ArrayList<Album> getAlbums() {
		return albums;
	}



	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}



	public Artist getArtist() {
		return artist;
	}



	public void setArtist(Artist artist) {
		this.artist = artist;
	}



	/**
	 * Prints brief info about the song.
	 */
	public void printBrief()
	{
		System.out.println("ID: " + this.id + ", name: " + this.name);
	}
	
	/**
	 * prints the song details.
	 */
	public void printDetailed()
	{
		System.out.println("Song ID: " + this.id);
		System.out.println("Song Name: " + this.name);
		System.out.println("Song Genre: " + this.genre);
		System.out.println("Song Release Date: " + this.releaseDate);
		
		System.out.println("Albums to which this song belongs: ");
		for(Album a:this.albums)
		{
			System.out.print("\t");
			a.printBrief();
		}
	
		System.out.println("Song Artist: ");
		System.out.print("\t");
		this.artist.printBrief();
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id + ", name: " + this.name;
	}
}
