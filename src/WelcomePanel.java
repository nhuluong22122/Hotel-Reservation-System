import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel{
	
	public WelcomePanel(HotelComponents hotel) {
		setLayout(null);
		
		JButton managerButton = new JButton("Manager");
		managerButton.setBounds(300, 200, 100, 50);
		managerButton.addActionListener(new 
				ActionListener() {
					public void actionPerformed(ActionEvent e) {
						hotel.update(0, new ManagerPanel());
					}
		});

		JButton guestButton = new JButton("Guest");
		guestButton.setBounds(100,200,100,50);
		
		add(managerButton);
		add(guestButton);
	}
}
