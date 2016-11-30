import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GuestModel {
	private HashMap<Guest, ArrayList<Room>> hotel;
	private ArrayList<Room> availableRooms;
	private ArrayList<ChangeListener> listeners;
	private int IDCounter;
	private int currentID;
	private Guest currentGuest;

	public GuestModel() {
		hotel = new HashMap<Guest, ArrayList<Room>>();
		listeners = new ArrayList<ChangeListener>();
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

	/**
	 * Returns a formatted string of current available rooms of a room type
	 * @param type the room type requested
	 * @return s a string with data of current available rooms
	 */	
	public String getRooms(String type) {
		String s = "";
		for (Room r : availableRooms) {
			if (r.getRoomType().equals(type)) {
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
		if (currentGuest == null) {
			JOptionPane.showMessageDialog(null, "This ID is not valid. Please enter another one.");
			throw new NullPointerException();
		}
		System.out.println(currentGuest.getUserID());
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
		System.out.println("Success! Your id is " + g.getUserID());
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
			throw new Exception(currentGuest.getUsername() + " did not reserve the room " + r.getRoomNumber());
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
		//	if (currentGuest == null) {
		//		throw new NullPointerException();
		//	}
		
		//	Room r = availableRooms.get(roomNumber-1);
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

			// Notify all views
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener c : listeners) {
				c.stateChanged(event);
			}
			
			System.out.println(room.toString());
		}
		catch (NullPointerException e) {
			System.out.println("Room " + roomNumber + " is not available");
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
		//		if (currentGuest == null) {
		//			throw new NullPointerException();
		//		}

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
			
			// Notify all views
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener c : listeners) {
				c.stateChanged(event);
			}
		}
		catch (NullPointerException e) {
			System.out.println("No available rooms");
		}
	}

	/**
	 * Adds a ChangeListener to the listeners ArrayList
	 * @param listener to be added
	 * @postcondition: listener is added to the listeners ArrayList
	 */
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
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
		if (s.compareTo(e) > 0) {
			throw new IllegalArgumentException();
		}
		
		// Update guest information
		currentGuest.setStartDate(s);
		currentGuest.setEndDate(e);

		// Update availability information to display for current date
		ArrayList<Room> newAvailability = new ArrayList<Room>(); 
		//
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
				if (r.isClash(s,e)) {
					newAvailability.remove(r);
				}
			}
		}
		availableRooms = newAvailability;
	}
	
	public void update(){
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener c : listeners) {
			c.stateChanged(event);
		}
	}


}