package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import app.Backend;
import componentsV2.Album;
import componentsV2.Artist;
import componentsV2.Person;
import componentsV2.Playlist;
import componentsV2.PublicSubscription;
import componentsV2.Song;
import componentsV2.User;
import exceptions.DataBaseProcessException;

public class DB_operations {

	//JDBC Driver Name and Database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/hms";

    //Database Credentials
    static final String USER = "cng443user";
    static final String PASS = "1234";
    
    static Connection conn;
    static Statement stmt;
    
    /**
     * set up the db connection
     * modular function used by many functions in this class 
     * @throws DataBaseProcessException
     */
    private static void setUpDB() throws DataBaseProcessException {
        try {
            //Register the JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Open a connection
            System.out.println("Connecting to database....");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a Query
            System.out.println("Creating statement....");
            stmt = conn.createStatement();
        } catch(SQLException se) {
        	se.printStackTrace();
        	throw new DataBaseProcessException("SQL exception encountered while setting up the connection; check stack trace");
        } catch(ClassNotFoundException e) {
        	e.printStackTrace();
        	throw new DataBaseProcessException("Failed to load the jdbc driver; check stack trace");
        }
    }
    
    /**
     * close the db connection
     * modular function used by many functions in this class 
     * @throws DataBaseProcessException
     */
    private static void closeDB() throws DataBaseProcessException {
        //block used to close resources
        try
        {
            if(stmt!=null)
                stmt.close();
        }
        catch(SQLException se2)
        {
        	se2.printStackTrace();
        	throw new DataBaseProcessException("SQL exception encountered while closing the statement; check stack trace");
        }

        try
        {
            if(conn!=null)
                conn.close();
        }
        catch(SQLException se3)
        {
        	se3.printStackTrace();
        	throw new DataBaseProcessException("SQL exception encountered while closing the connection; check stack trace");
        }
        System.out.println("Closing DB Connection");
    }
    
    /**
     * Fetches a certain field given a table, field, and a filter SQL statement(or none)
     * @param table
     * @param field
     * @param filter
     * @return array of the matches
     * @throws DataBaseProcessException
     */
    public static ArrayList<String> fetchField(String table, String field, String filter) throws DataBaseProcessException{
        setUpDB();
        ArrayList<String> fetchedList = _fetchField(table, field, filter);
        closeDB();
        return fetchedList;
    }
    
    /**
     * Auxillary for fetchfield
     * @param table
     * @param field
     * @param filter
     * @return
     * @throws DataBaseProcessException
     */
    private static ArrayList<String> _fetchField(String table, String field, String filter) throws DataBaseProcessException{
        ArrayList<String> fetchedList = new ArrayList<String>();
        String sql = "SELECT " + field + " FROM " + table;
        if(filter != null)
            sql = sql + filter;
        try{
            ResultSet rs = stmt.executeQuery(sql);
            //Extract Results from Result Set
            while(rs.next())
            {
                fetchedList.add(rs.getString(field));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new DataBaseProcessException("SQL exception encountered while exceuting or getting results from '" + sql + "'");
        }

        return fetchedList;
    }
    
    /**
     * The following six functions store elements of the MusicPlayer to the database.
     * The sql commands can explain the flow
     */
    public static boolean storeUser(User user) throws DataBaseProcessException {
    	if(fetchField("user", "id", " WHERE id = \"" + user.getId() + "\"").size() > 0)
            return false;
    	
        setUpDB();
        String sql = "INSERT INTO user (id, name, date_of_birth, userName, email)" +
                " VALUES ('"+ user.getId() + "', '" + user.getName() + "', '" + user.getDOB().toEpochDay() + "', '" +
                user.getUserName() + "', '" + user.getSubscription().getEmail() + "')";
        _update(sql);
        closeDB();
        return true;
    }
    
    public static boolean storeArtist(Artist artist) throws DataBaseProcessException {
    	if(fetchField("artist", "id", " WHERE id = \"" + artist.getId() + "\"").size() > 0)
            return false;
    	
        setUpDB();
        String sql = "INSERT INTO artist (id, name, date_of_birth, typeOfMusic)" +
                " VALUES ('"+ artist.getId() + "', '" + artist.getName() + "', '" + artist.getDOB().toEpochDay() +
                "', '" + artist.getTypeOfMusic() + "')";
        _update(sql);
        closeDB();
        return true;
    }
      
    public static boolean storeAlbum(Album album) throws DataBaseProcessException {
    	if(fetchField("album", "id", " WHERE id = \"" + album.getId() + "\"").size() > 0)
            return false;
    	
        setUpDB();
        String sql = "INSERT INTO album (id, name, releaseDate, genre, artist_id)" +
                " VALUES ('"+ album.getId() + "', '" + album.getName() + "', '" + album.getReleaseDate().toEpochDay() +
                "', '" + album.getGenre() + "', '" + album.getArtist().getId() + "')";
        _update(sql);
        closeDB();
        return true;
    }
    
    public static boolean storeSong(Song song) throws DataBaseProcessException {
    	if(fetchField("song", "id", " WHERE id = \"" + song.getId() + "\"").size() > 0)
            return false;
    	
        setUpDB();
        String sql = "INSERT INTO song (id, name, releaseDate, genre, artist_id, album_id)" +
                " VALUES ('"+ song.getId() + "', '" + song.getName() + "', '" + song.getReleaseDate().toEpochDay() +
                "', '" + song.getGenre() + "', '" + song.getArtist().getId() + "', '" + song.getAlbums().get(0).getId() + "')";
        _update(sql);
        closeDB();
        return true;
    }
    
    public static boolean storePlaylist(Playlist playlist) throws DataBaseProcessException {
    	if(fetchField("playlist", "id", " WHERE id = \"" + playlist.getId() + "\"").size() > 0)
            return false;
    	
        setUpDB();
        String sql = "INSERT INTO playlist (id, name, dateCreated, owner_id)" +
                " VALUES ('"+ playlist.getId() + "', '" + playlist.getName() + "', '" + playlist.getDateCreated().toEpochDay() +
                "', '" + playlist.getOwner().getId() + "')";
        _update(sql);
        closeDB();
        return true;
    }
    
    public static boolean storePlaylistMapping(Playlist playlist, Song song) throws DataBaseProcessException {
    	if(fetchField("playlist_song_mapping", "id", " WHERE playlist_id = \"" + playlist.getId() + "\"" + 
    				" AND song_id = \"" + song.getId() + "\"").size() > 0)
            return false;
    	
        setUpDB();
        String sql = "INSERT INTO playlist_song_mapping (playlist_id, song_id)" +
                " VALUES ('"+ playlist.getId() + "', '" + song.getId() + "')";
        _update(sql);
        closeDB();
        return true;
    }
    
    /**
     * Modular function used by all storing functions above
     * @param sql
     * @throws DataBaseProcessException
     */
    private static void _update(String sql) throws DataBaseProcessException {
    	try{
            stmt.executeUpdate(sql);
        } catch(SQLException e){
        	e.printStackTrace();
            throw new DataBaseProcessException("SQL exception encountered while exceuting or getting results from '" + sql + "'");
        }
    }
    
    /**
     * The following five pairs of functions are concerned with the retrieval of the system's components
     * One function gets the rows from the database and returns them as objects, the other one is concerned with adding these objects to the system
     */
    public static int retrieveUsers() throws DataBaseProcessException {
    	
        setUpDB();
        int duplicates = 0;
        ArrayList<User> fetched = _retrieveUsers();
        if(!fetched.isEmpty()) {
        	for(User newUser: fetched) {
	        	if(Backend.getUser(newUser.getId()) == null)
	        		Backend.users.add(newUser);
	        	else
	        		duplicates++;
        	}
        }
        else
        	throw new DataBaseProcessException("No users were found in the database!");
        closeDB();
        return duplicates;
    }
    
    private static ArrayList<User> _retrieveUsers() throws DataBaseProcessException {
    	String sql = "SELECT * FROM user";

        try{
            ResultSet rs = stmt.executeQuery(sql);
            String name, userName, email;
            int id;
            long dob;
            ArrayList<User> fetched = new ArrayList<>();
            while(rs.next())
            {
            	id = rs.getInt("id");
                name = rs.getString("name");
                userName = rs.getString("userName");
                email = rs.getString("email");
                dob = rs.getLong("date_of_birth");
                fetched.add(new User(id, name, LocalDate.ofEpochDay(dob), new ArrayList<Person>(), new ArrayList<Person>(), userName, new ArrayList<Album>(), new ArrayList<Playlist>(), new PublicSubscription(email)));
            }
            return fetched;
            
        } catch(SQLException e){
        	e.printStackTrace();
            throw new DataBaseProcessException("SQL exception encountered while exceuting or getting results from '" + sql + "'");
        }
    }
    
    public static int retrieveArtists() throws DataBaseProcessException {
    	
        setUpDB();
        int duplicates = 0;
        ArrayList<Artist> fetched = _retrieveArtists();
        if(!fetched.isEmpty()) {
        	for(Artist newArtist: fetched) {
	        	if(Backend.getArtist(newArtist.getId()) == null)
	        		Backend.artists.add(newArtist);
	        	else
	        		duplicates++;
        	}
        }
        else
        	throw new DataBaseProcessException("No artists were found in the database!");
        closeDB();
        return duplicates;
    }
    
    private static ArrayList<Artist> _retrieveArtists() throws DataBaseProcessException {
    	String sql = "SELECT * FROM artist";

        try{
            ResultSet rs = stmt.executeQuery(sql);
            String name, typeOfMusic;
            int id;
            long dob;
            ArrayList<Artist> fetched = new ArrayList<>();
            while(rs.next())
            {
            	id = rs.getInt("id");
                name = rs.getString("name");
                typeOfMusic = rs.getString("typeOfMusic");
                dob = rs.getLong("date_of_birth");
                fetched.add(new Artist(id, name, LocalDate.ofEpochDay(dob), new ArrayList<Person>(), new ArrayList<Person>(), typeOfMusic, new ArrayList<String>(), new ArrayList<Album>(), new ArrayList<Song>()));
            }
            return fetched;
            
        } catch(SQLException e){
        	e.printStackTrace();
            throw new DataBaseProcessException("SQL exception encountered while exceuting or getting results from '" + sql + "'");
        }
    }
    
    public static int retrieveAlbums() throws DataBaseProcessException {
    	
        setUpDB();
        int duplicates = 0;
        ArrayList<Album> fetched = _retrieveAlbums();
        if(!fetched.isEmpty()) {
        	for(Album newAlbum: fetched) {
	        	if(Backend.getAlbum(newAlbum.getId()) == null) {
	        		Backend.albums.add(newAlbum);
	        		newAlbum.getArtist().addAlbum(newAlbum);
	        	}
	        	else
	        		duplicates++;
        	}
        }
        else
        	throw new DataBaseProcessException("No albums were found in the database!");
        closeDB();
        return duplicates;
    }
    
    private static ArrayList<Album> _retrieveAlbums() throws DataBaseProcessException {
    	String sql = "SELECT * FROM album";

        try{
            ResultSet rs = stmt.executeQuery(sql);
            String name, genre;
            int id, artist_id;
            long releaseDate;
            Artist artist;
            ArrayList<Album> fetched = new ArrayList<>();
            while(rs.next())
            {
            	id = rs.getInt("id");
                name = rs.getString("name");
                genre = rs.getString("genre");
                releaseDate = rs.getLong("releaseDate");
                artist_id = rs.getInt("artist_id");
                artist = Backend.getArtist(artist_id);
                fetched.add(new Album(id, name, genre, LocalDate.ofEpochDay(releaseDate), artist, new ArrayList<Song>()));
            }
            return fetched;
            
        } catch(SQLException e){
        	e.printStackTrace();
            throw new DataBaseProcessException("SQL exception encountered while exceuting or getting results from '" + sql + "'");
        }
    }
    
    public static int retrieveSongs() throws DataBaseProcessException {
    	
        setUpDB();
        int duplicates = 0;
        ArrayList<Song> fetched = _retrieveSongs();
        if(!fetched.isEmpty()) {
        	for(Song newSong: fetched) {
	        	if(Backend.getSong(newSong.getId()) == null) {
	        		Backend.songs.add(newSong);
	        		newSong.getArtist().addSong(newSong);
	        		newSong.getAlbums().get(0).addSong(newSong);
	        	}
	        	else
	        		duplicates++;
        	}
        }
        else
        	throw new DataBaseProcessException("No songs were found in the database!");
        closeDB();
        return duplicates;
    }
    
    private static ArrayList<Song> _retrieveSongs() throws DataBaseProcessException {
    	String sql = "SELECT * FROM song";

        try{
            ResultSet rs = stmt.executeQuery(sql);
            String name, genre;
            int id, artist_id, album_id;
            long releaseDate;
            Artist artist;
            ArrayList<Album> albums= new ArrayList<>();
            Album album;
            ArrayList<Song> fetched = new ArrayList<>();
            while(rs.next())
            {
            	id = rs.getInt("id");
                name = rs.getString("name");
                genre = rs.getString("genre");
                releaseDate = rs.getLong("releaseDate");
                artist_id = rs.getInt("artist_id");
                artist = Backend.getArtist(artist_id);
                album_id = rs.getInt("album_id");
                album = Backend.getAlbum(album_id);
                albums.add(album);
                fetched.add(new Song(id, name, genre, LocalDate.ofEpochDay(releaseDate), albums, artist));
            }
            return fetched;
            
        } catch(SQLException e){
        	e.printStackTrace();
            throw new DataBaseProcessException("SQL exception encountered while exceuting or getting results from '" + sql + "'");
        }
    }
    
    public static int retrievePlaylists() throws DataBaseProcessException {
    	
        setUpDB();
        int duplicates = 0;
        ArrayList<Playlist> fetched = _retrievePlaylists();
        if(!fetched.isEmpty()) {
        	for(Playlist newPlaylist: fetched) {
	        	if(Backend.getPlaylist(newPlaylist.getId()) == null) {
	        		retrievePlaylistSongs(newPlaylist.getId()).forEach(newPlaylist::addSong);
	        		Backend.playlists.add(newPlaylist);
	        		newPlaylist.getOwner().addPlaylist(newPlaylist);
	        	}
	        	else
	        		duplicates++;
        	}
        }
        else
        	throw new DataBaseProcessException("No playlists were found in the database!");
        closeDB();
        return duplicates;
    }
    
    private static ArrayList<Playlist> _retrievePlaylists() throws DataBaseProcessException {
    	String sql = "SELECT * FROM playlist";

        try{
            ResultSet rs = stmt.executeQuery(sql);
            String name;
            int id, owner_id;
            long dateCreated;
            User owner;
            ArrayList<Playlist> fetched = new ArrayList<>();
            
            while(rs.next())
            {
            	id = rs.getInt("id");
                name = rs.getString("name");
                dateCreated = rs.getLong("dateCreated");
                owner_id = rs.getInt("owner_id");
                owner = Backend.getUser(owner_id);
                fetched.add(new Playlist(id, name, owner, LocalDate.ofEpochDay(dateCreated), new ArrayList<Song>())); //empty songs list just for now, follow the trace
            }
            return fetched;
            
        } catch(SQLException e){
        	e.printStackTrace();
            throw new DataBaseProcessException("SQL exception encountered while exceuting or getting results from '" + sql + "'");
        }
    }
    
    /**
     * gets the list of songs in a playlist
     * @param playlist_id
     * @return list of songs
     * @throws DataBaseProcessException
     */
    private static ArrayList<Song> retrievePlaylistSongs(int playlist_id) throws DataBaseProcessException {
    	String sql = "SELECT * FROM playlist_song_mapping WHERE playlist_id = \"" + playlist_id + "\"";

        try{
            ResultSet rs = stmt.executeQuery(sql);
            int song_id;
            ArrayList<Song> fetched = new ArrayList<>();
            while(rs.next())
            {
                song_id = rs.getInt("song_id");
                fetched.add(Backend.getSong(song_id));
            }
            return fetched;
            
        } catch(SQLException e){
        	e.printStackTrace();
            throw new DataBaseProcessException("SQL exception encountered while exceuting or getting results from '" + sql + "'");
        }
    }
    
    /**
     * the following five functions are concerned with deleting the system's components from the database.
     */
    public static void deleteAllUsers() throws DataBaseProcessException {
        setUpDB();
        _delete("user");
        closeDB();
    }
    
    public static void deleteAllArtists() throws DataBaseProcessException {
        setUpDB();
        _delete("artist");
        closeDB();
    }
    
    public static void deleteAllAlbums() throws DataBaseProcessException {
        setUpDB();
        _delete("album");
        closeDB();
    }
    
    public static void deleteAllSongs() throws DataBaseProcessException {
        setUpDB();
        _delete("song");
        closeDB();
    }
    
    public static void deleteAllPlaylists() throws DataBaseProcessException {
        setUpDB();
        _delete("playlist_song_mapping");
        _delete("playlist");
        closeDB();
    }
    
    /**
     * Modular function that deletes all rows from the given table
     * @param table
     * @throws DataBaseProcessException
     */
    private static void _delete(String table) throws DataBaseProcessException {
    	String sql = "DELETE FROM " + table;

        try{
            stmt.executeUpdate(sql);
        } catch(SQLException e){
        	e.printStackTrace();
            throw new DataBaseProcessException("SQL exception encountered while exceuting or getting results from '" + sql + "'");
        }
    }
    
}
