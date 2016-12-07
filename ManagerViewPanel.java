import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Models a view panel consisting of room view and month view.
 * @author DuocNguyen
 *
 */
public class ManagerViewPanel extends JPanel implements ChangeListener{


	private CalendarDataModel model;
	private CalendarMonthView mView;
	private JPanel monthView;
	private RoomView rView;
	private JPanel contentPane;
	private TreeMap<Date, ArrayList<Room>> resMap;

	/**
	 * Constructs a view panel.
	 * @param m Calendar data model.
	 * @param rc Reservations calendar
	 * @param hm Hotel model
	 */
	public ManagerViewPanel(CalendarDataModel m, ReservationsCalendar rc, HotelModel hm){
		model = m;
		resMap = rc.getReservationsMap();
		model.attach(this);

		contentPane = new JPanel();
		mView = new CalendarMonthView(model);
		rView = new RoomView(hm);
		contentPane.add(rView);
		contentPane.add(getMonthView());
		add(contentPane);

	}

	/**
	 * Gets the month view panel.
	 * @return month view panel.
	 */
	private JPanel getMonthView() {

		monthView = new JPanel();	//this is monthView
		monthView.setPreferredSize(new Dimension(300,465));
		monthView.setBorder(LineBorder.createGrayLineBorder());	//adds gray lines
		monthView.setLayout(new BoxLayout(monthView, BoxLayout.Y_AXIS));
		mView.setBorder(LineBorder.createGrayLineBorder());	//adds gray lines
		monthView.add(mView);	//add the calendar


		JPanel roomInfo = new JPanel();	//room info view
		roomInfo.setPreferredSize(new Dimension(300, 250));
		roomInfo.setBorder(LineBorder.createGrayLineBorder());
		roomInfo.setLayout(new BoxLayout(roomInfo, BoxLayout.Y_AXIS));


		JPanel p1 = getRoomsInfo("Available Rooms");	//gets available rooms
		JPanel p2 = getRoomsInfo("Reserved Rooms");		//gets reserved rooms


		roomInfo.add(p1);
		roomInfo.add(p2);


		monthView.add(roomInfo); //add room info panel
		return monthView;
	}


	/**
	 * Gets all the available or reserved rooms.
	 * @param s Available or Reserved rooms.
	 * @return A panel containing requested rooms.
	 */
	public JPanel getRoomsInfo(String s){
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.setBorder(LineBorder.createGrayLineBorder());
		JLabel header = new JLabel(s, SwingConstants.CENTER);
		p.add(header, BorderLayout.NORTH);
		Date data = dateConverter(model.getData());
		int numOfReservedRooms = 0;
		ArrayList<Room> rooms = new ArrayList<Room>();
		if(resMap.containsKey(data)){
			rooms = resMap.get(data);
			rView.setReservedRooms(rooms);			//*****sets array list of reserved rooms in room view.
			numOfReservedRooms = rooms.size();		//figure this out. How come it doesn't go in here?
		}
		if(s.startsWith("A")){
			//available
			p.add(getAvailableRooms(rooms));
			//get the reserved room number and print it.
		} else {
			//reserved
			p.add(getReservedRooms(rooms));			//pass in parameter array list of reserved rooms. Then
		}

		return p;
	}

	/**
	 * Get get all reserved room numbers on selected day.
	 * @param rs List of reservedRooms
	 * @return	A panel of all reserved room numbers.
	 */
	public JPanel getReservedRooms(ArrayList<Room> rs){
		JPanel p = new JPanel();
		p.setBorder(LineBorder.createGrayLineBorder());
		p.setLayout(new GridLayout(2,5));
		for(Room r: rs ){
			for(int i = 1; i <=20; i++){
				if(r.getRoomNumber() == i){
					JLabel j = new JLabel("" + i, SwingConstants.CENTER);
					p.add(j);

				}
			}
		}

		return p;
	}

	/**
	 * Gets all the available room numbers on the selected day.
	 * @param rs List of reserved room
	 * @return A panel of all available room numbers.
	 */
	public JPanel getAvailableRooms(ArrayList<Room> rs){
		JPanel p = new JPanel();
		p.setBorder(LineBorder.createGrayLineBorder());
		p.setLayout(new GridLayout(2,5));
		//set all 20 rooms available if no rooms are reserved 
		if(rs.isEmpty()){
			for(int i = 1; i <=20; i++){
				JLabel j = new JLabel("" + i, SwingConstants.CENTER);
				p.add(j);
			}
			return p;
		}
		//get available rooms except reserved rooms

		
		for(int i = 1; i <=20; i++){
			boolean available = true;
			for(Room r: rs ){
				if(r.getRoomNumber() == i){
					available = false;
				}
			}
			if(available){
				JLabel j = new JLabel("" + i, SwingConstants.CENTER);
				p.add(j);
			}
		}

		return p;
	}


	/**
	 * Convert Gregorian date to Date object. 
	 * @param d Selected Gregorian date.
	 * @return	Date object on selected date.
	 */
	public static Date dateConverter(GregorianCalendar d){
		String date = ReservationPanel.formatDate(d.getTime());
		Date result = new Date(date);

		return result;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		GregorianCalendar data = model.getData();
		mView.updatePanel(data);
		rView.reset();
		contentPane.remove(monthView);
		contentPane.add(getMonthView());
		revalidate();
	}

}