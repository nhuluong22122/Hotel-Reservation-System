import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * The first screen of the reservation system that allows user to select to proceed as a guest or a manager
 * @author DuocNguyen
 *
 */
public class WelcomePanel extends JPanel{

	private final String FILE_NAME = "reservations.txt";
	/**
	 * Constructor that loads the data whenever the program starts and display 2 buttons
	 * @param hotel the hotel model being used to switch frame
	 */
	public WelcomePanel(HotelModel hotel) {
		setLayout(null);

		JButton managerButton = new JButton("Manager");
		managerButton.setBounds(400, 175, 200, 100);
		managerButton.setFont(new Font("Arial", Font.PLAIN, 15));
		
		managerButton.addActionListener(new 
				ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotel.update(new ManagerPanel(hotel));		//first idea
			}
		});

		JButton guestButton = new JButton("Guest");
		guestButton.setFont(new Font("Arial", Font.PLAIN, 15));
		guestButton.setBounds(100,175,200,100);
		guestButton.addActionListener( new 
				ActionListener() {

					public void actionPerformed(ActionEvent e) {
						Reservations r = deserialize();
						GuestModel guestModel = new GuestModel(r);
						hotel.update(new UserPanel(hotel, guestModel));
					}
		});

		add(managerButton);
		add(guestButton);
	}
	
	

	/**
	 * Gets all reservations from guestmodel.txt file.
	 * @return	Events Calendar.
	 */
	public Reservations deserialize(){
		Reservations result = null;

		try {
			File file = new File(FILE_NAME);
			
			if(!file.exists()){
				return new Reservations();	//returns a new GuestModel for the very first run
			}
			
			FileInputStream fileIn = new FileInputStream(FILE_NAME);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			result = (Reservations) in.readObject();
			in.close();
			fileIn.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}


}
