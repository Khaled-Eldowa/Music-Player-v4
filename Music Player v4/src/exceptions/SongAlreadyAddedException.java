package exceptions;

public class SongAlreadyAddedException extends Exception {
	public SongAlreadyAddedException(int id)
	{
		super("The song with id = " + id + " was already added");
	}
}
