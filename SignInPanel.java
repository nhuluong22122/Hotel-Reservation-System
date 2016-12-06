import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * The Sign In Panel that allows users to enter their id to log in
 * @author nhuluong
 *
 */
public class SignInPanel extends JPanel {
	/**
	 * Construct a panel with a JTextField to entire ID and a JButton to check ID and proceed to the next frame
	 * @param hotel the hotel model to switch frames
	 * @param guestdata the guest model to manipulate and check ID
	 */
	public SignInPanel(HotelModel hotel, GuestModel guestdata) {
		setLayout(null);

		JLabel id = new JLabel("ID:");
		JTextField idinput = new JTextField();
		JButton login = new JButton("Log In");

		id.setBounds(150, 75, 100, 100);
		idinput.setBounds(200, 75, 300, 100);
		login.setBounds(250, 200, 200, 75);

		id.setFont(new Font("Arial", Font.PLAIN, 20));
		idinput.setFont(new Font("Arial", Font.PLAIN, 20));
		login.setFont(new Font("Arial", Font.PLAIN, 20));

		add(id);
		add(idinput);
		add(login);

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idinput.getText();
				if (id.matches("[0-9]+") && id.length() > 0) {
					int idnum = Integer.parseInt(id);
					guestdata.signIn(idnum);
					Guest g = guestdata.getReservations().getCurrentGuest();
					guestdata.getReservations().resetTransaction();
					hotel.update(new ReservationOptions(hotel, guestdata, g));
				} else {
					JOptionPane.showMessageDialog(null, "Please enter numbers for id.");
				}

			}

		});

	}
}
