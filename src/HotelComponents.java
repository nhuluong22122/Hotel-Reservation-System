import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HotelComponents {
	
	private ArrayList<JComponent> c;
	private ArrayList<ChangeListener> listeners;
	
	public HotelComponents(ArrayList<JComponent> data) {
		this.c = data;
		listeners = new ArrayList<>();
	}
	
	public ArrayList<JComponent> getData()
	{
		return (ArrayList<JComponent>)(c.clone());
	}

	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}
	
	public void update(int location, JComponent c)
	{
		this.c.set(location, c);
		for (ChangeListener l : listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}
}
