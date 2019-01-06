package componentsV2;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Abstract class for subscriptions
 */
public abstract class Subscription implements Serializable{

	private int songLimit;
	private LocalDate subscriptionDate;
	private LocalDate expiryDate;
	private String email;
	
	/**
	 * 1 month sub now
	 * @param songLimit
	 * @param email
	 */
	public Subscription(int songLimit, String email)
	{
		this.songLimit = songLimit;
		this.subscriptionDate = LocalDate.now();
		this.expiryDate = this.subscriptionDate.plusDays(30);
		this.email = email;
	}
	
	/**
	 * 1 month sub custom
	 * @param songLimit
	 * @param subscriptionDate
	 * @param email
	 */
	public Subscription(int songLimit, LocalDate subscriptionDate, String email)
	{
		this.songLimit = songLimit;
		this.subscriptionDate = subscriptionDate;
		this.expiryDate = this.subscriptionDate.plusDays(30);
		this.email = email;
	}
	
	/**
	 * Full fields constructor
	 * @param songLimit
	 * @param subscriptionDate
	 * @param expiryDate
	 * @param email
	 */
	public Subscription(int songLimit, LocalDate subscriptionDate, LocalDate expiryDate, String email)
	{
		this.songLimit = songLimit;
		this.subscriptionDate = subscriptionDate;
		this.expiryDate = expiryDate;
		this.email = email;
	}

	public int getSongLimit() {
		return songLimit;
	}

	public void setSongLimit(int songLimit) {
		this.songLimit = songLimit;
	}

	public LocalDate getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(LocalDate subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void renewSubscription()
	{
		this.expiryDate = this.subscriptionDate.plusDays(30);
	}
	
	public abstract String toString();
	public abstract String getSubType();
}
