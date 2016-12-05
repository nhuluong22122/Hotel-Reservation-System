import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Models a data model class for MVC pattern.
 * @author DuocNguyen
 * @version 1.0
 */
public class CalendarDataModel {

	private GregorianCalendar data;
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * Constructs a data model.
	 * @param data The date selected for views.
	 */
	public CalendarDataModel(GregorianCalendar data){
		this.data = data;
		this.listeners = new ArrayList<>();
	}
	
	/**
	 * Gets the date selected.
	 * @return	Selected date in GregorianCalendar.
	 */
	public GregorianCalendar getData()
	{
		return (GregorianCalendar) (data.clone());
	}
	
	/**
	 * Attach a listener to model.
	 * @param c The listener.
	 */
	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}
	
	/**
	 * Changes the selected date.
	 * @param date	The new selected date.
	 */
	public void update(GregorianCalendar date)
	{
		this.data = date;
		for (ChangeListener l : listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}
}
