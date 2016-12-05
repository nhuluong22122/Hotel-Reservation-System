import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RoomDataModel {
	private int data;
	private ArrayList<ChangeListener> listeners;
	
	public RoomDataModel() {
		int data = 0;
		listeners = new ArrayList<>();
	}
	
	public int getData()
	{
		return data;
	}

	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}
	
	public void update(int d)
	{
		data = d;
		for (ChangeListener l : listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}
}
