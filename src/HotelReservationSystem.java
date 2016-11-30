import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class HotelReservationSystem {
	
	public static void main(String[] args) {
		HotelModel hotel = new HotelModel(null);
		hotel.update(new WelcomePanel(hotel));
		HotelFrame frame = new HotelFrame(hotel);
		hotel.attach(frame);
	}
}
