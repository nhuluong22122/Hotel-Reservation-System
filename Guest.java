import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Guest stores user data such as id, username, check in date, and check out date
 * @author Michelle Song
 *
 */

public class Guest implements Comparable<Guest>, Serializable{
    private int userID;
    private String username;
    private Date startDate;
    private Date endDate;
    
    //testing purposes.
    public Guest(int ID, String user) {
        userID = ID;
        username = user;
    }
    
	/**
	 * Returns the guest user's ID
	 * @return userID of the guest
	 */
    public int getUserID() {
        return userID;
    }
    
	/**
	 * Returns the guest's username
	 * @return the username of the guest
	 */
    public String getUsername() {
        return username;
    }
    
    /**
	 * Returns the check in date of the guest
	 * @return the check in date of the guest
	 */
    public Date getStartDate() {
        return startDate;
    }
    
	/**
	 * Returns the check out date of the guest
	 * @return the check out date of the guest
	 */
    public Date getEndDate() {
        return endDate;
    }
    
	/**
	 * Sets the user ID of the guest
	 * @param ID the new user ID
	 * @postcondition: user ID of the guest is set to the new user ID
	 */
    public void setUserID(int ID) {
        userID = ID;
    }
    
	/**
	 * Sets the start date of the guest
	 * @param sDate the new start date
	 * @postcondition: start date of the guest is set to the new start date
	 */
    public void setStartDate(Date sDate) {
        startDate = sDate;
    }
    
	/**
	 * Sets the end date of the guest
	 * @param eDate the new end date
	 * @postcondition: end date of the guest is set to the new end date
	 */
    public void setEndDate(Date eDate) {
        endDate = eDate;
    }
    
	/**
	 * Sets the username of the guest
	 * @param user the new username
	 * @postcondition: username of the guest is set to the new username
	 */
    public void setUsername(String user) {
        username = user;
    }
    
	/**
	 * Overrides compareTo method, comparing guest to another by username
	 * @param g the guest to compare to
	 * @returns -1, 0, 1, for less than, equal to, and greater than, respectively
	 */
    public int compareTo(Guest g) {
        return username.compareTo(g.getUsername());
    }
    
}