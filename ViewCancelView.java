import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The panel that allows user to view and cancel their reservations
 * 
 * @author nhuluong
 *
 */
public class ViewCancelView extends JPanel {
	/**
	 * Constructor that contains a text area and JComponents to remove a room
	 * and exit
	 * 
	 * @param hm
	 *            the hotel model
	 * @param gm
	 *            the guest model
	 */
	public ViewCancelView(HotelModel hm, GuestModel gm) {
		setLayout(null);
		JLabel instruction = new JLabel("Current reserved room");
		instruction.setBounds(150, 25, 400, 25);
		// <----- TextArea to display current rooms ----->

		final JTextArea textArea = new JTextArea(300, 400);
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(150, 50, 400, 250);
		
		ComprehensiveReceipt c = new ComprehensiveReceipt(gm.getReservations());
		textArea.setText(c.showReservedRooms());

		ChangeListener listener = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				textArea.setText(c.showReservedRooms());

			}
		};
		gm.addChangeListener(listener); // Update this textArea if anything
										// changes

		Reservations r = gm.getReservations();
		ArrayList<Room> list = r.getData().get(r.getCurrentGuest());// Get all
																	// the rooms
																	// of
																	// current
																	// guest
		// Create an array to store all room numbers
		String[] roomName = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			roomName[i] = String.valueOf(list.get(i).getRoomNumber()) + " ("
					+ String.valueOf(list.get(i).getStartDate()).substring(4, 10) + " "
					+ String.valueOf(list.get(i).getStartDate()).substring(24) + ")";
		}

		final JComboBox<String> selection = new JComboBox<String>(roomName);
		selection.setBounds(300, 325, 200, 50);
		selection.setFont(new Font("Arial", Font.PLAIN, 15));

		// Update the combo box
		ChangeListener selectionUpdate = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				selection.removeItemAt(selection.getSelectedIndex());
				selection.validate();
				selection.repaint();
			}
		};
		gm.addChangeListener(selectionUpdate);

		// Remove button
		JButton remove = new JButton("Remove room: ");
		remove.setBounds(150, 325, 150, 50);

		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (selection.getItemCount() > -1) {
						gm.cancelReservation(list.get(selection.getSelectedIndex()));
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "There are no more reservations to cancel.");
				}
			}

		});
		// This button directs the user to the main menu
		JButton done = new JButton("Done");
		done.setBounds(150, 400, 400, 50);
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hm.update(new ReservationOptions(hm, gm, gm.getReservations().getCurrentGuest()));
			}

		});

		add(selection);
		add(scroll);
		add(remove);
		add(done);
		add(instruction);

	}

}
