package componentsV2;

import java.time.LocalDate;

/**
 * Class for student subs
 */
public class StudentSubscription extends Subscription{
	private int additionalSongLimit;
	private String schoolName;
	private int schoolID;
	private String schoolAddress;
	private int studentID;
	
	/**
	 * Different construcotrs
	 */
	public StudentSubscription(String email, String schoolName, int schoolID, String schoolAddress,
			int studentID) {
		super(2, email);
		this.additionalSongLimit = 0;
		this.schoolName = schoolName;
		this.schoolID = schoolID;
		this.schoolAddress = schoolAddress;
		this.studentID = studentID;
	}
	public StudentSubscription(LocalDate subscriptionDate, LocalDate expiryDate, String email, String schoolName,
			int schoolID, String schoolAddress, int studentID) {
		super(2, subscriptionDate, expiryDate, email);
		this.additionalSongLimit = 0;
		this.schoolName = schoolName;
		this.schoolID = schoolID;
		this.schoolAddress = schoolAddress;
		this.studentID = studentID;
	}
	
	public StudentSubscription(int songLimit, LocalDate subscriptionDate, String email, int additionalSongLimit,
			String schoolName, int schoolID, String schoolAddress, int studentID) {
		super(songLimit, subscriptionDate, email);
		this.additionalSongLimit = additionalSongLimit;
		this.schoolName = schoolName;
		this.schoolID = schoolID;
		this.schoolAddress = schoolAddress;
		this.studentID = studentID;
	}
	
	public int getAdditionalSongLimit() {
		return additionalSongLimit;
	}
	public void setAdditionalSongLimit(int additionalSongLimit) {
		this.additionalSongLimit = additionalSongLimit;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public int getSchoolID() {
		return schoolID;
	}
	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
	}
	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
	/**
	 * Custom increase song limits similar to public
	 */
	public void IncreaseSongLimits()
	{
		long subscriptionEpoch = this.getSubscriptionDate().toEpochDay();
		long currentEpoch = LocalDate.now().toEpochDay();
		long difference = currentEpoch - subscriptionEpoch;
		if(difference > 6*30)
		{
			additionalSongLimit += 2*(int)((difference-6*30)/30);
		}
	}
	
	public String toString() {
		return "Subscription Type: Student.\nSubscription Expiry Date: " + this.getExpiryDate().toString();
	}
	
	public String getSubType()
	{
		return "Student";
	}
}
