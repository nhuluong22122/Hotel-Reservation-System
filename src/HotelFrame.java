import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HotelFrame extends JFrame implements ChangeListener{
	private ArrayList<JComponent> a;
	private HotelComponents components;
	private JPanel contentPanel;
	private CardLayout cardLayout;
	private final int FRAME_WIDTH = 700;
	private final int FRAME_HEIGHT =500;
	
	public HotelFrame(HotelComponents model){			 
		cardLayout = new CardLayout();
		contentPanel = new JPanel();
		contentPanel.setLayout(cardLayout);
		
		components = model;
		this.a = components.getData();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		
		contentPanel.add(a.get(0));
		
		cardLayout.show(contentPanel, "hello");
		
		this.setContentPane(contentPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		a = components.getData();
		contentPanel.removeAll();
		contentPanel.add(a.get(0));
		this.setContentPane(contentPanel);
	}
}
