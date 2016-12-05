import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * The Guest class that holds information about a guest
 * 
 * @author nhuluong
 *
 */
public class Guest implements Comparable<Guest>, Serializable {
	private int userID;
	private String username;
	private Date startDate;
	private Date endDate;

	/**
	 * Constructor
	 * 
	 * @param ID
	 *            the id of a new Guest
	 * @param user
	 *            the user name of a new user
	 */
	public Guest(int ID, String user) {
		userID = ID;
		username = user;
	}

	/**
	 * Return the userID
	 * 
	 * @return the user's ID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Return the user's user name
	 * 
	 * @return user name
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Comparable method that allows the data to be sorted based on the guest's
	 * user name
	 */
	public int compareTo(Guest g) {
		return username.compareTo(g.getUsername());
	}
	/**
	 * Set the start date of the room to keep as user's info
	 * @param sDate
	 */
	public void setStartDate(Date sDate) {
		startDate = sDate;
	}
	/**
	 * Set the end date of the room to keep as user's info
	 * @param sDate
	 */
	public void setEndDate(Date eDate) {
		endDate = eDate;
	}
	/**
	 * Get the start date of the room to keep as user's info
	 * @param sDate the start date of the room
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Get the end date of the room to keep as user's info
	 * @param sDate the end date of the room
	 */
	public Date getEndDate() {
		return endDate;
	}
}