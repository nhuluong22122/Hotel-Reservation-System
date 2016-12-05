import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


enum MONTHS
{
	January, Febuary, March, April, May, June, July, August, September, October, November, December;
}
enum DAYS
{
	Su, Mo, Tu, We, Th, Fr, Sa ;
}


/**
 * Models a calendar month view.
 * @author DuocNguyen
 * @version 1.0
 */
public class CalendarMonthView extends JPanel{

	private MONTHS[] months; 
	private DAYS[] days;
	private CalendarDataModel model;

	/**
	 * Constructs a calendar month view.
	 * @param model	The model to control this view.
	 */
	public CalendarMonthView(CalendarDataModel model) {
		this.model = model;
		months = MONTHS.values();
		days = DAYS.values();
		updatePanel(model.getData());
	}

	

	/**
	 * Updates the month view.
	 * @param data
	 */
	public void updatePanel(GregorianCalendar data) {
		this.removeAll();
		//this.setPreferredSize(new Dimension(220,250));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		int month = data.get(Calendar.MONTH);
		int year = data.get(Calendar.YEAR);


		//adds navigating months and year on calendar
		JPanel navigate = new JPanel();
		navigate.setBorder(LineBorder.createGrayLineBorder());
		navigate.setLayout(new GridLayout(1,2));
		
		JPanel monthNavigate = new JPanel();		//adds drop down box to navigate months
		JComboBox<MONTHS> monthsBox = new JComboBox<MONTHS>();
		for(int i = 0; i < months.length; i++) {
			monthsBox.addItem(months[i]);
			if(i == month) {
				monthsBox.setSelectedIndex(month);
			}
		}
		monthsBox.addActionListener(getMonthsNavigateListener(monthsBox));
		monthNavigate.add(monthsBox);

		
		JPanel yearShift = new JPanel();		//adds shifts buttons to navigate year
		yearShift.setLayout(new GridLayout(1,2));
		JButton leftButton = new JButton("<");
		leftButton.addActionListener(getCalendarViewActionListerner(-1));			//shift  back 1 year

		JButton rightButton = new JButton(">");
		rightButton.addActionListener(getCalendarViewActionListerner(1));			//shift  ahead 1 year
		yearShift.add(leftButton);
		yearShift.add(rightButton);
		
		navigate.add(monthNavigate);
		navigate.add(yearShift);


		
		JLabel headerLabel = new JLabel(months[month].toString()+ " " + year);


		JPanel dayOfWeekLabels = new JPanel();
		dayOfWeekLabels.setLayout(new GridLayout(1,9));
		for(int i = 0; i < days.length; i++) {
			JLabel l = new JLabel(days[i].toString(), SwingConstants.CENTER);
			dayOfWeekLabels.add(l);
		}

		GregorianCalendar temp = new GregorianCalendar(data.get(Calendar.YEAR), data.get(Calendar.MONTH), 1);
		int daysOfMonth = temp.getActualMaximum(Calendar.DAY_OF_MONTH);
		int firstDayOfMonth = temp.get(Calendar.DAY_OF_WEEK);


		JPanel dayLabels = new JPanel();
		dayLabels.setLayout(new GridLayout(6,7));

		//offset
		for(int i = 1; i <= firstDayOfMonth-1;i++) {
			JLabel b = new JLabel();
			b.setVisible(false);
			dayLabels.add(b);
		}


		//buttons 
		for(int i = 1; i <= daysOfMonth; i++) {
			JLabel b = new JLabel(i +"", SwingConstants.CENTER);
			if(i == data.get(Calendar.DAY_OF_MONTH)) {
				JButton highLight = new JButton(i +"");
				dayLabels.add(highLight);
			} else {
				dayLabels.add(b);
			}
			b.addMouseListener(new
					MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
						Point mousePoint = e.getPoint();
						if(b.contains(mousePoint)) {
							int year = data.get(Calendar.YEAR);
							int month = data.get(Calendar.MONTH);
							
							int day = Integer.parseInt(b.getText());
						
							GregorianCalendar newDate = new GregorianCalendar(year, month, day);
							model.update(newDate);
						}
				}
			});
			
		}



		//offset
		for(int i = 1; i <= (42-firstDayOfMonth-daysOfMonth); i++) {
			JLabel b = new JLabel();
			b.setVisible(false);
			dayLabels.add(b);
		}

		//set alignment
		yearShift.setAlignmentX(CENTER_ALIGNMENT);
		headerLabel.setAlignmentX(CENTER_ALIGNMENT);
		dayOfWeekLabels.setAlignmentX(CENTER_ALIGNMENT);
		dayLabels.setAlignmentX(CENTER_ALIGNMENT);

		//add all components 
		add(navigate);
		add(headerLabel);
		add(dayOfWeekLabels);
		add(dayLabels);
		
		this.revalidate();
		this.repaint();
	}

	/**
	 * Changes the view to the next or previous day from currently selected day.
	 * @param offset -1 for previous or 1 for next day.
	 * @param currentDate The current selected day on calendar.
	 * @param model The model that controls all views.
	 * @return	An action listener for the back and forth buttons.
	 */
	private ActionListener getCalendarViewActionListerner(int offset) {
		return new 
				ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				GregorianCalendar data = model.getData();
				int year = data.get(Calendar.YEAR);
				int month = data.get(Calendar.MONTH);
				int day = data.get(Calendar.DAY_OF_MONTH);
				GregorianCalendar newDate = new GregorianCalendar(year+offset, month, day);
				model.update(newDate);
			} 

		};
	}
	
	private ActionListener getMonthsNavigateListener(JComboBox<MONTHS> c){
		return new 
				ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						GregorianCalendar data = model.getData();
						int year = data.get(Calendar.YEAR);
						int month = c.getSelectedIndex();
						int day = data.get(Calendar.DAY_OF_MONTH);
						GregorianCalendar newDate = new GregorianCalendar(year, month, day);
						model.update(newDate);
						System.out.println(month);
					}
			
		};
	}

}
