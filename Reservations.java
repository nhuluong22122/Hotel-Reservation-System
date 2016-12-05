import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Reservations implements Serializable{
	private HashMap<Guest, ArrayList<Room>> hotel;
	private ArrayList<Room> availableRooms;
	private int IDCounter;
	private int currentID;
	private Guest currentGuest;

	public Reservations() {
		hotel = new HashMap<Guest, ArrayList<Room>>();
		availableRooms = new ArrayList<Room>();
		IDCounter = 0;

		// Initialize the availableRooms arrayList
		for (int roomNum = 1; roomNum <= 20; roomNum++) {
			Room r = new Room();
			r.setRoomNumber(roomNum);
			r.setCurrentStatus(false);
			if (roomNum <= 10) {
				r.setRoomType("Economy");
			}
			else if (roomNum > 10) {
				r.setRoomType("Luxury");
			}
			availableRooms.add(r);
		}
	}

	public int getSize() {
		return hotel.size();
	}
	
//	public void setData(HashMap<Guest, ArrayList<Room>> data) {
//		hotel = data;
//	}
//	
//	public void setAvailableRooms(ArrayList<Room> list) {
//		availableRooms = list;
//	}
//	
//	public HashMap<Guest,ArrayList<Room>> getData() {
//		return hotel;
//	}
//	
//	public ArrayList<Room> getList() {
//		return availableRooms;
//	}
	
	/**
	 * Returns a formatted string of current available rooms of a room type
	 * @param type the room type requested
	 * @return s a string with data of current available rooms
	 */	
	public String getRooms(String type) {
		String s = "";
		for (Room r : availableRooms) {
			if (r.getRoomType().equals(type) && r.getRoomNumber() != 0) {
				s += r.getRoomNumber() + "\n";
			}
		}
		return s;
	}

	/**
	 * @param ID of guest
	 * @precondition: Guest ID is registered in the system
	 * @postcondition: Guest is logged in
	 */
	public void signIn(int ID) throws NullPointerException {
		for (Guest g : hotel.keySet()) {
			if (g.getUserID() == ID) {
				currentGuest = g;
				currentID = ID;
				break;
			}
		}

	}

	/**
	 * @param username of the guest
	 * @precondition: Guest signing up has unique username and ID # 
	 * @precondition: Guest is added to the hotel
	 */
	public Guest signUp(String username) {
		// Add guest to hotel
		Guest g = new Guest(IDCounter, username);
		IDCounter++;
		ArrayList<Room> roomList = new ArrayList<Room>();
		hotel.put(g, roomList); 
		JOptionPane.showMessageDialog(null, "Success! Your id is " + g.getUserID());
		return g;
	}

	/**
	 * @param g the guest
	 * @param r the room to cancel
	 * @throws Exception 
	 * @precondition: Guest has the room reserved
	 * @postcondition: Room is removed from Guest's arraylist in hotel
	 */
	public void cancelReservation(Room r) throws Exception {
		if (!hotel.get(currentGuest).contains(r)) {
			String message = currentGuest.getUsername() + " did not reserve the room " + r.getRoomNumber();
			JOptionPane.showMessageDialog(null, message);
			throw new Exception(message);
		}
		hotel.get(currentGuest).remove(r); 
	}
	
	/**
	 * Assigns a guest to a specified room number until guest cancels the reservation
	 * @param roomNumber the number of the room
	 * @precondition: user is signed in and room number exists in the system
	 * @postcondition: Room is removed from available rooms and added to the map of occupied rooms
	 */
	public void addRoom(int roomNumber) {
		Room room = null;

		// Locate room in data structure
		for (Room r : availableRooms) {
			if (r.getRoomNumber() == roomNumber) {
				room = r;
			}
		}

		try {
			// Set room information
			room.setCurrentStatus(true);
			room.setStartDate(currentGuest.getStartDate());
			room.setEndDate(currentGuest.getEndDate());

			// Add guest & room to data structure
			if (hotel.containsKey(currentGuest)) {
				hotel.get(currentGuest).add(room);
			}
			else {
				ArrayList<Room> guestRooms = new ArrayList<Room>();
				hotel.put(currentGuest, guestRooms);
			}

			availableRooms.remove(room);
		}
		catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Room " + roomNumber + " is not available");
		}
	}

	/**
	 * Assigns a guest to a room until guest cancels the reservation
	 * @param checkIn the check-in date
	 * @param checkOut the check-out date
	 * @param roomType the type of the room 
	 * @precondition: user is signed in
	 * @postcondition: Room is removed from available rooms and added to the map of occupied rooms
	 */
	public void addRoom(String checkIn, String checkOut, String roomType) throws IllegalArgumentException, NullPointerException {
		Room room = null;

		// Locate room in data structure
		if (roomType.equals("Economy")) {
			for (int roomNum = 0; roomNum < 10; roomNum++) {
				if (!availableRooms.get(roomNum).getCurrentStatus()) {
					room = availableRooms.get(roomNum);
					availableRooms.remove(roomNum);
					break;
				}
			}
		}
		else if (roomType.equals("Luxury")) {
			for (int roomNum = 10; roomNum < 20; roomNum++) {
				if (!availableRooms.get(roomNum).getCurrentStatus()) {
					room = availableRooms.get(roomNum);
					availableRooms.remove(roomNum);
					break;
				}
			}
		}

		try {
			// Set room information
			room.setStartDate(currentGuest.getStartDate());
			room.setEndDate(currentGuest.getEndDate());
			room.setCurrentStatus(true);

			// Add guest & room to data structure
			if (hotel.containsKey(currentGuest)) {
				hotel.get(currentGuest).add(room);
			}
			else {
				ArrayList<Room> guestRooms = new ArrayList<Room>();
				hotel.put(currentGuest, guestRooms);
			}
		}
		catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No rooms are available");
		}
	}


	/**
	 * Updates guest data and room availability after user logs in and selects preferences
	 * @param s the start date
	 * @param e the end date
	 * @precondition: date is valid, meaning, end date does not precede start date
	 * @postcondition: guest data and room availability is updated
	 */
	public void updateData(Date s, Date e) {
		// Check if date is valid
		Date current = new Date();
		current.setMinutes(0);
		current.setHours(0);
		current.setSeconds(0);
		s.setMinutes(0);
		s.setHours(0);
		s.setSeconds(0);
	
		int timeDifference = (int) ((s.getTime()-current.getTime())/(1000*60*60*24));
		int dayDifference = (int) ((e.getTime()-s.getTime())/(1000*60*60*24));
		
		if (s.compareTo(e) > 0 || timeDifference < 0 || dayDifference > 60) {
			throw new IllegalArgumentException();
		}

		// Update guest information
		currentGuest.setStartDate(s);
		currentGuest.setEndDate(e);


		// Update availability information to display for current date
		ArrayList<Room> newAvailability = new ArrayList<Room>(); 
		for (int roomNum = 1; roomNum <= 20; roomNum++) {
			Room r = new Room();
			r.setRoomNumber(roomNum);
			r.setCurrentStatus(false);
			if (roomNum <= 10) {
				r.setRoomType("Economy");
			}
			else if (roomNum > 10) {
				r.setRoomType("Luxury");
			}
			newAvailability.add(r);
		}
		for (Guest g : hotel.keySet()) {
			for (Room r : hotel.get(g)) {
				Room ro = newAvailability.get(r.getRoomNumber()-1);
				ro.setStartDate(r.getStartDate());
				ro.setEndDate(r.getEndDate());
				ro.setCurrentStatus(true);
				if (r.isClash(s,e)) {
					ro.setRoomNumber(0);
				}
			}
		}
		availableRooms = newAvailability;
	}
	
	public Guest getCurrentGuest() {
		return currentGuest;
	}
	
}
