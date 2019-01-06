package componentsV2;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for artists.
 */
public class Artist extends Person{ //extends Person which implements serializable

	private String typeOfMusic;
	private ArrayList<String> awards;
	private ArrayList<Album> albums;
	private ArrayList<Song> songs;
	
	/**
	 * Practical constructor
	 * @param name
	 * @param DOB
	 * @param typeOfMusic
	 * @param awards
	 */
	public Artist(String name, LocalDate DOB, String typeOfMusic, ArrayList<String> awards) {
		super(name, DOB);
		this.typeOfMusic = typeOfMusic;
		this.awards = awards;
		this.albums = new ArrayList<>(); //No albums should be passed to the constructor, as albums already created will be another artist's
		this.songs = new ArrayList<>(); //No songs should be passed to the constructor, as songs already created will be another artist's
	}
	
	/**
	 * Full child fields constructor
	 * @param name
	 * @param DOB
	 * @param typeOfMusic
	 * @param awards
	 * @param albums
	 * @param songs
	 */
	public Artist(String name, LocalDate DOB, String typeOfMusic, ArrayList<String> awards, ArrayList<Album> albums,
			ArrayList<Song> songs) {
		super(name, DOB);
		this.typeOfMusic = typeOfMusic;
		this.awards = awards;
		this.albums = albums;
		this.songs = songs;
	}

	/**
	 * Full child/super fileds constructor
	 * @param id
	 * @param name
	 * @param dOB
	 * @param listOfFollowers
	 * @param listOfFollowings
	 * @param typeOfMusic
	 * @param awards
	 * @param albums
	 * @param songs
	 */
	public Artist(int id, String name, LocalDate dOB, ArrayList<Person> listOfFollowers,
			ArrayList<Person> listOfFollowings, String typeOfMusic, ArrayList<String> awards, ArrayList<Album> albums,
			ArrayList<Song> songs) {
		super(id, name, dOB, listOfFollowers, listOfFollowings);
		this.typeOfMusic = typeOfMusic;
		this.awards = awards;
		this.albums = albums;
		this.songs = songs;
	}


	/**
	 * Getters and Setters
	 */
	public String getTypeOfMusic() {
		return typeOfMusic;
	}
	public void setTypeOfMusic(String typeOfMusic) {
		this.typeOfMusic = typeOfMusic;
	}
	public ArrayList<String> getAwards() {
		return awards;
	}
	public void setAwards(ArrayList<String> awards) {
		this.awards = awards;
	}
	public ArrayList<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}
	public ArrayList<Song> getSongs() {
		return songs;
	}
	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}
	
	/**
	 * Adds the given album to the artist if it was not already.
	 * @param album
	 */
	public void addAlbum(Album album)
	{
		if(!this.albums.contains(album))
			this.albums.add(album);
	}
	
	/**
	 * Removes the given album from the artist.
	 * @param album
	 */
	public void removeAlbum(Album album)
	{
		this.albums.remove(album);
	}
	
	/**
	 * Adds the given song to the artist if it was not already.
	 * @param song
	 */
	public void addSong(Song song)
	{
		if(!this.songs.contains(song))
			this.songs.add(song);
	}
	
	/**
	 * Removes the given song from the artist.
	 * @param song
	 */
	public void removeSong(Song song)
	{
		this.songs.remove(song);
	}
	public void printDetailed()
	{
		this.printPersonDetailed();
		System.out.println("Type of music: " + this.typeOfMusic);
		System.out.println("List of awards: ");
		for(String s: this.awards)
		{
			System.out.print("\t");
			System.out.println(s);
		}
		System.out.println("List of albums: ");
		for(Album a: this.albums)
		{
			System.out.print("\t");
			a.printBrief();
		}
		System.out.println("List of songs: ");
		for(Song s: this.songs)
		{
			System.out.print("\t");
			s.printBrief();
		}
	}
	
	/**
	 * Tostring is implemented in parent
	 */
}
