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

public class ViewPanel extends JPanel implements ChangeListener{


	private CalendarDataModel model;
	private CalendarMonthView mView;
	private JPanel monthView;
	private RoomView rView;
	private JPanel contentPane;
	private TreeMap<Date, ArrayList<Room>> resMap;


	public ViewPanel(CalendarDataModel m, ReservationsCalendar rc, HotelModel hm){
		model = m;
		resMap = rc.getReservationsMap();
		model.attach(this);

		contentPane = new JPanel();
		mView = new CalendarMonthView(model);
		rView = new RoomView(hm);
		contentPane.add(rView);
		contentPane.add(monthView());
		add(contentPane);

	}

	private JPanel monthView() {

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



	public JPanel getRoomsInfo(String s){
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBorder(LineBorder.createGrayLineBorder());
		JLabel header = new JLabel(s, SwingConstants.CENTER);
		p.add(header);
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
	 * Get get all reserved rooms on selected day.
	 * @param rs
	 * @return
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
		for(Room r: rs ){
			for(int i = 1; i <=20; i++){
				if(r.getRoomNumber() != i){
					JLabel j = new JLabel("" + i, SwingConstants.CENTER);
					p.add(j);
				}
			}
		}
		return p;
	}


	public static Date dateConverter(GregorianCalendar d){
		String date = formatDate(d.getTime());
		Date result = new Date(date);

		return result;
	}

	/**
	 * Returns a string in MM/DD/YY format from the date of the event
	 * Example: Date date of event becomes 10/02/2016 (mm/dd/yy)
	 * @return a String in MM/DD/YY format
	 */
	public static String formatDate(Date d) {
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

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		GregorianCalendar data = model.getData();
		mView.updatePanel(data);
		rView.reset();
		contentPane.remove(monthView);
		contentPane.add(monthView());
		revalidate();
	}

}


//*****continue with how to show available rooms and reserved rooms