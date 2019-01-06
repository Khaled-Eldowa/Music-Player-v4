package componentsV2;

import java.time.LocalDate;

/**
 * Class for public subs
 * @author khale
 *
 */
public class PublicSubscription extends Subscription{

	private int additionalSongLimit;

	/**
	 * Different constructors
	 */
	public PublicSubscription(String email) {
		super(1, email);
		this.additionalSongLimit = 0;
	}

	public PublicSubscription(LocalDate subscriptionDate, LocalDate expiryDate, String email) {
		super(1, subscriptionDate, expiryDate, email);
		this.additionalSongLimit = 0;
	}
	
	public PublicSubscription(int songLimit, LocalDate subscriptionDate, LocalDate expiryDate, String email,
			int additionalSongLimit) {
		super(songLimit, subscriptionDate, expiryDate, email);
		this.additionalSongLimit = additionalSongLimit;
	}

	public int getAdditionalSongLimit() {
		return additionalSongLimit;
	}

	public void setAdditionalSongLimit(int additionalSongLimit) {
		this.additionalSongLimit = additionalSongLimit;
	}
	
	/**
	 * The required method for increasing the song limits
	 */
	public void IncreaseSongLimits()
	{
		long subscriptionEpoch = this.getSubscriptionDate().toEpochDay();
		long currentEpoch = LocalDate.now().toEpochDay();
		long difference = currentEpoch - subscriptionEpoch;
		if(difference > 6*30)
		{
			additionalSongLimit += (int)((difference-6*30)/30);
		}
	}
	
	public String toString() {
		return "Subscription Type: Public.\nSubscription Expiry Date: " + this.getExpiryDate().toString();
	}
	
	public String getSubType()
	{
		return "Public";
	}
	
}
