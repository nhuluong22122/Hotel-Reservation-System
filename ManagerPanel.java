import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.*;

public class ManagerPanel extends JPanel{
	
	private HotelModel hotel;
	private ReservationsCalendar reservationsCalendar;
	
	public ManagerPanel(HotelModel h) {
		hotel = h;
		reservationsCalendar = new ReservationsCalendar();
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(200, 200, 300, 100);
		
		
		
		Dimension s = new Dimension(90,90);
		
		JButton loadButton = new JButton("Load");
		loadButton.setPreferredSize(s);
		loadButton.addActionListener(new 
				ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						reservationsCalendar.load();
					}
			
		});
		
		
		
		JButton viewButton = new JButton("View");
		viewButton.setPreferredSize(s);
		viewButton.addActionListener(viewListener());

		
		JButton quitButton = new JButton("Quit");
		quitButton.setPreferredSize(s);
		quitButton.addActionListener(new 
				ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
						
					}
			
		});
		
		panel.add(loadButton);
		panel.add(viewButton);
		panel.add(quitButton);
		add(panel);
		
	}
	
	
	public ActionListener viewListener() {
		return new 
				ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						GregorianCalendar data = new GregorianCalendar();			//selects the current day for views.
						
						CalendarDataModel model = new CalendarDataModel(data);
						
						hotel.update(new ViewPanel(model, reservationsCalendar));
					}
			
		};
	}


}
