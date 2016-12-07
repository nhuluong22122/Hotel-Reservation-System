import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Room model for room view MVC model.
 * @author DuocNguyen
 *
 */
public class RoomDataModel {
	private int data;
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * Constructs a room data model.
	 */
	public RoomDataModel() {
		int data = 0;
		listeners = new ArrayList<>();
	}
	
	/**
	 * Gets the currently selected data.
	 * @return Data
	 */
	public int getData()
	{
		return data;
	}

	/**
	 * Add a listener to model.
	 * @param c Listener.
	 */
	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}
	
	/**
	 * Update a new selected room number. 
	 * @param num New room number.
	 */
	public void update(int num)
	{
		data = num;
		for (ChangeListener l : listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}
}
