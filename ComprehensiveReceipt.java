import java.util.ArrayList;
import java.util.Collections;

/**
 * The Comprehensive receipt that displays every valid reservations the current
 * user created.
 * 
 * @author nhuluong
 *
 */
public class ComprehensiveReceipt implements Receipt {
	private Reservations r;
	private Guest g;

	/**
	 * Constructor
	 * 
	 * @param r2
	 *            the current room data
	 */
	public ComprehensiveReceipt(Reservations r2) {
		r = r2;
		g = r.getCurrentGuest();
	}

	/**
	 * Display the user ID based on the current guest
	 * 
	 * @return the current user ID
	 */
	public String showUserID() {
		return String.valueOf(g.getUserID());
	}

	/**
	 * Display the user name based on the current guest
	 * 
	 * @return the current user name
	 */
	public String showUserName() {
		return g.getUsername();
	}

	/**
	 * Display all the reserved rooms made by the user & his or her old valid
	 * data
	 * 
	 * @return a formatted paragraph that contains all the information about the
	 *         user' reserved rooms
	 */
	public String showReservedRooms() {
		String allRooms = "";
		String currentDate = "";
		String previousDate = "";
		String currentType = "";
		String previousType = "";
		boolean first = true;
		if (r.getData().containsKey(g)) {
			ArrayList<Room> current = r.getData().get(g);
			Collections.sort(current, r.getComparator());
			for (Room r : current) {
				int sMonth = Integer.parseInt(String.valueOf(r.getStartDate().getMonth())) + 1;
				int eMonth = Integer.parseInt(String.valueOf(r.getStartDate().getMonth())) + 1;
				currentDate = String.valueOf(sMonth) + "/" + r.getStartDate().getDate() + "/"
						+ String.valueOf(r.getStartDate().getYear()).substring(1) + " - " + String.valueOf(eMonth) + "/"
						+ r.getEndDate().getDate() + "/" + String.valueOf(r.getStartDate().getYear()).substring(1);
				if (!currentDate.equals(previousDate)) {
					if (first) {
						allRooms += "Date:" + currentDate + "\n";
						first = false;
					} else {
						allRooms += "\n\nDate:" + currentDate + "\n";
						previousType = "";
					}
				}
				previousDate = currentDate;
				currentType = r.getRoomType();
				if (!currentType.equals(previousType)) {
					allRooms += "Room Type: " + r.getRoomType() + "\n";
					allRooms += "Room Number: " + r.getRoomNumber();
				} else {
					allRooms += ", " + r.getRoomNumber();
				}
				previousType = currentType;

			}
		}
		return allRooms;

	}

	/**
	 * Display the amount due based on all their valid reserved rooms
	 * 
	 * @return the total amount due from all their valid reservations
	 */
	public String showAmountDue() {
		int totalPrice = 0;
		if (r.getData().containsKey(g)) {
			ArrayList<Room> current = r.getData().get(g);
			for (Room r : current) {
				if (r.getRoomType().equalsIgnoreCase("luxury")) {
					totalPrice += 200;
				} else {
					totalPrice += 80;
				}

			}
		}
		return String.valueOf(totalPrice);
	}

}
