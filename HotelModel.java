import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HotelModel{
	
	private JPanel data;
	private ArrayList<ChangeListener> listeners;
	
	public HotelModel(JPanel d) {
		data = d;
		listeners = new ArrayList<>();
	}
	
	public JPanel getData()
	{
		return data;
	}

	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}
	
	public void update(JPanel d)
	{
		data = d;
		for (ChangeListener l : listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}
}
