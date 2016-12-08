import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author MichelleSong
 * @author NhuLuong
 *
 */
public class Room implements Serializable {
	private boolean isCurrent;
	private String roomType;
	private int roomNumber;
	private Date startDate;
	private Date endDate;
	private Guest guest;

	/**
	 * Return the current status of the room
	 *
	 * @return the current status of the room
	 */
	public boolean getCurrentStatus() {
		return isCurrent;
	}

	/**
	 * Return the room type
	 *
	 * @return the room type
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * Return the room number
	 *
	 * @return the room number
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * Return the start date
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Return the end date
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Set the current status of the room
	 *
	 * @param status
	 *            the new status of the room
	 */
	public void setCurrentStatus(boolean status) {
		isCurrent = status;
	}

	/**
	 * Set the room type
	 *
	 * @param rType
	 *            the new room type
	 */
	public void setRoomType(String rType) {
		roomType = rType;
	}

	/**
	 * Set the room number
	 * @param rNumber the new room number
	 */
	public void setRoomNumber(int rNumber) {
		roomNumber = rNumber;
	}
	/**
	 * Set the start date of the room
	 * @param sDate the new start date
	 */
	public void setStartDate(Date sDate) {
		startDate = sDate;
	}
	/**
	 * Set the new end date of the room
	 * @param eDate the new end date
	 */
	public void setEndDate(Date eDate) {
		endDate = eDate;
	}
	/**
	 * Set the guest that reserves this room
	 * @param g the guest that reserves this room
	 */
	public void setGuest(Guest g) {
		guest = g;
	}
	/**
	 * Get the guest that reserves this room
	 * @return the guest that reserves this room
	 */
	public Guest getGuest() {
		return guest;
	}
	/**
	 * Check if the room's time conflicts with other rooms
	 * @param start the day user wants to check in
	 * @param end the day user wants to check out
	 * @return true if the room is available
	 */
	public boolean isClash(Date start, Date end) {
		Room r1 = this;
		if (r1.getStartDate().equals(start) && r1.getEndDate().equals(end)) {
			return true;
		}
		if (r1.getStartDate().compareTo(end) <= 0 && start.compareTo(r1.getEndDate()) <= 0) {
			return true;
		}
		return false;
	}
	/**
	 * Check if this room equals the other room
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (o.getClass() != getClass())
			return false;
		Room r = (Room) o;

		return r.getCurrentStatus() == isCurrent && r.getStartDate().compareTo(startDate) == 0
				&& r.getEndDate().compareTo(endDate) == 0 && r.getRoomNumber() == roomNumber
				&& r.getRoomType().compareTo(roomType) == 0;
	}
	/**
	 * Print the room information in a string format
	 */
	public String toString() {
		String s = isCurrent + " " + roomType + " " + roomNumber + " " + startDate + " " + endDate;
		return s;
	}
	/**
	 * Calculate the price of this room based on the number of reserved days
	 * @return the price of the room based on the	 number of reserved days
	 */
	public int getPrice() {
		int difference = endDate.getDate() - startDate.getDate();
		if (roomType.contains("Luxury")) {
			return difference * 200;
		} else {
			return difference * 80;
		}
	}
}
