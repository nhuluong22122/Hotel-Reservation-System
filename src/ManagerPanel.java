import java.util.ArrayList;

import javax.swing.*;

public class ManagerPanel extends JPanel{
	
	
	public ManagerPanel() {
		
		setLayout(null);
		
		JButton viewButton = new JButton("View");
		viewButton.setBounds(20, 200, 50, 50);

		JButton saveButton = new JButton("Save");
		saveButton.setBounds(120, 200, 50, 50);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setBounds(220, 200, 50, 50);
		
		add(viewButton);
		add(saveButton);
		add(quitButton);
		
	}


}
