import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel{

	public WelcomePanel(HotelModel hotel) {
		setLayout(null);

		JButton managerButton = new JButton("Manager");
		managerButton.setBounds(500, 200, 100, 50);
		managerButton.addActionListener(new 
				ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotel.update(new ManagerPanel(hotel));		//first idea
			}
		});

		JButton guestButton = new JButton("Guest");
		guestButton.setBounds(100,200,100,50);
		guestButton.addActionListener( new 
				ActionListener() {

					public void actionPerformed(ActionEvent e) {
						GuestModel gm = new GuestModel();
						
						hotel.update(new UserPanel(hotel, gm));
						
					}
				
		});

		add(managerButton);
		add(guestButton);
	}
}
