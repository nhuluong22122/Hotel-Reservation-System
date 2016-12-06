import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GuestModel{

	private ArrayList<ChangeListener> listeners;
	private Reservations re;

	public GuestModel(Reservations r) {
		re = r;
		//create new reservations
		listeners = new ArrayList<ChangeListener>();
		
	}

	/**
	 * Returns a formatted string of current available rooms of a room type
	 * @param type the room type requested
	 * @return s a string with data of current available rooms
	 */	
	public String getRooms(String type) {
		//call method
		return re.getRooms(type);
	}

	/**
	 * @param ID of guest
	 * @precondition: Guest ID is registered in the system
	 * @postcondition: Guest is logged in
	 */
	public void signIn(int ID) throws NullPointerException {
		//call method 
		re.signIn(ID);
		Guest currentGuest = re.getCurrentGuest();
		//call get current guest
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
		//call method
		return re.signUp(username);
	}

	/**
	 * @param g the guest
	 * @param r the room to cancel
	 * @throws Exception 
	 * @precondition: Guest has the room reserved
	 * @postcondition: Room is removed from Guest's arraylist in hotel
	 */
	public void cancelReservation(Room r) throws Exception {
		//call method
		re.cancelReservation(r);
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener c : listeners) {
			c.stateChanged(event);
		}
	}

	/**
	 * Assigns a guest to a specified room number until guest cancels the reservation
	 * @param roomNumber the number of the room
	 * @precondition: user is signed in and room number exists in the system
	 * @postcondition: Room is removed from available rooms and added to the map of occupied rooms
	 */
	public void addRoom(int roomNumber) {

		//call method
		re.addRoom(roomNumber);
		// Notify all views
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener c : listeners) {
			c.stateChanged(event);
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
		//call method
		re.addRoom(checkIn, checkOut, roomType);
			// Notify all views
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener c : listeners) {
				c.stateChanged(event);
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
		//call method
		re.updateData(s, e);
	}

	public void update(){
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener c : listeners) {
			c.stateChanged(event);
		}
	}


	public Reservations getReservations(){
		return re;
	}

}