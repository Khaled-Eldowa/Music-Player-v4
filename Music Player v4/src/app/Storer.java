package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import componentsV2.Album;
import componentsV2.Artist;
import componentsV2.Person;
import componentsV2.Playlist;
import componentsV2.PublicSubscription;
import componentsV2.Song;
import componentsV2.User;
import mainApp.MainMenu;

public class Storer {

	public static void store(MainMenu mainMenu)
	{
		BufferedWriter out = null;
		try {
		 
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("MusicPlayer.txt")));
			int nUsers = Backend.users.size();
			out.write(Integer.toString(nUsers));
			out.newLine();
			for(User user: Backend.users)
			{
				out.write(Integer.toString(user.getId()));
				out.newLine();
				out.write(user.getName());
				out.newLine();
				out.write(Integer.toString(user.getDOB().getYear()));
				out.newLine();
				out.write(Integer.toString(user.getDOB().getMonthValue()));
				out.newLine();
				out.write(Integer.toString(user.getDOB().getDayOfMonth()));
				out.newLine();
				out.write(user.getUserName());
				out.newLine();
				out.write(user.getSubscription().getEmail());
				out.newLine();	
			}
			
			int nArtists = Backend.artists.size();
			out.write(Integer.toString(nArtists));
			out.newLine();
			for(Artist artist: Backend.artists)
			{
				out.write(Integer.toString(artist.getId()));
				out.newLine();
				out.write(artist.getName());
				out.newLine();
				out.write(Integer.toString(artist.getDOB().getYear()));
				out.newLine();
				out.write(Integer.toString(artist.getDOB().getMonthValue()));
				out.newLine();
				out.write(Integer.toString(artist.getDOB().getDayOfMonth()));
				out.newLine();
				out.write(artist.getTypeOfMusic());
				out.newLine();
			}
			
			int nAlbums = Backend.albums.size();
			out.write(Integer.toString(nAlbums));
			out.newLine();
			for(Album album: Backend.albums)
			{
				out.write(Integer.toString(album.getId()));
				out.newLine();
				out.write(album.getName());
				out.newLine();
				out.write(album.getGenre());
				out.newLine();
				out.write(Integer.toString(album.getReleaseDate().getYear()));
				out.newLine();
				out.write(Integer.toString(album.getReleaseDate().getMonthValue()));
				out.newLine();
				out.write(Integer.toString(album.getReleaseDate().getDayOfMonth()));
				out.newLine();
				out.write(Integer.toString(album.getArtist().getId()));
				out.newLine();
			}
			
			int nSongs = Backend.songs.size();
			out.write(Integer.toString(nSongs));
			out.newLine();
			for(Song song: Backend.songs)
			{
				out.write(Integer.toString(song.getId()));
				out.newLine();
				out.write(song.getName());
				out.newLine();
				out.write(song.getGenre());
				out.newLine();
				out.write(Integer.toString(song.getReleaseDate().getYear()));
				out.newLine();
				out.write(Integer.toString(song.getReleaseDate().getMonthValue()));
				out.newLine();
				out.write(Integer.toString(song.getReleaseDate().getDayOfMonth()));
				out.newLine();
				out.write(Integer.toString(song.getAlbums().get(0).getId()));
				out.newLine();
				out.write(Integer.toString(song.getArtist().getId()));
				out.newLine();
			}
			
			int nPlaylists = Backend.playlists.size();
			out.write(Integer.toString(nPlaylists));
			out.newLine();
			for(Playlist playlist: Backend.playlists)
			{
				out.write(Integer.toString(playlist.getId()));
				out.newLine();
				out.write(playlist.getName());
				out.newLine();
				out.write(Integer.toString(playlist.getOwner().getId()));
				out.newLine();
				out.write(Integer.toString(playlist.getDateCreated().getYear()));
				out.newLine();
				out.write(Integer.toString(playlist.getDateCreated().getMonthValue()));
				out.newLine();
				out.write(Integer.toString(playlist.getDateCreated().getDayOfMonth()));
				out.newLine();
				out.write(Integer.toString(playlist.getSongs().size()));
				out.newLine();
				for(Song song: playlist.getSongs()) {
					out.write(Integer.toString(song.getId()));
					out.newLine();
				}
			}
			
			mainMenu.logMessage("Data Saved Successfully to MusicPlayer.txt");
			
			out.close();
		} catch (FileNotFoundException e) {
			mainMenu.logMessage("The provided file for storage was not found");
		} catch (IOException e) {
			mainMenu.logMessage("IO Exception");
		}
		finally {
			if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
					mainMenu.logMessage("IO Exception");
				}
		}
			
	}
	
	public static void load(MainMenu mainMenu, File file)
	{
		BufferedReader in = null;
		try {
		 
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
			int nUsers = Integer.parseInt(in.readLine());
			for(int i=0; i<nUsers; i++)
			{
				int id = Integer.parseInt(in.readLine());
				String name = in.readLine();
				int y = Integer.parseInt(in.readLine());
				int m = Integer.parseInt(in.readLine());
				int d = Integer.parseInt(in.readLine());
				String userName = in.readLine();
				String email = in.readLine();
				User temp = new User(id, name, LocalDate.of(y, m, d), new ArrayList<Person>(), new ArrayList<Person>(), userName, new ArrayList<Album>(), new ArrayList<Playlist>(), new PublicSubscription(email));
				Backend.users.add(temp);
			}
			
			int nArtists = Integer.parseInt(in.readLine());
			for(int i=0; i<nArtists; i++)
			{
				int id = Integer.parseInt(in.readLine());
				String name = in.readLine();
				int y = Integer.parseInt(in.readLine());
				int m = Integer.parseInt(in.readLine());
				int d = Integer.parseInt(in.readLine());
				String genre = in.readLine();
				Artist temp = new Artist(id, name, LocalDate.of(y, m, d), new ArrayList<Person>(), new ArrayList<Person>(), genre, new ArrayList<String>(), new ArrayList<Album>(), new ArrayList<Song>());
				Backend.artists.add(temp);
			}
			
			int nAlbums = Integer.parseInt(in.readLine());
			for(int i=0; i<nAlbums; i++)
			{
				int id = Integer.parseInt(in.readLine());
				String name = in.readLine();
				String genre = in.readLine();
				int y = Integer.parseInt(in.readLine());
				int m = Integer.parseInt(in.readLine());
				int d = Integer.parseInt(in.readLine());
				int artistID = Integer.parseInt(in.readLine());
				Artist artist = Backend.getArtist(artistID);
				Album temp = new Album(id, name, genre, LocalDate.of(y, m, d), artist, new ArrayList<Song>());
				Backend.albums.add(temp);
				artist.addAlbum(temp);
			}
			
			int nSongs = Integer.parseInt(in.readLine());
			for(int i=0; i<nSongs; i++)
			{
				int id = Integer.parseInt(in.readLine());
				String name = in.readLine();
				String genre = in.readLine();
				int y = Integer.parseInt(in.readLine());
				int m = Integer.parseInt(in.readLine());
				int d = Integer.parseInt(in.readLine());
				int albumID = Integer.parseInt(in.readLine());
				int artistID = Integer.parseInt(in.readLine());
				Album album = Backend.getAlbum(albumID);
				ArrayList<Album> albums= new ArrayList<>();
				albums.add(album);
				Artist artist = Backend.getArtist(artistID);
				Song temp = new Song(id, name, genre, LocalDate.of(y, m, d), albums, artist);
				Backend.songs.add(temp);
				album.addSong(temp);
				artist.addSong(temp);
			}
			
			int nPlaylists = Integer.parseInt(in.readLine());
			for(int i=0; i<nPlaylists; i++)
			{
				int id = Integer.parseInt(in.readLine());
				String name = in.readLine();
				int ownerID = Integer.parseInt(in.readLine());
				User owner = Backend.getUser(ownerID);
				int y = Integer.parseInt(in.readLine());
				int m = Integer.parseInt(in.readLine());
				int d = Integer.parseInt(in.readLine());
				int songsNumber = Integer.parseInt(in.readLine());
				ArrayList<Song> songs = new ArrayList<>();
				for(int j=0; j<songsNumber; j++)
				{
					songs.add(Backend.getSong(Integer.parseInt(in.readLine())));
				}
				Playlist temp = new Playlist(id, name, owner, LocalDate.of(y, m, d), songs);
				Backend.playlists.add(temp);
				owner.addPlaylist(temp);
			}
			
			mainMenu.logMessage("File Loaded Successfully");
			
			in.close();
		} catch (FileNotFoundException e) {
			mainMenu.logMessage("The provided file for loading was not found");
		} catch (IOException e) {
			mainMenu.logMessage("IO Exception");
		}
		finally {
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					mainMenu.logMessage("IO Exception");
				}
		}
			
	}
}
