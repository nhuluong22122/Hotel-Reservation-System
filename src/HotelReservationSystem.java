import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class HotelReservationSystem {
	
	public static void main(String[] args) {
		ArrayList<JComponent> a = new ArrayList<>();
		HotelComponents hotel = new HotelComponents(a);
		a.add(new WelcomePanel(hotel));
		
		HotelFrame frame = new HotelFrame(hotel);
		hotel.attach(frame);
	}
	
	
}
