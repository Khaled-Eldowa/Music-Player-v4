package exceptions;

public class PlaylistNotFoundException extends Exception {
	public PlaylistNotFoundException(int id)
	{
		super("No playlist was found with id = " + id);
	}
}
