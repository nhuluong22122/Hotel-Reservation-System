import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserPreferencePanel extends JPanel{
	private Date checkIn;
	private Date checkOut;
	private GuestModel model;
	private ReservationPanel rPanel;

	public UserPreferencePanel(HotelModel hm, GuestModel gm, ReservationPanel panel) {
		rPanel = panel;
		model = gm;
		setLayout(null);

		// Components in userPreferencePanel
		JLabel formatWarning = new JLabel("Enter date in MM/DD/YY format:");
		formatWarning.setFont(new Font("Sans-serif", Font.PLAIN, 14));
		formatWarning.setBounds(200, 50, 300, 50);
		add(formatWarning);
		JLabel checkInLabel = new JLabel("Check in");
		checkInLabel.setBounds(200, 100, 100, 20);
		final JTextField checkInField = new JTextField();
		checkInField.setBounds(200, 120, 100, 50);
		checkInField.setFont(new Font("Sans-serif", Font.PLAIN, 14));
		checkInField.setHorizontalAlignment(JTextField.CENTER);


		JLabel checkOutLabel = new JLabel("Check out");
		checkOutLabel.setBounds(320,100,70,20);
		final JTextField checkOutField = new JTextField();
		checkOutField.setBounds(320,120,100,50);
		checkOutField.setFont(new Font("Sans-serif", Font.PLAIN, 14));
		checkOutField.setHorizontalAlignment(JTextField.CENTER);

		JLabel roomTypeLabel = new JLabel("Room type:");
		JButton luxuryRoomButton = new JButton("$200");
		JButton econRoomButton = new JButton("$80");

		roomTypeLabel.setBounds(200, 200, 80, 50);
		luxuryRoomButton.setBounds(280, 200, 80, 50);
		econRoomButton.setBounds(370, 200, 80, 50);

		luxuryRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					checkIn = new Date(checkInField.getText());
					checkOut = new Date(checkOutField.getText());
					rPanel.setStartDate(checkIn);
					rPanel.setEndDate(checkOut);
					rPanel.setRoomType("Luxury");
					model.updateData(checkIn, checkOut);
					//rPanel.updateData(model);
					hm.update(rPanel);
				
				}
				catch(NullPointerException n) {
					JOptionPane.showMessageDialog(null, "Please log in");
				}
				catch(IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, "Please enter a valid check-in and check-out date");
				}
			}
		});

		econRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					checkIn = new Date(checkInField.getText());
					checkOut = new Date(checkOutField.getText());
					rPanel.setStartDate(checkIn);
					rPanel.setEndDate(checkOut);
					rPanel.setRoomType("Economy");
					model.updateData(checkIn, checkOut);
					rPanel.updateData(model);
					hm.update(rPanel);
					
				}
				catch(NullPointerException n) {
					JOptionPane.showMessageDialog(null, "Please log in");
				}
				catch(IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, "Please enter a valid check-in and check-out date");
				}
			}
		});
		add(checkInLabel);
		add(checkInField);
		add(checkOutLabel);
		add(checkOutField);
		add(roomTypeLabel);
		add(luxuryRoomButton);
		add(econRoomButton);
	}
	
}