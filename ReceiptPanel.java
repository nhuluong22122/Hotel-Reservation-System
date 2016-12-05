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

public class ReceiptPanel extends JPanel{
	//**********Nhu you can continue here.
	private HotelModel hotel;
	private Reservations reservations;
	private final String FILE_NAME = "reservations.txt";
	
	public ReceiptPanel(Reservations r, HotelModel hm) {
		reservations = r;
		hotel = hm;
		
		JLabel test = new JLabel("Print receipt");
		
		JButton quitButton = new JButton("Quit");		// will go back to the initial view. call serialize method here.
		quitButton.addActionListener(new 
				ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						serialize();
						System.exit(0);
					}
		});
		
		
		add(test);
		add(quitButton);
	}
	
	
	
	
	/**
	 * Saving all reservations to guestmodel.txt. 
	 */
	public void serialize(){
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
