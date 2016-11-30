import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUpPanel extends JPanel {
	public SignUpPanel(HotelModel hotel, GuestModel guestData) {
		setLayout(null);

		JLabel username = new JLabel("Username:");
		JTextField usernameinput = new JTextField();
		JButton createAccount = new JButton("Create an account");

		username.setBounds(100, 125, 150, 100);
		usernameinput.setBounds(200, 125, 300, 100);
		createAccount.setBounds(250, 250, 200, 75);

		username.setFont(new Font("Arial", Font.PLAIN, 20));
		usernameinput.setFont(new Font("Arial", Font.PLAIN, 20));
		createAccount.setFont(new Font("Arial", Font.PLAIN, 20));

		add(username);
		add(usernameinput);
		add(createAccount);

		createAccount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameinput.getText();
				Guest g = guestData.signUp(username);
				guestData.signIn(g.getUserID());
				hotel.update(new ReservationOptions(hotel, guestData, g));

			}

		});
	}
}
