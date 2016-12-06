import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The model for hotel MVC pattern.
 * @author DuocNguyen
 *
 */
public class HotelModel{
	
	private JPanel data;
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * Constructs a hotel model.
	 * @param p A hotel JPanel.
	 */
	public HotelModel(JPanel p) {
		data = p;
		listeners = new ArrayList<>();
	}
	
	/**
	 * Gets the current hotel JPanel being displayed.
	 * @return
	 */
	public JPanel getData()
	{
		return data;
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
	 * Update Hotel JPanel for display.
	 * @param p A JPanel.
	 */
	public void update(JPanel p)
	{
		data = p;
		for (ChangeListener l : listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}
}
