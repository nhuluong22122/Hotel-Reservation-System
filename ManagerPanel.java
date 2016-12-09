import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Models a Manager Panel.
 * @author DuocNguyen
 *
 */
public class ManagerPanel extends JPanel{
    
    private HotelModel hotel;
    private ReservationsCalendar reservationsCalendar;
    private final Dimension BUTTON_DIMENSION = new Dimension(90,90);
    
    /**
     * Constructs a manager panel.
     * @param h	The hotel model.
     */
    public ManagerPanel(HotelModel h) {
        hotel = h;
        reservationsCalendar = new ReservationsCalendar();
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(200, 200, 300, 100);
        JLabel loadedLabel = new JLabel();
        loadedLabel.setBounds(327, 350, 50, 50);
        
        
        JButton loadButton = new JButton("Load");
        loadButton.setPreferredSize(BUTTON_DIMENSION);
        loadButton.addActionListener(new
                                     ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservationsCalendar.load();			//loads all guest reservations for view function.
                loadedLabel.setText("Loaded");			//prompt that reservations have been loaded.
            }
            
        });
        
        
        
        JButton viewButton = new JButton("View");
        viewButton.setPreferredSize(BUTTON_DIMENSION);
        viewButton.addActionListener(new
                                     ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalendarDataModel model = new CalendarDataModel();		//creates a data model for calendar view
                hotel.update(new ManagerViewPanel(model, reservationsCalendar, hotel));
            }
            
        });
        
        
        JButton quitButton = new JButton("Quit");
        quitButton.setPreferredSize(BUTTON_DIMENSION );
        quitButton.addActionListener(new 
                                     ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                hotel.update(new WelcomePanel(hotel));		//switch back to welcome panel.
                
            }
            
        });
        
        panel.add(loadButton);
        panel.add(viewButton);
        panel.add(quitButton);
        add(panel);
        add(loadedLabel);
    }
    
    
}
