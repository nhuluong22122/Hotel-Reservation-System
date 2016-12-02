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

public class WelcomePanel extends JPanel{

	private final String FILE_NAME = "reservations.txt";
	
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
