import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignInPanel extends JPanel {
	public SignInPanel(HotelModel hotel, GuestModel guestdata) {
		setLayout(null);

		JLabel id = new JLabel("ID:");
		JTextField idinput = new JTextField();
		JButton createAccount = new JButton("Log In");

		id.setBounds(150, 75, 100, 100);
		idinput.setBounds(200, 75, 300, 100);
		createAccount.setBounds(250, 200, 200, 75);

		id.setFont(new Font("Arial", Font.PLAIN, 20));
		idinput.setFont(new Font("Arial", Font.PLAIN, 20));
		createAccount.setFont(new Font("Arial", Font.PLAIN, 20));

		add(id);
		add(idinput);
		add(createAccount);

		createAccount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idinput.getText();
				if (id.matches("[0-9]+") && id.length() > 0) {
					int idnum = Integer.parseInt(id);
					guestdata.signIn(idnum);
				} else {
					JOptionPane.showMessageDialog(null, "Please enter numbers for id.");
				}

			}

		});

	}
}
