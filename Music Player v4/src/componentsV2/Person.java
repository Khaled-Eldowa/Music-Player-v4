package componentsV2;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import interfaces.IFollow;

/**
 * 
 * Class for representing a person.
 * implements the ifollow interface.
 */
public abstract class Person implements IFollow<Person>, Serializable{

	private static int nextID = 0;
	private int id;
	private String name;
	private LocalDate DOB;
	private ArrayList<Person> listOfFollowers;
	private ArrayList<Person> listOfFollowings;
	
	/**
	 * The constructor I mainly use.
	 * No followers, following at creation, auto ID.
	 * @param name
	 * @param DOB
	 */
	public Person(String name, LocalDate DOB)
	{
		this.id = nextID++;
		this.name = name;
		this.DOB = DOB;
		listOfFollowers = new ArrayList<>();
		listOfFollowings = new ArrayList<>();
	}
	
	/**
	 * auto ID.
	 * @param name
	 * @param dOB
	 * @param listOfFollowers
	 * @param listOfFollowings
	 */
	public Person(String name, LocalDate dOB, ArrayList<Person> listOfFollowers, ArrayList<Person> listOfFollowings) 
	{
		this.id = nextID++;
		this.name = name;
		DOB = dOB;
		this.listOfFollowers = listOfFollowers;
		this.listOfFollowings = listOfFollowings;
	}

	/**
	 * All fields are passed.
	 * @param id
	 * @param name
	 * @param dOB
	 * @param listOfFollowers
	 * @param listOfFollowings
	 */
	public Person(int id, String name, LocalDate dOB, ArrayList<Person> listOfFollowers,
			ArrayList<Person> listOfFollowings) {
		this.id = id;
		this.name = name;
		DOB = dOB;
		this.listOfFollowers = listOfFollowers;
		this.listOfFollowings = listOfFollowings;
	}
	
	/**
	 * Getters and Setters:
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDOB() {
		return DOB;
	}
	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}
	public ArrayList<Person> getListOfFollowers() {
		return listOfFollowers;
	}
	public void setListOfFollowers(ArrayList<Person> listOfFollowers) {
		this.listOfFollowers = listOfFollowers;
	}
	public ArrayList<Person> getListOfFollowings() {
		return listOfFollowings;
	}
	public void setListOfFollowings(ArrayList<Person> listOfFollowings) {
		this.listOfFollowings = listOfFollowings;
	}
	
	/**
	 * The next two methods are from the implemented interface.
	 * Ideally, if one of them is called, the other one should also be called for the other object to
	 * ensure the integrity of the relations.
	 * In my opinion, since both methods are public, it would have been better if we only have one 
	 * public method that makes sure that both operations are done. 
 	 */
	/**
	 * follow a person
	 */
	public void follow(Person toBeFollowed)
	{
		if(!this.listOfFollowings.contains(toBeFollowed))
			this.listOfFollowings.add(toBeFollowed);
	}
	/**
	 * get followed by a person
	 */
	public void followedBy(Person toBeFollower)
	{
		if(!this.listOfFollowers.contains(toBeFollower))
			this.listOfFollowers.add(toBeFollower);
	}
	/**
	 * unfollow a person
	 */
	public void unfollow(Person toBeUnFollowed)
	{
		this.listOfFollowings.remove(toBeUnFollowed);
	}
	/**
	 * remove a person from your followers
	 */
	public void removeFollower(Person toBeDeleted)
	{
		this.listOfFollowers.remove(toBeDeleted);
	}
	/**
	 * print the person briefly
	 */
	public void printBrief()
	{
		System.out.println("ID: " + this.id + ", name: " + this.name);
	}
	/**
	 * print the person fileds in details
	 */
	public void printPersonDetailed()
	{
		System.out.println("id: " + this.id);
		System.out.println("name: " + this.name);
		System.out.println("List of followers: ");
		for(Person p: this.listOfFollowers)
		{
			System.out.print("\t");
			p.printBrief();
		}
		System.out.println("List of followings: ");
		for(Person p: this.listOfFollowings)
		{
			System.out.print("\t");
			p.printBrief();
		}
	}
	/**
	 * print all the fields (super,child) in detail
	 */
	public abstract void printDetailed();
	@Override
	public String toString() {
		return "ID: " + this.id + ", name: " + this.name;
	}
}
