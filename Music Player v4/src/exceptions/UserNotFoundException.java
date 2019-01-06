package exceptions;

public class UserNotFoundException extends Exception {
	public UserNotFoundException(int id)
	{
		super("No user was found with id = " + id);
	}
	public UserNotFoundException(String name)
	{
		super("No user was found with name = " + name);
	}
}
