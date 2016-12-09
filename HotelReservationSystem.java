/**
 * Hotel Reservation System Project.
 * @author DuocNguyen
 * @author NhuLuong
 * @author MichelleSong
 * @version 1.0
 */
public class HotelReservationSystem {
	
	public static void main(String[] args) {
		HotelModel hotel = new HotelModel(null);
		hotel.update(new WelcomePanel(hotel));
		HotelFrame frame = new HotelFrame(hotel);
		hotel.attach(frame);
	}
}
