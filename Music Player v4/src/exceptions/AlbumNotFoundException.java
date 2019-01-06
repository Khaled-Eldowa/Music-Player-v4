package exceptions;

public class AlbumNotFoundException extends Exception {
	public AlbumNotFoundException(int id)
	{
		super("No album was found with id = " + id);
	}
}
