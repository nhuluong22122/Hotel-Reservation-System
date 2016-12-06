import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This panel displays 3 selections with 3 buttons: Reservation, View & Cancel,
 * Quit. This panel will display as a main menu after the user signed up or
 * signed in
 * 
 * @author nhuluong
 *
 */
public class ReservationOptions extends JPanel {
	private Reservations reservations;
	private final String FILE_NAME = "reservations.txt";

	/**
	 * Constructor for this panel that constructs 3 buttons with appropriate
	 * functions
	 * 
	 * @param hotel
	 *            the hotel model to switch frames
	 * @param gm
	 *            the guest model
	 * @param g
	 *            the current guest that logged in
	 */
	public ReservationOptions(HotelModel hotel, GuestModel gm, Guest g) {
		setLayout(null);
		reservations = gm.getReservations();
		JLabel welcome = new JLabel();
		welcome.setText("Welcome " + g.getUsername() + "! Please select the following options.");
		JButton m = new JButton("Make a Reservation");
		JButton vc = new JButton("View/Cancel a Reservation");
		JButton quit = new JButton("Quit");

		welcome.setBounds(150, 50, 400, 75);
		m.setBounds(50, 225, 250, 75);
		vc.setBounds(400, 225, 250, 75);
		quit.setBounds(200, 325, 275, 75);

		welcome.setFont(new Font("Arial", Font.PLAIN, 15));
		m.setFont(new Font("Arial", Font.PLAIN, 15));
		vc.setFont(new Font("Arial", Font.PLAIN, 15));
		quit.setFont(new Font("Arial", Font.PLAIN, 15));

		add(welcome);
		add(m);
		add(vc);
		add(quit);

		// Make a reservation
		m.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.signIn(g.getUserID());
				ReservationPanel rp = new ReservationPanel(hotel, gm);
				hotel.update(new UserPreferencePanel(hotel, gm, rp));
			}
		});

		// View and cancel
		vc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hotel.update(new ViewCancelView(hotel, gm));
			}
		});

		// Quit
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				serialize();
				hotel.update(new WelcomePanel(hotel));
			}
		});

	}

	/**
	 * Save the current data in a file or override it with newest data
	 */
	public void serialize() {
		try {
			FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(reservations);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
