import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReceiptPanel extends JPanel{
	
	private GuestModel guestModel;
	private HotelModel hotel;
	private final String FILE_NAME = "guestmodel.txt";
	
	public ReceiptPanel(GuestModel gm, HotelModel hm) {
		guestModel = gm;
		hotel = hm;
		
		JLabel test = new JLabel("Print receipt");
		
		JButton quitButton = new JButton("Quit");		// will go back to the initial view. call serialize method here.
		quitButton.addActionListener(new 
				ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						serialize();
						hotel.update(new WelcomePanel(hotel));
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
			out.writeObject(guestModel);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
