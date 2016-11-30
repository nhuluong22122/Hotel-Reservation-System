import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReservationOptions extends JPanel {
	public ReservationOptions(HotelModel hotel, GuestModel gm, Guest g) {
		setLayout(null);

		JLabel welcome = new JLabel();
		welcome.setText("Welcome " + g.getUsername() + "! Please select the following options.");
		JButton m = new JButton("Make a Reservation");
		JButton vc = new JButton("View/Cancel a Reservation");

		welcome.setBounds(150, 50, 400, 75);
		m.setBounds(50, 225, 250, 75);
		vc.setBounds(400, 225, 250, 75);

		welcome.setFont(new Font("Arial", Font.PLAIN, 15));
		m.setFont(new Font("Arial", Font.PLAIN, 15));
		vc.setFont(new Font("Arial", Font.PLAIN, 15));
		
		add(welcome);
		add(m);
		add(vc);

		//Make a reservation
		m.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.signIn(g.getUserID());
				ReservationPanel rp = new ReservationPanel(hotel, gm);
				hotel.update(new UserPreferencePanel(hotel, gm, rp));
			}
		});
		
		
		//View and cancle
		vc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}
}
