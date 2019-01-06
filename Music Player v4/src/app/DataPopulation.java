package app;

import componentsV2.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Old class from previous assignments
 */
public class DataPopulation {
	public static void populateBackend()
	{
		//add an artist with two albums each containing three songs.
		Artist Trivium = Backend.addArtist("Trivium", LocalDate.of(1999, 1, 1) , "Heavy Metal", new ArrayList<String>());
		Album _Shogun = Backend.addAlbum("Shogun", "Heavy Metal", LocalDate.of(2008, 9, 30), Trivium);
		ArrayList<Album> Shogun = new ArrayList<>();
		Shogun.add(_Shogun);
		ArrayList<Song> ShogunSongs = new ArrayList<>();
		ShogunSongs.add(Backend.addSong("Kirisute Gomen", Shogun.get(0).getGenre(), Shogun.get(0).getReleaseDate(), Shogun, Shogun.get(0).getArtist()));
		ShogunSongs.add(Backend.addSong("Down From the Sky", Shogun.get(0).getGenre(), Shogun.get(0).getReleaseDate(), Shogun, Shogun.get(0).getArtist()));
		ShogunSongs.add(Backend.addSong("Insurrection", Shogun.get(0).getGenre(), Shogun.get(0).getReleaseDate(), Shogun, Shogun.get(0).getArtist()));
		Album _TSATS = Backend.addAlbum("The Sin and the Sentence", "Heavy Metal", LocalDate.of(2017, 10, 17), Trivium);
		ArrayList<Album> TSATS = new ArrayList<>();
		TSATS.add(_TSATS);
		ArrayList<Song> TSATSSongs = new ArrayList<>();
		TSATSSongs.add(Backend.addSong("Beyond Oblivion", TSATS.get(0).getGenre(), TSATS.get(0).getReleaseDate(), TSATS, TSATS.get(0).getArtist()));
		TSATSSongs.add(Backend.addSong("Sever the Hand", TSATS.get(0).getGenre(), TSATS.get(0).getReleaseDate(), TSATS, TSATS.get(0).getArtist()));
		TSATSSongs.add(Backend.addSong("Thrown into the Fire", TSATS.get(0).getGenre(), TSATS.get(0).getReleaseDate(), TSATS, TSATS.get(0).getArtist()));
		Album _SITS = Backend.addAlbum("Silence in the Snow", "Heavy Metal", LocalDate.of(2015, 10, 2), Trivium);
		ArrayList<Album> SITS = new ArrayList<>();
		SITS.add(_SITS);
		ArrayList<Song> SITSSongs = new ArrayList<>();
		SITSSongs.add(Backend.addSong("Blind Leading the Blind", SITS.get(0).getGenre(), SITS.get(0).getReleaseDate(), SITS, SITS.get(0).getArtist()));
		SITSSongs.add(Backend.addSong("Pull Me from the Void", SITS.get(0).getGenre(), SITS.get(0).getReleaseDate(), SITS, SITS.get(0).getArtist()));
		SITSSongs.add(Backend.addSong("Until the World Goes Cold", SITS.get(0).getGenre(), SITS.get(0).getReleaseDate(), SITS, SITS.get(0).getArtist()));
		
		//add an artist with two albums each containing three songs.
		Artist InVain = Backend.addArtist("In Vain", LocalDate.of(2003, 1, 1), "Melodic Death Metal", new ArrayList<String>());
		Album _Currents = Backend.addAlbum("Currents", "Melodic Death Metal", LocalDate.of(2018, 1, 26), InVain);
		ArrayList<Album> Currents = new ArrayList<>();
		Currents.add(_Currents);
		ArrayList<Song> CurrentsSongs = new ArrayList<>();
		CurrentsSongs.add(Backend.addSong("Seekers of the Truth", Currents.get(0).getGenre(), Currents.get(0).getReleaseDate(), Currents, Currents.get(0).getArtist()));
		CurrentsSongs.add(Backend.addSong("Origin", Currents.get(0).getGenre(), Currents.get(0).getReleaseDate(), Currents, Currents.get(0).getArtist()));
		CurrentsSongs.add(Backend.addSong("As the Black Horde Storms", Currents.get(0).getGenre(), Currents.get(0).getReleaseDate(), Currents, Currents.get(0).getArtist()));
		Album _Aenigma = Backend.addAlbum("Aenigma", "Melodic Death Metal", LocalDate.of(2013, 3, 11), InVain);
		ArrayList<Album> Aenigma = new ArrayList<>();
		Aenigma.add(_Aenigma);
		ArrayList<Song> AenigmaSongs = new ArrayList<>();
		AenigmaSongs.add(Backend.addSong("Against the Grain", Aenigma.get(0).getGenre(), Aenigma.get(0).getReleaseDate(), Aenigma, Aenigma.get(0).getArtist()));
		AenigmaSongs.add(Backend.addSong("Image of Time", Aenigma.get(0).getGenre(), Aenigma.get(0).getReleaseDate(), Aenigma, Aenigma.get(0).getArtist()));
		AenigmaSongs.add(Backend.addSong("To the Core", Aenigma.get(0).getGenre(), Aenigma.get(0).getReleaseDate(), Aenigma, Aenigma.get(0).getArtist()));
		Album _Mantra = Backend.addAlbum("Mantra", "Melodic Death Metal", LocalDate.of(2010, 1, 18), InVain);
		ArrayList<Album> Mantra = new ArrayList<>();
		Mantra.add(_Mantra);
		ArrayList<Song> MantraSongs = new ArrayList<>();
		MantraSongs.add(Backend.addSong("Captivating Solitude", Mantra.get(0).getGenre(), Mantra.get(0).getReleaseDate(), Mantra, Mantra.get(0).getArtist()));
		MantraSongs.add(Backend.addSong("Dark Prophets, Black Hearts", Mantra.get(0).getGenre(), Mantra.get(0).getReleaseDate(), Mantra, Mantra.get(0).getArtist()));
		MantraSongs.add(Backend.addSong("Wayakin", Mantra.get(0).getGenre(), Mantra.get(0).getReleaseDate(), Mantra, Mantra.get(0).getArtist()));
		
		//add three users
		User Luna = Backend.addUser("Luna", LocalDate.of(1992,1,1), "Luna",new ArrayList<Album>(), new ArrayList<Playlist>(), "Luna@NASA.com");
		Backend.followPerson(Luna, Trivium);
		Backend.followPerson(Luna, InVain);
		User Ganymede = Backend.addUser("Ganymede", LocalDate.of(1992,1,1), "Ganymede", new ArrayList<Album>(), new ArrayList<Playlist>(), "Ganymede@NASA.com");
		Backend.followPerson(Ganymede, Luna);
		Backend.followPerson(Ganymede, Trivium);
		Backend.followPerson(Ganymede, InVain);
		User Titan = Backend.addUser("Titan", LocalDate.of(1992,1,1), "Titan", new ArrayList<Album>(), new ArrayList<Playlist>(), "Titan@NASA.com");
		Backend.followPerson(Titan, Luna);
		Backend.followPerson(Titan, Ganymede);
		Backend.followPerson(Titan, Trivium);
		Backend.followPerson(Titan, InVain);
		
		//add a playlist containing some songs
		ArrayList<Song> songs1 = new ArrayList<>();
		songs1.add(ShogunSongs.get(0));
		songs1.add(TSATSSongs.get(0));
		songs1.add(SITSSongs.get(0));
		songs1.add(CurrentsSongs.get(0));
		songs1.add(AenigmaSongs.get(0));
		songs1.add(MantraSongs.get(0));
		Backend.addPlaylist("First Mix", Luna, LocalDate.of(2018, 10, 15), songs1);
		
		//add a playlist containing some songs
		ArrayList<Song> songs2 = new ArrayList<>();
		songs2.add(ShogunSongs.get(1));
		songs2.add(TSATSSongs.get(1));
		songs2.add(SITSSongs.get(1));
		songs2.add(CurrentsSongs.get(1));
		songs2.add(AenigmaSongs.get(1));
		songs2.add(MantraSongs.get(1));
		Backend.addPlaylist("Second Mix", Ganymede, LocalDate.of(2018, 10, 16), songs2);
		
		//add a playlist containing some songs
		ArrayList<Song> songs3 = new ArrayList<>();
		songs3.add(ShogunSongs.get(2));
		songs3.add(TSATSSongs.get(2));
		songs3.add(SITSSongs.get(2));
		songs3.add(CurrentsSongs.get(2));
		songs3.add(AenigmaSongs.get(2));
		songs3.add(MantraSongs.get(2));
		Backend.addPlaylist("Third Mix", Titan, LocalDate.of(2018, 10, 17), songs3);
	}
}
