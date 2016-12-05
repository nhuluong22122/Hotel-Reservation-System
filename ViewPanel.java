import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.GregorianCalendar;

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

	private HotelModel hotel;
	private CalendarDataModel model;
	private CalendarMonthView mView;
	private RoomView rView;
	
	public ViewPanel(HotelModel h, CalendarDataModel m){
		model = m;
		hotel = h;
		model.attach(this);
		
		JPanel contentPane = new JPanel();
		mView = new CalendarMonthView(model);
		rView = new RoomView(model);
		contentPane.add(monthView());
		contentPane.add(rView);
		
		add(contentPane);
		
	}

	private JPanel monthView() {

		JPanel monthView = new JPanel();	//this is monthView
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

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		GregorianCalendar data = model.getData();
		mView.updatePanel(data);
		revalidate();
	}
	
	private JPanel getRoomsInfo(String s){
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBorder(LineBorder.createGrayLineBorder());
		JLabel header = new JLabel(s, SwingConstants.CENTER);
		p.add(header);
		p.add(getRooms());
		
		
		return p;
	}
	
	private JPanel getRooms() {		//needs event handling here
		JPanel p = new JPanel();
		p.setBorder(LineBorder.createGrayLineBorder());
		p.setLayout(new GridLayout(2,5));
		for(int i = 1 ; i <= 10; i++) {
			JLabel j = new JLabel("" + i, SwingConstants.CENTER);
			p.add(j);
		}
		return p;
	}
	
}
