package interfaces;

import componentsV2.Person;

/**
 * 
 * Interface for following.
 *
 * @param <E>
 */
public interface IFollow<E> {
	/**
	 * follow a person
	 */
	public abstract void follow(E toBeFollowed);
	/**
	 * get followed by a person
	 */
	public abstract void followedBy(E toBeFollower);
	/**
	 * unfollow a person
	 */
	public void unfollow(Person toBeUnFollowed);
	/**
	 * remove a person from your followers
	 */
	public void removeFollower(Person toBeDeleted);
}
