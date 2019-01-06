package exceptions;

public class ArtistNotFoundException extends Exception {
	public ArtistNotFoundException(String name)
	{
		super("No artist was found with name = " + name);
	}
}
