/**
 * The Receipt interface to use for polymorphism in Strategy pattern. This
 * receipt contains a user's id, user name, reservations and total amount due.
 * 
 * @author nhuluong
 *
 */
public interface Receipt {
	String showUserID();

	String showUserName();

	String showReservedRooms();

	String showAmountDue();
}
