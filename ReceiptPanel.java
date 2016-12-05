import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Reciept Panel that displays the reciept based on users' preference
 * 
 * @author nhuluong
 *
 */
public class ReceiptPanel extends JPanel {
	private HotelModel hotel;
	private Reservations reservations;
	private final String FILE_NAME = "reservations.txt";

	/**
	 * Constructor of the panel that contains other JComponents
	 * 
	 * @param r
	 *            the reservation data
	 * @param hm
	 *            the hotel model
	 * @param rm
	 *            the guest model
	 * @param receiptType
	 *            the receipt type based on user' selection from the previous
	 *            frame
	 */
	public ReceiptPanel(Reservations r, HotelModel hm, GuestModel rm, Receipt receiptType) {
		reservations = r;
		hotel = hm;
		setLayout(null);
		// Label
		JLabel test = new JLabel("Print receipt");
		test.setBounds(300, 50, 100, 50);
		// Quit button that save latest data and go back to the user panel
		JButton quitButton = new JButton("Quit");
		quitButton.setBounds(300, 400, 100, 50);
		quitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				serialize();
				hm.update(new UserPanel(hm, rm));
			}
		});
		// Where the receipt will be displayed
		JTextArea textArea = new JTextArea(300, 400);
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial", Font.PLAIN, 13));
		textArea.setText(r.formatReceipt(receiptType));
		JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBounds(200, 100, 300, 250);

		add(test);
		add(quitButton);
		add(sp);
	}

	/**
	 * Saving all reservations to guestmodel.txt.
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
