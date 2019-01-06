package componentsV2;

import java.time.LocalDate;

/**
 * Class for premium subs
 * @author khale
 *
 */
public class PremiumSubscription extends Subscription{
	float paymentTotal;
	long creditCardNumber;
	String workOrganization;
	String workAddress;
	static final float monthlyfees = (float)10.0;
	
	/**
	 * Different Constructors
	 */
	public PremiumSubscription(String email, long creditCardNumber, String workOrganization, String workAddress) {
		super(1000, email);
		this.paymentTotal = monthlyfees;
		this.creditCardNumber = creditCardNumber;
		this.workOrganization = workOrganization;
		this.workAddress = workAddress;
	}
	public PremiumSubscription(String email, float paymentTotal, long creditCardNumber, String workOrganization,
			String workAddress) {
		super(1000, email);
		this.paymentTotal = paymentTotal;
		this.creditCardNumber = creditCardNumber;
		this.workOrganization = workOrganization;
		this.workAddress = workAddress;
	}
	public PremiumSubscription(LocalDate subscriptionDate, LocalDate expiryDate, String email, float paymentTotal,
			long creditCardNumber, String workOrganization, String workAddress) {
		super(1000, subscriptionDate, expiryDate, email);
		this.paymentTotal = paymentTotal;
		this.creditCardNumber = creditCardNumber;
		this.workOrganization = workOrganization;
		this.workAddress = workAddress;
	}
	
	public float getPaymentTotal() {
		return paymentTotal;
	}
	public void setPaymentTotal(float paymentTotal) {
		this.paymentTotal = paymentTotal;
	}
	public long getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getWorkOrganization() {
		return workOrganization;
	}
	public void setWorkOrganization(String workOrganization) {
		this.workOrganization = workOrganization;
	}
	public String getWorkAddress() {
		return workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	
	public String toString() {
		return "Subscription Type: Premium.\nSubscription Expiry Date: " + this.getExpiryDate().toString();
	}
	
	public String getSubType()
	{
		return "Premium";
	}
	
}
