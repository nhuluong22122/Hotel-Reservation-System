import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * The Panel that allows user to select Simple or Comprehensive receipt  
 * @author nhuluong
 *
 */
public class ReceiptOptions extends JPanel {
	/**
	 * Construct a panel that display 2 receipt options
	 * @param r the data model
	 * @param hm the hotel model
	 * @param gm the guest model
	 */
	public ReceiptOptions(Reservations r, HotelModel hm, GuestModel gm) {
		setLayout(null);

		JLabel label = new JLabel("Select the receipt type");
		JButton simple = new JButton("Simple");
		JButton complicate = new JButton("Comprehensive");

		label.setBounds(275, 50, 200, 50);
		simple.setBounds(200, 100, 300, 100);
		complicate.setBounds(200, 300, 300, 100);

		label.setFont(new Font("Arial", Font.PLAIN, 15));
		simple.setFont(new Font("Arial", Font.PLAIN, 20));
		complicate.setFont(new Font("Arial", Font.PLAIN, 20));

		add(label);
		add(simple);
		add(complicate);

		simple.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hm.update(new ReceiptPanel(r, hm, gm, new SimpleReceipt(r)));
				
				
			}
		});

		complicate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hm.update(new ReceiptPanel(r, hm, gm, new ComprehensiveReceipt(r)));
			}
		});

	}
}
