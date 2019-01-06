package exceptions;

public class SongNotFoundException extends Exception {
	public SongNotFoundException(int id)
	{
		super("No song was found with id = " + id);
	}
	public SongNotFoundException(String name)
	{
		super("No song was found with name = " + name);
	}
}
