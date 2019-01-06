package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import componentsV2.*;
import database.DataStorage;

public class Backend {
	public static Scanner in = new Scanner(System.in);
	public static ArrayList<Song> songs = new ArrayList<>();
	public static ArrayList<User> users = new ArrayList<>();
	public static ArrayList<Playlist> playlists = new ArrayList<>();
	public static ArrayList<Artist> artists = new ArrayList<>();
	public static ArrayList<Album> albums = new ArrayList<>();
	public static DataStorage dataStorage = new DataStorage(); //the required addition to the UML Diagram


	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * prints the menu
	 * @return the option selected
	 * @throws InputMismatchException
	 */
	/*public static int menu() throws InputMismatchException
	{
		return Interaction.menu();
	}*/
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * Adds a new user to the system
	 * @param name
	 * @param albums list of albums
	 * @param playlists list of playlists
	 * @param followers list of followers
	 * @param Followings list of followings
	 * @return the newly created user object
	 */
	public static User addUser(String name, LocalDate DOB, String userName, ArrayList<Album> listOfAlbums,
			ArrayList<Playlist> listOfPlaylists, String email)
	{
		User tempUser = new User(name,DOB,userName,listOfAlbums,listOfPlaylists,email);
		users.add(tempUser);
		return tempUser;
	}
		
	/**
	 * Searches for a user using id
	 * @param id
	 * @return the user if found
	 */
	public static User getUser(int id)
	{
		for(User u: users)
		{
			if(u.getId() == id)
				return u;
		}
		return null;
	}
	
	/**
	 * Searches for a user using userName
	 * @param name
	 * @return the user if found
	 */
	public static User getUser(String name)
	{
		for(User u: users)
		{
			if(u.getUserName().equals(name))
				return u;
		}
		return null;
	}
	
	/**
	 * Deletes the user with the given id
	 * @param id
	 * @return the deleted user if found
	 */
	public static User deleteUser(int id)
	{
		User tempUser;
		for(int i=0; i<users.size(); i++)
		{
			if(users.get(i).getId() == id)
			{
				tempUser = users.get(i);
				deleteUserTraces(tempUser);
				users.remove(i);
				return tempUser;
			}
		}
		return null;
	}
	
	/**
	 * Deletes the user with the given userName
	 * @param name
	 * @return the deleted user if found
	 */
	public static User deleteUser(String name)
	{
		User tempUser;
		for(int i=0; i<users.size(); i++)
		{
			if(users.get(i).getUserName().equals(name))
			{
				tempUser = users.get(i);
				deleteUserTraces(tempUser);
				users.remove(i);
				return tempUser;
			}
		}
		return null;
	}
	
	/**
	 * Removes all references to the given user. Used before deletion.
	 * @param u the concerned user
	 */
	public static void deleteUserTraces(User u)
	{
		deletePlaylistsOwnedByUser(u.getId());
		RemovePersonFollowers(u);
		RemovePersonFollowings(u);
	}
	
	/**
	 * prints the details of the user with the given id.
	 * @param id
	 * @return a check to inform the calling function if the user was found or not.
	 */
	public static boolean viewUserDetails(int id)
	{
		User tempUser = getUser(id);
		if(tempUser == null)
			return false;
		else
		{
			tempUser.printDetailed();
			return true;
		}
	}
	
	/**
	 * Adds the given subscription object to the given user.
	 * @param user
	 * @param subscription
	 */
	public static void subscribeUser(User user, Subscription subscription)
	{
		/*THE DETAILS OF THE SUBSCRIPTION ARE GATHERED IN THE METHOD "subscribeUser_Interactive()" in the class 
		Interactive where this method is called*/
		user.setSubscription(subscription);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Finds a user using the given id.
	 * @param id
	 * @return the playlist if found.
	 */
	public static Playlist getPlaylist(int id)
	{
		for(Playlist p: playlists)
		{
			if(p.getId() == id)
				return p;
		}
		return null;
	}
	
	/**
	 * Deletes all playlists owned by the user with the given id.
	 * @param userId
	 */
	public static void deletePlaylistsOwnedByUser(int userId)
	{
		for(User u: users)
		{
			ArrayList<Playlist> p = u.getListOfPlaylists();
			for(int i=0; i<p.size(); i++)
			{
				if(p.get(i).getOwner().getId() == userId)
					p.remove(i);
			}
		}
		for(int i=0; i<playlists.size(); i++)
		{
			if(playlists.get(i).getOwner().getId() == userId)
				playlists.remove(i);
		}
	}
	
	/**
	 * Deletes the playlist with the given id for all users.
	 * @param playlistID
	 */
	public static void deletePlaylistForAllUser(int playlistID)
	{
		for(User u: users)
		{
			ArrayList<Playlist> p = u.getListOfPlaylists();
			for(int i=0; i<p.size(); i++)
			{
				if(p.get(i).getId() == playlistID)
					p.remove(i);
			}
		}
		for(int i=0; i<playlists.size(); i++)
		{
			if(playlists.get(i).getId() == playlistID)
				playlists.remove(i);
		}
	}
	
	/**
	 * Adds a new playlist to the system for a user
	 * @param userID
	 * @param playlistName
	 * @param playlistDate
	 * @param playlistSongs
	 * @return the new playlist if the user was found.
	 */
	public static Playlist addPlaylistbyUser(int userID, String playlistName, LocalDate playlistDate, ArrayList<Song> playlistSongs)
	{
		User tempUser = getUser(userID);
		if(tempUser == null)
			return null;
		else
			return addPlaylist(playlistName, tempUser, playlistDate, playlistSongs);
	}
	
	/**
	 * Adds a new playlist to the system.
	 * @param name
	 * @param owner
	 * @param date
	 * @param songs
	 * @return the created playlist.
	 */
	public static Playlist addPlaylist(String name, User owner, LocalDate date, ArrayList<Song> songs)
	{
		Playlist tempPlaylist = new Playlist(name, owner, date, songs);
		tempPlaylist.getOwner().addPlaylist(tempPlaylist);
		playlists.add(tempPlaylist);
		return tempPlaylist;
	}
	
	/**
	 * Adds to a user an already existing playlist
	 * @param p
	 * @param u
	 */
	public static void addExistingPlaylistToUser(Playlist p, User u)
	{
		u.addPlaylist(p);
	}
	
	/**
	 * Adds a song to a playlist.
	 * @param songID
	 * @param playlistID
	 * @return an int for error checking in the caller function.
	 */
	public static int addSongtoPlaylist(int songID, int playlistID)
	{
		int error = 0, size;
		Song tempSong = getSong(songID);
		Playlist tempPlaylist = getPlaylist(playlistID);
		if(tempSong == null)
			error++;
		if(tempPlaylist == null)
			error += 2;
		if(error == 0)
		{
			size = tempPlaylist.getSongs().size();
			tempPlaylist.addSong(tempSong);
			if(size == tempPlaylist.getSongs().size())
				error = -1; //just a warning that the song was already in the playlist
		}
		return error;
	}
	
	/**
	 * Prints the details of the playlist with the given id.
	 * @param id
	 * @return a boolean for check.
	 */
	public static boolean getPlaylistDetails(int id)
	{
		Playlist tempPlaylist = getPlaylist(id);
		if(tempPlaylist == null)
			return false;
		else
		{
			tempPlaylist.printDetailed();
			return true;
		}
	}
	
	/**
	 * Deletes a playlist for a user 
	 * @param userID
	 * @param playlistID
	 * @return an int for error checking with the caller function.
	 */
	public static int deletePlaylist(int userID, int playlistID)
	{
		int error = 0;
		User tempUser = getUser(userID);
		Playlist tempPlaylist = getPlaylist(playlistID);
		if(tempUser == null)
			error++;
		if(tempPlaylist == null)
			error += 2;
		if(error == 0)
		{
			if(!tempUser.getListOfPlaylists().contains(tempPlaylist))
			{
				error = -1;
			}
			else
			{
				if(tempPlaylist.getOwner().getId() == userID)
				{
					deletePlaylistForAllUser(playlistID);
					error = -2;
				}
				else
					tempUser.removePlaylist(tempPlaylist);
			}
		}
		return error;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Adds a new song to the system.
	 * @param name
	 * @param genre
	 * @param date
	 * @param album
	 * @param artist
	 * @return the newly created song object.
	 */
	public static Song addSong(String name, String genre, LocalDate date, ArrayList<Album> albums, Artist artist)
	{
		Song tempSong = new Song(name, genre, date, albums, artist);
		for(Album album : albums)
			album.addSong(tempSong);
		songs.add(tempSong);
		return tempSong;
	}
	
	/**
	 * Returns a song with the given id.
	 * @param id
	 * @return the sing if found.
	 */
	public static Song getSong(int id)
	{
		for(Song s: songs)
		{
			if(s.getId() == id)
				return s;
		}
		return null;
	}
	
	/**
	 * Deletes the song with the given id.
	 * @param id
	 * @return the deleted song if found. 
	 */
	public static Song deleteSong(int id)
	{
		Song tempSong;
		for(int i=0; i<songs.size(); i++)
		{
			if(songs.get(i).getId() == id)
			{
				tempSong = songs.get(i);
				for(Album album : tempSong.getAlbums())
					album.removeSong(tempSong);
				songs.remove(i);
				return tempSong;
			}
		}
		return null;
	}
	
	/**
	 * Deletes the song with the given name.
	 * @param name
	 * @return the deleted song if found.
	 */
	public static Song deleteSong(String name)
	{
		Song tempSong;
		for(int i=0; i<songs.size(); i++)
		{
			if(songs.get(i).getName().equals(name))
			{
				tempSong = songs.get(i);
				for(Album album : tempSong.getAlbums())
					album.removeSong(tempSong);
				songs.remove(i);
				return tempSong;
			}
		}
		return null;
	}
	
	/**
	 * Returns the albums of the song with the given id.
	 * @param songID
	 * @return the albums if found.
	 */
	public static ArrayList<Album> getSongAlbums(int songID)
	{
		Song tempSong = getSong(songID);
		if(tempSong==null)
			return null;
		else
			return tempSong.getAlbums();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Gets an album with the given id.
	 * @param id
	 * @return the album if found.
	 */
	public static Album getAlbum(int id)
	{
		for(Album a: albums)
		{
			if(a.getId() == id)
				return a;
		}
		return null;
	}
	
	/**
	 * Adds a new album to the system
	 * @param name
	 * @param genre
	 * @param date
	 * @param artist
	 * @return the newly created album object.
	 */
	public static Album addAlbum(String name, String genre, LocalDate date, Artist artist)
	{
		Album tempAlbum = new Album(name, genre, date, artist);
		tempAlbum.getArtist().addAlbum(tempAlbum);
		albums.add(tempAlbum);
		return tempAlbum;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Gets an artist with the given id.
	 * @param id
	 * @return the artist if found.
	 */
	public static Artist getArtist(int id)
	{
		for(Artist a: artists)
		{
			if(a.getId() == id)
				return a;
		}
		return null;
	}
	
	/**
	 * Adds an artist to the system
	 * @param name
	 * @param country
	 * @param typeOfMusic
	 * @return the newly created artist object.
	 */
	public static Artist addArtist(String name, LocalDate DOB, String typeOfMusic, ArrayList<String> awards)
	{
		Artist tempArtist = new Artist(name, DOB, typeOfMusic, awards);
		artists.add(tempArtist);
		return tempArtist;
	}
	
	public static Artist deleteArtist(String name)
	{
		Artist tempArtist;
		for(int i=0; i<artists.size(); i++)
		{
			if(artists.get(i).getName().equals(name))
			{
				tempArtist = artists.get(i);
				deleteArtistTraces(tempArtist);
				artists.remove(i);
				return tempArtist;
			}
		}
		return null;
	}
	
	/**
	 * Deletes all the traces of an artist including all their songs and albums
	 * @param artist
	 */
	public static void deleteArtistTraces(Artist artist)
	{
		//delete all their songs from all the playlists
		for(Playlist p:playlists)
		{
			p.setSongs((ArrayList<Song>)p.getSongs().stream()
				.filter(s -> s.getArtist().getId()!=artist.getId())
				.collect(Collectors.toList()));
		}
		
		//delete all their albums form all the users
		for(User u:users)
		{
			u.setListOfAlbums((ArrayList<Album>)u.getListOfAlbums().stream()
				.filter(a -> a.getArtist().getId()!=artist.getId())
				.collect(Collectors.toList()));
		}
		
		//delete all their songs from the MuiscPlayer
		Backend.songs = (ArrayList<Song>)Backend.songs.stream()
				.filter(s -> s.getArtist().getId()!=artist.getId())
				.collect(Collectors.toList());
		
		//delete all their albums from the MusicPlayer
		Backend.albums = (ArrayList<Album>)Backend.albums.stream()
				.filter(a -> a.getArtist().getId()!=artist.getId())
				.collect(Collectors.toList());
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * Forces all the given person's followers to unfollow it.
	 * @param person
	 */
	public static void RemovePersonFollowers(Person person)
	{
		if(person != null) {
			for(Person follower: person.getListOfFollowers())
				follower.unfollow(person);
		}
	}
	
	/**
	 * Removes the given person from the list of followers of all the persons that it followed.
	 * @param person
	 */
	public static void RemovePersonFollowings(Person person)
	{
		if(person != null) {
			for(Person following: person.getListOfFollowings())
				following.removeFollower(person);
		}
	}
	
	/**
	 * Make one person follow another one
	 * @param you
	 * @param toBeFollowed
	 */
	public static boolean followPerson(Person you, Person toBeFollowed)
	{
		int sizeBefore = you.getListOfFollowings().size();
		you.follow(toBeFollowed);
		toBeFollowed.followedBy(you);
		if(sizeBefore == you.getListOfFollowings().size())
			return false; //already followed
		else
			return true;
	}
	
	/**
	 * returns the person with the given id if found.
	 * @param id
	 * @return
	 */
	public static Person getPerson(int id)
	{
		User user = getUser(id);
		if(user!=null)
			return user;
		Artist artist = getArtist(id);
		return artist; //might be null	
	}
	
	public static void listFollowers(Person person) {
		if(person.getListOfFollowers().isEmpty())
			System.out.println("No followers.");
		else
		{
			for(Person follower:person.getListOfFollowers())
			{
				System.out.print("\t");
				follower.printBrief();
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Displays all users.
	 */
	public static void displayUsers()
	{
		System.out.println("All the users in the system: \n");
		for(User u:users)
		{
			u.printDetailed();
			System.out.println("***********");
		}	
	}
	
	/**
	 * Displays all songs.
	 */
	public static void displaySongs()
	{
		System.out.println("All the songs in the system: \n");
		for(Song s:songs)
		{
			s.printDetailed();
			System.out.println("***********");
		}	
	}
	
	/**
	 * Displays all artists.
	 */
	public static void displayArtists()
	{
		System.out.println("All the artists in the system: \n");
		for(Artist a:artists)
		{
			a.printDetailed();
			System.out.println("***********");
		}	
	}
	
	/**
	 * Prints the users classified by their subscription types
	 */
	public static void listSubscribedUsers()
	{
		ArrayList<User> publicSub = new ArrayList<>();
		ArrayList<User> studentSub = new ArrayList<>();
		ArrayList<User> premuimSub = new ArrayList<>();
		
		for(User user:users)
		{
			if(user.getSubscription() instanceof PublicSubscription)
				publicSub.add(user);
			else if(user.getSubscription() instanceof StudentSubscription)
				studentSub.add(user);
			else
				premuimSub.add(user);
		}
		
		System.out.println("Public subscribers:");
		if(publicSub.isEmpty())
			System.out.println("\tNo users in this category.");
		for(User u:publicSub)
		{
			System.out.print("\t");
			u.printBrief();
		}
		
		System.out.println("Student subscribers:");
		if(studentSub.isEmpty())
			System.out.println("\tNo users in this category.");
		for(User u:studentSub)
		{
			System.out.print("\t");
			u.printBrief();
		}
		
		System.out.println("Premium subscribers:");
		if(premuimSub.isEmpty())
			System.out.println("\tNo users in this category.");
		for(User u:premuimSub)
		{
			System.out.print("\t");
			u.printBrief();
		}
	}
	
	/**
	 * Increases song limits for public subscriptions
	 */
	public static void increaseSongLimitsPublicSubs() 
	{
		for(User user:users)
		{
			PublicSubscription publicSubscriptionObject;
			Subscription temp = user.getSubscription();
			if(temp instanceof PublicSubscription)
			{
				publicSubscriptionObject = (PublicSubscription) temp;
				publicSubscriptionObject.IncreaseSongLimits();
			}
		}
		System.out.println("Song limits were updated successfully for public subscriptions");
	}
	/*
	 * The sequence diagram of the previous method is provided in the submission folder.
	 * To simplify the sequence diagram, we can do either of the following in the class diagram:
	 * 1- We can store all the public subscription in an Arraylist in the MusicPlayer so we can iterate
	 * over them directly without going to every single user and testing if they have a public subscription.
	 * 2- We can put IncreaseSongLimit() as an abstract method in the Subscription class, and implement it normally in
	 * the publicSubscription subclass and implement it using empty methods in the other two subclasses.
	 * This way we still have to iterate over all users, but we won't have to test if their subscription is premium or not.
	 * Compared to the previous solution, this one saves some memory by not having an Arraylist of all public subscriptions. 
	 */
}

