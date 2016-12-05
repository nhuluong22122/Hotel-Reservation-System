import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public class Room implements Serializable{
	private boolean isCurrent;
	private String roomType;
	private int roomNumber;
	private Date startDate;
	private Date endDate;
	
	public boolean getCurrentStatus(){
		return isCurrent;
	}
	
	public String getRoomType() {
		return roomType;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setCurrentStatus(boolean status){
		isCurrent = status;
	}
	
	public void setRoomType(String rType) {
		roomType = rType;
	}
	
	public void setRoomNumber(int rNumber) {
		roomNumber = rNumber;
	}
	
	public void setStartDate(Date sDate) {
		startDate = sDate;
	}
	
	public void setEndDate(Date eDate) {
		endDate = eDate;
	}
	
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
	
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (o.getClass() != getClass()) return false;
		Room r = (Room)o;

		return r.getCurrentStatus() == isCurrent && r.getStartDate().compareTo(startDate) == 0 
				&& r.getEndDate().compareTo(endDate) == 0 && r.getRoomNumber() == roomNumber && r.getRoomType().compareTo(roomType) == 0;
	}
	
	public String toString() {
		String s = isCurrent + " " + roomType + " " + roomNumber + " " + startDate + " " + endDate;
		return s;
	}
}