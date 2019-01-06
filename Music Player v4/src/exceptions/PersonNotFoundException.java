package exceptions;

public class PersonNotFoundException extends Exception {
	public PersonNotFoundException(int id)
	{
		super("No person was found with id = " + id);
	}
}
