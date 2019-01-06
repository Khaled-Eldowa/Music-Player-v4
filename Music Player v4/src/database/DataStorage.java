package database;

import app.Backend;
import componentsV2.Album;
import componentsV2.Artist;
import componentsV2.Playlist;
import componentsV2.Song;
import componentsV2.User;
import exceptions.DataBaseProcessException;
import mainApp.MainMenu;

public class DataStorage {

	/**
	 * read data from the database
	 * @param gui
	 */
	public void readData(MainMenu gui) {
		
		/**
		 * Retrieve all the system's components
		 * separate try catches to diagnose problems independently
		 * Inside the called functions, the data is being added to the Backend arrays
		 */
		
		int duplicates;
		
		try {
			duplicates = DB_operations.retrieveUsers();
			gui.logMessage("Users fetched from the database, found " + duplicates + " duplicates");
		} catch (DataBaseProcessException e) {
			e.printStackTrace();
			gui.logMessage(e.getMessage());
		}
		
		try {
			duplicates = DB_operations.retrieveArtists();
			gui.logMessage("Artists fetched from the database, found " + duplicates + " duplicates");
		} catch (DataBaseProcessException e) {
			e.printStackTrace();
			gui.logMessage(e.getMessage());
		}
		
		try {
			duplicates = DB_operations.retrieveAlbums();
			gui.logMessage("Albums fetched from the database, found " + duplicates + " duplicates");
		} catch (DataBaseProcessException e) {
			e.printStackTrace();
			gui.logMessage(e.getMessage());
		}
		
		try {
			duplicates = DB_operations.retrieveSongs();
			gui.logMessage("Songs fetched from the database, found " + duplicates + " duplicates");
		} catch (DataBaseProcessException e) {
			e.printStackTrace();
			gui.logMessage(e.getMessage());
		}
		
		try {
			duplicates = DB_operations.retrievePlaylists();
			gui.logMessage("Playlists fetched from the database, found " + duplicates + " duplicates");
		} catch (DataBaseProcessException e) {
			e.printStackTrace();
			gui.logMessage(e.getMessage());
		}
		
	}
	
	/**
	 * Write the data to the database
	 * @param gui
	 */
	public void writeData(MainMenu gui) {
		
		/**
		 * Store all the system's components
		 * separate try catches to diagnose problems independently
		 */
		
		boolean check;
		
		for(User user: Backend.users) {
			try {
				check = DB_operations.storeUser(user);
				if(check)
					gui.logMessage("User with id = " + user.getId() + " was stored successfully to the database");
				else
					gui.logMessage("User with id = " + user.getId() + " is already in the database");
			} catch (DataBaseProcessException e) {
				e.printStackTrace();
				gui.logMessage(e.getMessage());
			}
		}
		
		for(Artist artist: Backend.artists) {
			try {
				check = DB_operations.storeArtist(artist);
				if(check)
					gui.logMessage("Artist with id = " + artist.getId() + " was stored successfully to the database");
				else
					gui.logMessage("Artist with id = " + artist.getId() + " is already in the database");
			} catch (DataBaseProcessException e) {
				e.printStackTrace();
				gui.logMessage(e.getMessage());
			}
		}
		
		for(Album album: Backend.albums) {
			try {
				check = DB_operations.storeAlbum(album);
				if(check)
					gui.logMessage("Album with id = " + album.getId() + " was stored successfully to the database");
				else
					gui.logMessage("Album with id = " + album.getId() + " is already in the database");
			} catch (DataBaseProcessException e) {
				e.printStackTrace();
				gui.logMessage(e.getMessage());
			}
		}
		
		for(Song song: Backend.songs) {
			try {
				check = DB_operations.storeSong(song);
				if(check)
					gui.logMessage("Song with id = " + song.getId() + " was stored successfully to the database");
				else
					gui.logMessage("Song with id = " + song.getId() + " is already in the database");
			} catch (DataBaseProcessException e) {
				e.printStackTrace();
				gui.logMessage(e.getMessage());
			}
		}
		
		for(Playlist playlist: Backend.playlists) {
			try {
				check = DB_operations.storePlaylist(playlist);
				if(check) {
					for(Song song: playlist.getSongs()) {
						DB_operations.storePlaylistMapping(playlist, song);
					}
					gui.logMessage("Playlist with id = " + playlist.getId() + " was stored successfully to the database");
				}
				else
					gui.logMessage("Playlist with id = " + playlist.getId() + " is already in the database");
			} catch (DataBaseProcessException e) {
				e.printStackTrace();
				gui.logMessage(e.getMessage());
			}
		}
		
	}
	
	/**
	 * Clear the database from all the data
	 * @param gui
	 */
	public void clearDataBase(MainMenu gui) {
		
		try {
			DB_operations.deleteAllPlaylists();
			gui.logMessage("All playlists were deleted from the database");
		} catch (DataBaseProcessException e) {
			e.printStackTrace();
			gui.logMessage(e.getMessage());
		}
		
		try {
			DB_operations.deleteAllSongs();
			gui.logMessage("All songs were deleted from the database");
		} catch (DataBaseProcessException e) {
			e.printStackTrace();
			gui.logMessage(e.getMessage());
		}
		
		try {
			DB_operations.deleteAllAlbums();
			gui.logMessage("All albums were deleted from the database");
		} catch (DataBaseProcessException e) {
			e.printStackTrace();
			gui.logMessage(e.getMessage());
		}
		
		try {
			DB_operations.deleteAllArtists();
			gui.logMessage("All artists were deleted from the database");
		} catch (DataBaseProcessException e) {
			e.printStackTrace();
			gui.logMessage(e.getMessage());
		}
		
		try {
			DB_operations.deleteAllUsers();
			gui.logMessage("All users were deleted from the database");
		} catch (DataBaseProcessException e) {
			e.printStackTrace();
			gui.logMessage(e.getMessage());
		}
		
	}
}
