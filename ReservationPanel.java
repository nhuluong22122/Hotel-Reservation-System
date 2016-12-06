import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.Serializable;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * ReservationPanel holds a view of the available rooms and JComponents used to modify the existing data in model (JButton)
 * Responsible for creating the look and feel of the room availability view
 *
 */
public class ReservationPanel extends JPanel implements Serializable{
	private Date startDate;
	private Date endDate;
	private JLabel availabilityLabel;
	private JTextArea availabilityDisplay;
	private GuestModel guestModel;
	private String roomType;
	private HotelModel hotel;
	
	public ReservationPanel(HotelModel hm, GuestModel gm) {
		hotel = hm;
		guestModel = gm;
		ChangeListener listener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateAvailability(roomType);
			}
		};
		hm.attach(listener);
		guestModel.addChangeListener(listener);

		setLayout(null);
		availabilityLabel = new JLabel();
		availabilityLabel.setBounds(10, 10, 300, 50);
		availabilityDisplay = new JTextArea(20,10);
		availabilityDisplay.setBounds(25, 50, 200, 300);
		JScrollPane pane = new JScrollPane(availabilityDisplay);
		updateAvailability("");
		pane.setBounds(25, 50, 200, 300);

		add(pane);

		JLabel requestLabel = new JLabel("Enter room number to reserve:");
		requestLabel.setBounds(300, 70, 300, 50);
		requestLabel.setFont(new Font("Sans-serif", Font.PLAIN, 14));
		JTextField roomTextField = new JTextField("Room number");
		roomTextField.setBounds(300, 120, 100, 50);
		roomTextField.setFont(new Font("Sans-serif", Font.PLAIN, 12));
		roomTextField.setHorizontalAlignment(JTextField.CENTER);
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBounds(400, 120, 100, 50);
		JButton loopButton = new JButton("More reservations?");
		loopButton.setBounds(300, 200, 170, 50);
		JButton doneButton = new JButton("Done");
		doneButton.setBounds(500, 200, 100, 50);

		roomTextField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent event) {
				if (roomTextField.getText().equals("Room number"))
					roomTextField.setText("");
			}
			public void focusLost(FocusEvent event) {
				if (roomTextField.getText().isEmpty()) {
					roomTextField.setText("Room number");
				}
			}
		});
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int roomNumber = Integer.parseInt(roomTextField.getText());
				if (roomNumber <= 10 && roomType.equals("Luxury")) {
					JOptionPane.showMessageDialog(null, "Please select one of the available rooms shown.");
				}
				else if (roomNumber > 10 && roomType.equals("Economy")) {
					JOptionPane.showMessageDialog(null, "Please select one of the available rooms shown.");
				}
				else {
					try {
						gm.addRoom(roomNumber);
					}
					catch(IndexOutOfBoundsException oob) {
						JOptionPane.showMessageDialog(null, "Please select one of the available rooms shown.");
					}
				}
			}
		});
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Receipt window *** todo
				hotel.update(new ReceiptOptions(guestModel.getReservations(), hotel, guestModel));
			}
		});
		loopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hm.update(new UserPreferencePanel(hm, guestModel, new ReservationPanel(hm,guestModel)));
			}
		});

		add(requestLabel);
		add(roomTextField);
		add(confirmButton);
		add(loopButton);
		add(doneButton);
	}

	/**
	 * Update the room availability view
	 * @param type the room type selected
	 */
	public void updateAvailability(String type) {
		String title = "";
		if (startDate != null  & endDate != null) {
			title = "Available Rooms " + formatDate(startDate) + " - " + formatDate(endDate);
		}
		availabilityDisplay.setText(guestModel.getRooms(type));

		availabilityLabel.setText(title);
		add(availabilityLabel);
	}

	/**
	 * Overwrites saved data of the start date
	 * @param sDate the new date
	 * @throws Exception 
	 * @precondition: sDate is not null
	 * @postcondition: the start date is set to sDate
	 */
	public void setStartDate(Date sDate) throws IllegalArgumentException {
		if (sDate == null) {
			throw new IllegalArgumentException();
		}
		startDate = sDate;
		updateAvailability("");
	}

	/**
	 * Overwrites saved data of the end date
	 * @param eDate the end date
	 * @precondition: eDate is not null
	 * @postcondition: the end date is set to eDate
	 */
	public void setEndDate(Date eDate) throws IllegalArgumentException {
		if (eDate == null) {
			throw new IllegalArgumentException();
		}
		endDate = eDate;
		updateAvailability("");
	}

	/**
	 * Overwrites saved data of the room type selected
	 * @param type the new room type
	 * @precondition: type String is not null
	 * @postcondition: roomType is set to the new type
	 */
	public void setRoomType(String type) throws IllegalArgumentException {
		if (type == null) {
			throw new IllegalArgumentException();
		}
		roomType = type;
	}

	/**
	 * Returns a string in MM/DD/YY format from the date of the event
	 * Example: Date date of event becomes 10/02/2016 (mm/dd/yy)
	 * @return a String in MM/DD/YY format
	 */
	public String formatDate(Date d) {
		int month = d.getMonth() + 1;
		int dayOfMonth = d.getDate();
		int year = d.getYear() + 1900;
		String y = "" + year;
		String m = "" + month;
		String dom = "" + dayOfMonth;
		if (month < 10) {
			m = "0" + m;
		}
		if (dayOfMonth < 10) {
			dom = "0" + dom;
		}
		return m + "/" + dom + "/" + y.substring(2);
	}
	
	public void updateData(GuestModel gm){
		guestModel = gm;
		
		
	}
}