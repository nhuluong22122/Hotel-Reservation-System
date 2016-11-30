import java.util.Date;

public class Room {
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
		System.out.println("Start " + r1.getStartDate() + " End" + r1.getEndDate()); 
		System.out.println("Start " + start + " End" + end); 
		if (r1.getStartDate().equals(start) && r1.getEndDate().equals(end)) {
			System.out.println("1");
			return true;
		}
		if (r1.getStartDate().compareTo(end) <= 0 && start.compareTo(r1.getEndDate()) <= 0) {
			System.out.println("2");
			return true;
		}
		return false;
	}
	
	public String toString() {
		String s = isCurrent + " " + roomType + " " + roomNumber + " " + startDate + " " + endDate;
		return s;
	}
}