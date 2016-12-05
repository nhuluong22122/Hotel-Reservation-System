import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class RoomView extends JPanel{
	private CalendarDataModel model;
	
	public RoomView(CalendarDataModel m){
		setPreferredSize(new Dimension(390, 465));
		this.setBorder(LineBorder.createGrayLineBorder());	//adds gray lines
		this.setLayout(new BorderLayout());
		update();
	}
	
	public void update() {
		JPanel buttons = new JPanel();
		buttons.setPreferredSize(new Dimension(460, 50));
		buttons.setBorder(LineBorder.createGrayLineBorder());
		buttons.setLayout(new GridLayout(1,2));
		
		JButton saveButton = new JButton("Save");
		JButton quitButton = new JButton("Quit");
		
		//add buttons to buttons panel
		buttons.add(saveButton);
		buttons.add(quitButton);
		
		
		
		
		JPanel rv = new JPanel();
		rv.setBorder(LineBorder.createGrayLineBorder());	//adds gray lines
		rv.setLayout(new GridLayout(2,1));
		
		
		
		JPanel rooms = new JPanel();
		rooms.setBorder(LineBorder.createGrayLineBorder());
		rooms.setLayout(new GridLayout(4,5));
		for(int i = 1; i <= 20; i++){
			JLabel l = new JLabel(i+"", SwingConstants.CENTER);
			l.setBorder(LineBorder.createGrayLineBorder());
			rooms.add(l);
		}
		
		
		
		JPanel selectedRoomInfo = new JPanel();
		selectedRoomInfo.setBorder(LineBorder.createGrayLineBorder());
		JLabel roomInfo = new JLabel();
		roomInfo.setText("Room information");
		selectedRoomInfo.add(roomInfo);
		
		
		
		rv.add(rooms);
		rv.add(selectedRoomInfo);
		//add to this JPane.
		add(buttons, BorderLayout.SOUTH);
		add(rv);
	}
}
