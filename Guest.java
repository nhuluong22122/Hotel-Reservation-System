import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Guest implements Comparable<Guest>, Serializable{
	//	private ArrayList<Room> reservedRooms;
	private int userID;
	private String username;
	private Date startDate;
	private Date endDate;

	public Guest(int ID, String user) {
		userID = ID;
		username = user;
	}

	//	public ArrayList<Room> getReservedRooms() {
	//		return reservedRooms;
	//	}

	public int getUserID() {
		return userID;
	}

	public String getUsername() {
		return username;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setUserID(int ID) {
		userID = ID;
	}
	
	public void setStartDate(Date sDate) {
		startDate = sDate;
	}
	
	public void setEndDate(Date eDate) {
		endDate = eDate;
	}

	//	public void addReservedRoom(Room room) {
	//		reservedRooms.add(room);
	//	}
	//	
	//	public void removeReservedRoom(Room room) {
	//		reservedRooms.remove(room);
	//	}

	public void setUsername(String user) {
		username = user;
	}

	public int compareTo(Guest g) {
		return username.compareTo(g.getUsername());
	}
}